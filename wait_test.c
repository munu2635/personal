#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

void catch_sigchld(int signo) { puts("###( parent) catch SIGCHLD");}

int chstat;

int main(int argc, char *argv[]) {
	int i, n;

	struct sigaction sact;
	sact.sa_flags = 0;
	sigemptyset(&sact.sa_mask);
	sigaddset(&sact.sa_mask, SIGCHLD);

	sact.sa_handler = SIG_DFL;
	//sact.sa_handler = catch_sigchld;
	//sact.sa_handler = SIG_IGN;
	sigaction(SIGCHLD, &sact, NULL);

	for(i=0; i<5; i++) {
		if(fork() == 0) {
			if(i>2) sleep(6);
			printf("(%d child), PID = %d, PPID = %d Exited\n", i, getpid(), getppid());
			exit(13);
		}
	}
	sleep(3);
	puts("------------------------------");
	system("ps -a");
	puts("------------------------------");


	puts("# (parent) wait ");
	for(;;) {
		chstat = -1;
		n = wait(&chstat);
		printf("# sait = %d(child stat = %d)\n", n, chstat);
		if( n == -1 ) {
			if(errno == ECHILD) {
				perror (" no waiting child process"); break;
			}
			else if (errno == EINTR) {
				perror (" wait system interupt"); continue;
			}
		}
	}
	puts("# ( parent ) end");
	return 0;
}
