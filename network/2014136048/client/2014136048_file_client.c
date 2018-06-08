#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

char *EXIT_STRING = "exit";
char *GET_STRING = "get";
char *PUT_STRING = "put";
int get_and_save(int sd);
int put_and_send(int sd);

#define MAXLINE 511
#define SENDLEN 200


int main(int argc, char *argv[]){
	char buf[10];
	struct sockaddr_in servaddr;
	static int s;
	pid_t pid;

	// 입력 받은 인자 확인
	if(argc != 3){
		printf(" 사용법 : %s server_ip port\n", argv[0]);
		exit(0);
	}

	// 소켓 
	if((s = socket(PF_INET, SOCK_STREAM, 0 )) < 0) {
		perror("Client: Can't open stream socket.\n");
		exit(0);
	}
	
	bzero((char*)&servaddr, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	inet_pton(AF_INET, argv[1], &servaddr.sin_addr);
	servaddr.sin_port = htons(atoi(argv[2]));
	// 연결
	if(connect(s, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0) {
		perror("Client: can't connect to server.\n");
		exit(0);
	}
	// client사용자로부터 할 일을 선택하는 부분 
	// get  - server로 부터 파일 받아오기
	// put  - server로 파일 전송
	// exit - 종료 
	// exit 값이 나올때 까지 while문에서 반복된다. 

	while(1) {
		printf("----------------------------------\n");
		printf("할행동을 입력해주세요.\n");
		printf("get, put, exit\n -> ");
		

		if(fgets(buf, sizeof(buf), stdin) != NULL) {
			send(s,	buf, sizeof(buf), 0);
			if(strstr(buf, PUT_STRING) != NULL)
				put_and_send(s);
			else if(strstr(buf, GET_STRING) != NULL)
				get_and_save(s);
			else if(strstr(buf, EXIT_STRING) != NULL){
				puts("end."); close(s); exit(0);
			} else puts("잘못 입력하셨습니다.\n");
			
		}	
	}
	close(s);
	return 0;
}
	
// 파일을 서버에 전송하는 함수get
int put_and_send(int sd){
	char buf[ MAXLINE + 1 ];
	int nbyte;
	size_t filesize = 0, testsize = 0, get_len = 0; 
	FILE *file = NULL;
	
	
	printf("----------------------------------\n");
	printf("보낼 파일명을 입력해주세요.\n");
	printf("->");
	
	// 사용자로부터 전송할 파일을 입력받는다.	
	if(fgets(buf, sizeof(buf), stdin) != NULL){
		buf[strlen(buf)-1] = '\0';

		file =	fopen(buf, "rb");
		// 파일이 없다면 0을 보내고 함수 종료
		if(file == NULL) {
			send(sd, &filesize, sizeof(filesize), 0);
			printf("파일이 없습니다.\n");
			return 0;
		// /값이 있다면 함수 종료.
		} else if(strstr(buf,"/") != NULL){
			send(sd, &filesize, sizeof(filesize), 0);
			printf("다른 위치에 있는 값은 보낼 수 없습니다.\n");
			return 0;

		// 파일이 있다면 전송
		} else {
			printf("\n파일을 전송합니다..\n");
			send(sd, &buf, sizeof(buf), 0); // 전송할 파일 이름 전송
			fseek(file, 0, SEEK_END); 	// 파일 끝으로 이동
			filesize = ftell(file); 	// 파일 전체 길이 파악
			fseek(file, 0, SEEK_SET); 	// 파일 앞으로 다시 이동

			send(sd, &filesize, sizeof(filesize), 0); // 알아낸 파일 길이를 전송한다.
 	
			while(testsize != filesize)
		 	{ 
				if(filesize < SENDLEN){ 	
					// 파일 길이가SENDLEN보다 작은 값일때 파일 전송
					get_len = fread(buf, 1, filesize, file); 
					// 파일 길이만큼 읽어서 파일전송
					send(sd, buf, get_len, 0);
					break;
				} else if (filesize - testsize < SENDLEN) {
					// 파일 길이가 SENDLEN보다 큰 값이고 
					// 전송할 남은 길이가 SENDLEN보다 작을때 파일전송
					get_len = fread(buf, 1, testsize, file); 
					send(sd, buf, get_len, 0);
				} else {		
					// 파일 길이가 SENDLEN보다 큰 값일때 파일 전송 
					get_len = fread(buf, 1, SENDLEN, file);
					//SENDLEN만큼 읽어서 파일전송 
					send(sd, buf, get_len, 0); 
					testsize = testsize + get_len;  
				}
			}
			printf("전송 완료.\n");
		}
		fclose(file);
	}

	return 0;
}
// 파일을 서버에서 다운로드 하는 함수
int get_and_save(int sd) {
	char buf[ MAXLINE + 1 ];
	int nbyte;
	FILE *file = NULL;
	size_t filesize = 0, input_size = 0;
	char *str, *name;

	printf("----------------------------------\n");
	printf("받을 데이터를 입력해주세요.\n");
	printf(" -> ");
	// 사용자로부터 전송할 파일을 입력받는다.	
	if(fgets(buf, sizeof(buf), stdin) != NULL){

		buf[strlen(buf) - 1] = '\0';

		// 파일 이름을 전송
		send(sd, &buf, sizeof(buf), 0);				

		// recv 오류처리
		if(recv(sd, &filesize, sizeof(filesize), 0) == -1){
			perror("recv 1 fail");
			return -1;
		}
		// 파일이 없을때 수행
		if(filesize == 0){
			printf("데이터가 없습니다.\n");
			return 0;
		} else{ // 파일이 있을때 수행
			printf("파일을 다운합니다..\n");

			if(strstr(buf,"/") != NULL){		   // 위치가 다른 파일이 있을때 파일명을 받아오기 위해 사용하였다. 
				str = strtok(buf, "/");		   // 받아온 이름에서 '/'값을 제거한 앞부분 
   				while (str != NULL){               // 자른 문자열이 나오지 않을 때까지 반복 
					str = strtok(NULL, "/");
					if(str != NULL) name = str; // str 값이 널값이 아닐때의 마지막값을 name에 저장 
				}
				file = fopen(name, "wb"); // 전송받아서 쓸 파일 열기
			}
			else file = fopen(buf, "wb"); // 전송받아서 쓸 파일 열기
			input_size = SENDLEN;

			while(filesize !=0){
				if(filesize < SENDLEN) input_size = filesize; 	// 파일 사이즈가 SENDLEN보다 작다면 input_size에 파일 사이즈 입력
	
				nbyte = recv(sd, buf, input_size, 0);	  	// 파일에 들어갈 문자들을 받아온다.	
				fwrite(buf, sizeof(char), nbyte, file);	  	// 받아온 파일들로 값 작성

				filesize = filesize - nbyte; 		  	// 남은 파일 사이즈 기록
				nbyte = 0;
			}
			fclose(file);
			printf("다운로드 완료.\n");
		}
	}
	return 0;
}
