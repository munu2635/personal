#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <sys/file.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <time.h>

#define MAXLINE 511
#define MAX_SOCK 1024

char *EXIT_STRING = "exit";
char *START_STRING = " Connected to chat_server \n";

int maxfdp1;
int num_chat = 0;
int clisock_list[MAX_SOCK];
time_t clisock_time[MAX_SOCK];
char *clisock_ip[MAX_SOCK];
int listen_sock;

void addClient(int s, struct sockaddr_in *newcliaddr, time_t now); 
int getmax();
void save_message(char* buf, time_t now); //메세지 출력
void control_data(); //접속자 수 달라졌을때 출력 
void removeClient(int s);
int tcp_listen(int host, int port, int backlog);
void errquit(char *mesg);

int main(int argc, char *argv[]){
	struct sockaddr_in cliaddr;
	char buf[MAXLINE + 1];
	int i, j, nbyte, accp_sock, addrlen = sizeof(struct sockaddr_in);
	fd_set read_fds;
	time_t now;


	if(argc != 2){
		printf("Usage : %s port \n", argv[0]);
		exit(0);
	}

	listen_sock = tcp_listen(INADDR_ANY, atoi(argv[1]), 5);

	while(1){
		FD_ZERO(&read_fds);
		FD_SET(listen_sock, &read_fds);
		for(i=0; i<num_chat; i++)
			FD_SET(clisock_list[i], &read_fds);
		maxfdp1 = getmax() + 1;
		puts("wait for client");
		if(select(maxfdp1, &read_fds, NULL, NULL, NULL) < 0)
			errquit("select fail");

		if(FD_ISSET(listen_sock, &read_fds)){
			accp_sock = accept(listen_sock, (struct sockaddr *)&cliaddr, &addrlen);
			if(accp_sock == -1)
				errquit("accept fail");
			addClient(accp_sock, &cliaddr, now);
			send(accp_sock, START_STRING, strlen(START_STRING), 0);
			printf("add user number - %d \n", num_chat);
		}

		for( i = 0; i < num_chat; i++){
			if(FD_ISSET(clisock_list[i], &read_fds)){

				nbyte = recv(clisock_list[i], buf, MAXLINE, 0);
				if(nbyte <= 0) {
					removeClient(i);
					continue;
				}

				buf[nbyte] = 0;
				if(strstr(buf, EXIT_STRING) != NULL) {
					removeClient(i);
					continue;
				}

				for ( j = 0; j < num_chat; j++)
					send(clisock_list[j], buf, nbyte, 0);
				save_message(buf, now);
				printf("%s\n", buf);
			}
		}
	}
	return 0;
}

void addClient(int s, struct sockaddr_in *newcliaddr, time_t now){
	char buf[20];
	inet_ntop(AF_INET, &newcliaddr -> sin_addr, buf, sizeof(buf));
	printf("new client : %s\n", buf);
	clisock_list[ num_chat ] = s; 
	time(&now); clisock_time[ num_chat ] = now;
	clisock_ip[ num_chat ] = inet_ntoa(newcliaddr->sin_addr);
	num_chat++;
	control_data();
}

void removeClient(int s){
	close(clisock_list[s]);
	if( s != num_chat -1 ){
		clisock_list[s] = clisock_list[num_chat-1];
		clisock_time[s] = clisock_time[num_chat-1];
		clisock_ip[s] = clisock_ip[num_chat-1];
	}
	num_chat--;
	printf("1 user out. now user = %d\n", num_chat);
	control_data();
}

int getmax(){
	int max = listen_sock;
	int i;
	for( i = 0; i < num_chat; i++)
		if(clisock_list[i] > max )
			max = clisock_list[i];
	return max;
}

void control_data(){//새로운 client가 추가될때 마다 시간값이 변함 
	int i;
	printf("---------------------------------------\n");
	printf("현재 서버 총 접속자수 : %d\n", num_chat);
	printf("현재 접속 중인 클라이언트 IP - 접속시간\n");
	printf("---------------------------------------\n");
	for(i = 0; i < num_chat; i++){
		printf("%s - %s", clisock_ip[i], ctime(&clisock_time[i]) );
	}
	printf("---------------------------------------\n");
}

void save_message(char* buf, time_t now){
	FILE *f;
	f = fopen("save_message.txt", "a");
	time(&now);
	printf("---------------------------------------\n");
	fprintf(f, "(%lu)%s-%s", strlen(buf)-6, buf, ctime(&now));
	fclose(f);
}

int tcp_listen(int host, int port, int backlog){
	int sd;
	struct sockaddr_in servaddr;

	sd = socket(AF_INET, SOCK_STREAM, 0);
	if(sd == -1)
		errquit("socket fail");

	bzero((char *)&servaddr, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(host);
	servaddr.sin_port = htons(port);

	if(bind(sd, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0)
		errquit("bind fail");

	listen(sd, backlog);
	return sd;
}

void errquit(char *mesg){
	perror(mesg);
	exit(0);
}
