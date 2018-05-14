#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>

int main(int argc, char ** argv) {

	struct sockaddr_in peer;
	int sock;
	int nbytes;
	char buf[512];

	if(argc != 3) {
		printf("Usage : %d ip port\n", argv[0]);
		exit(1);
	}


	sock = socket(AF_INET, SOCK_DGRAM, 0);
	bzero(&peer, sizeof(struct sockaddr);
	peer.sin_family = AF_INET;
	peer.sin_port = htons(atoi(argv[2]));
	peer.sin_addr.s_addr = inet_addr(argv[1]);

	while ( fgets(buf, sizeof(buf, strlen, stdin) != NULL) {


	}

	close(sock);
	exit(0);
}
