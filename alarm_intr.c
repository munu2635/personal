#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <errno.h>

void catch(int sig) {
	printf("time out\n");
	return;
}

int main(int argc, char *argv[]) {
	int n;
	char buf[128];
	struct sigaction a;

	a.sa_handler = catch;
	a.sa_flags = 0;

	sigfillset(&a.sa_mask);
	sigaction(SIGALRM, &a, NULL);
	alarm(5);

	puts("input or 5 second wait");

	n = read(0, buf, sizeof(buf));
	if( n >= 0 )
		printf("%d byte read success\n", n);
	else if( errno == EINTR )
		printf("read() interrupted\n");
	else
		printf("read fail");
	alarm(0);
	return 0;
}
