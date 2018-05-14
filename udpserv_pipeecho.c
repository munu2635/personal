#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <errno.h>

#define MAX_BUFSZ 512

typedef struct mesg {
	struct sockadder_in addr;
	char data[ MAX_BUFSZ ];
} mesg_t;

void child_start(int sock, int pipefd[]);
void parent_start(int sock, int pipefd[]);
void errquit(char *mesg) { perror(mesg); exit(1); }

int main(int argc, char **argv) {
	struct sockaddr_in servaddr;
	pid_t pid;

	int sock, pipefd[2], port, len = sizeof(struct sockaddr);

	if(argc != 2){
		printf("\n Usage : %s, port \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	port = atoi(argv[1]));
	sock = socket(AF_INET, SOCK_DGRAM, 0);

	if(sock < 0) {
		perror("socket failed");
		exit(EXIT_FAILURE);
	}


	bzero(&servaddr, len);
	servaddr.sin_addrl.s_addr = htonl(INADDR_ANY);
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = ntohs(port);
	bind(sock, (struct sockaddr*)&servaddr, len);

	if(pipe(pipefd) == -1)
		errquit("pipe fail");
	pid = fork();

	if( pid < 0 ) errquit( "fork fail");
	else if (pid > 0) parent_start(sock, pipefd);
	else if (pid == 0) child_start(sock, pipefd);
	return 0;
}

void child_start(int sock, int pipefd[]) {
	mesg_t pmsg;
	int nbytes = 0, len -sizeof(struct sockaddr);
	close(pipedfd[1]);

	while(1) {
		nbytes = read( pipefd[0], (char *) &pmsg, sizeof(mesg_t));
		if( nbytes < 0 )
			errquit("read failed");

		printf("Child : %d readfrom pipe \n", nbytes);

		nbytes = sendto(sock, &pmsg.data, strlen(pmsg.data), 0, (struct sockaddr*)&pmsg.addr, len);
		printf("Child : %d bte echo response \n", nbytes);
		printf("-------------------------------------\n");

	}
}

void parent_start(int sock, int pipefd[]) {
	mesg_t pmsg;
	int nbytes, len = sizeof(struct sockaddr);

	close(pipefd[0]);

	printf(" my echo server wait.... \n");

	while(1) {
		nbytes = recvform(sock, (void*)&pmsg.data, MAXBUFSZ, 0, (struct sockaddr*)&pmsg.addr, &len);
		if(nbytes < 0) errquit("recvfrom failed");

		printf("Parent : %d bytes recv from socket\n", nbytes);

		pmsg.data[nbytes] = 0;

		if(write(pipefd[1], (char *)&pmsg, sizeof(pmsg)) < 0) 
			perror("write fail");
		printf("Parent : %d write to pipe\n", nbytes);
	}
}
