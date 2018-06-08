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
int Save(int sd);
int Send(int sd);

#define MAXLINE 511
#define SENDLEN 200



int main(int argc, char *argv[]){
	char get_message[ 10 ];
	struct sockaddr_in cliaddr, servaddr;
	int listen_sock, accp_sock, addrlen = sizeof(cliaddr);
	pid_t pid;

	if(argc != 2){
		printf(" 사용법 : %s port\n", argv[0]);
		exit(0);
	}


	if((listen_sock = socket(PF_INET, SOCK_STREAM, 0 )) < 0) {
		perror("socket fail");
		exit(0);
	}

	bzero((char*)&servaddr, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(atoi(argv[1]));

	if(bind(listen_sock, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0){
		perror("bind fail");
		exit(0);
	}
	// 클라이언트가 들어오고 나갈때 반복되는 부분 
	while(1){
		printf("-----------------------------------------\n");
		puts("서버가 클라이언트를 기다리고 있습니다. ");
		listen(listen_sock, 1);
	
		// 클라이언트 연결 
		if((accp_sock = accept(listen_sock, (struct sockaddr *)&cliaddr, &addrlen)) < 0) {
			perror("accept fail");
			exit(0);
		}

		puts("클라이언트가 연결되었습니다. ");
		
		// 클라이언트가 보내는 값으로 어떤 작업을 할것인지 분류되는 지점 
		while(1) {
			printf("-----------------------------------------\n");
			puts("클라이언트 입력 대기. ");

			recv(accp_sock, get_message, sizeof(get_message),0);
			printf("%s",get_message); 

			if(strstr(get_message, GET_STRING) != NULL)
				Send(accp_sock);
			else if(strstr(get_message, PUT_STRING) != NULL)
				Save(accp_sock);
			else if(strstr(get_message, EXIT_STRING) != NULL){
				puts("end....."); close(accp_sock); break;
			}
		}
	}
	close(listen_sock);
	close(accp_sock);
	return 0;
}

// 클라이언트가 입력한 파일을 전송하는 함수
int Send(int sd){
	char buf[ MAXLINE + 1 ];
	int nbyte;
	char getname[ MAXLINE + 1 ];
	size_t filesize = 0, testsize = 0, get_len = 0; 
	FILE *file = NULL;
	printf("-----------------------------------------\n");
	puts("파일을 전송합니다. ");
	
	// 파일의 이름을 받는다.
	if(recv(sd, &getname, sizeof(getname), 0) == -1){
		perror("recverror");
		return 0;
	}
	printf("전송할 파일 이름 : %s",getname);
	file =	fopen(getname, "rb");

	// 파일이 없을 경우 0값을 전송하고 종료한다.
	if(file == NULL) {
		send(sd, &filesize, sizeof(filesize), 0);
		printf("\n파일 없음\n");
		return 0;
	}else { //파일이 있을 경우 
		printf("\n파일 전송\n");

			
		fseek(file, 0, SEEK_END);	// 파일 끝으로 이동한다. 
		filesize = ftell(file); 	// 파일 끝의 위치를 갖고온다.
		fseek(file, 0, SEEK_SET); 	// 다시 파일을 앞으로 갖고온다. 
		
		send(sd, &filesize, sizeof(filesize), 0); // 알아낸 파일의 길이를 전송한다.

		while(testsize != filesize)
 		{ 
			if(filesize < SENDLEN){	// 파일 길이가SENDLEN보다 작은 값일때 파일 전송
				get_len = fread(buf, 1, filesize, file);
				send(sd, buf, get_len, 0); 
				break;
			}  else if (filesize - testsize < SENDLEN) {
					// 파일 길이가 SENDLEN보다 큰 값이고 
					// 전송할 남은 길이가 SENDLEN보다 작을때 파일전송
				get_len = fread(buf, 1, testsize, file); 
				send(sd, buf, get_len, 0);
			} else {		// 파일 길이가 SENDLEN보다 큰 값일때 파일 전송 
				get_len = fread(buf, 1, SENDLEN, file);  
				send(sd, buf, get_len, 0); 
				testsize = testsize + get_len;
			}
		}

		printf("전송완료.\n");
		fclose(file);
	}
	return 0;
}

// 클라이언트가 전송할 파일을 처리 및 저장하는 함수
int Save(int sd) {
	char buf[ MAXLINE + 1 ];
	int nbyte;
	char getname[ MAXLINE + 1 ];
	size_t filesize = 0, input_size = 0;
	FILE *file = NULL; 
	printf("-----------------------------------------\n");
	puts("파일을 업로드합니다. ");
	// 파일 이름을 클라이언트로 부터 받는다. 
	recv(sd, &getname, sizeof(getname), 0);
	// 파일이 없을 경우 0값을 받아서 처리 
	if(*getname == 0){
		printf("데이터가 없습니다.\n");
		return 0;
	} else { // 파일이 존재할 경우 
		file = fopen(getname, "wb"); // 업로드할 파일 생성
		printf("업로드 할 파일 이름 : %s \n",getname);
		puts("파일 업로드");

		recv(sd, &filesize, sizeof(filesize), 0); // 파일 길이를 전송받는다.
		input_size = SENDLEN; 

		while(filesize != 0) { 
			if(filesize < SENDLEN) input_size = filesize; 	// 파일길이가 SENDLEN보다 작을경우 

			nbyte = recv(sd, buf, input_size, 0);	 	// 파일에 들어갈 문자들을 받아온다.
			fwrite(buf, sizeof(char), nbyte, file);		// 받아온 파일들로 값 작성
				
			filesize = filesize - nbyte;  			// 남은 파일 사이즈 기록
			nbyte = 0;
		}
		printf("업로드완료.\n");
		fclose(file);
	}

}
