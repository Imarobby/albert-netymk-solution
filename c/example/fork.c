#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void child_process(void) {
	int i;
	for (i = 0; i <20; ++i) {
		printf("%d...\n", i);
		sleep(1);
	}
}

int main() {
	int pid, cid;

	pid = getpid();
	printf("Fork demo! I am the parent (pid = %d)\n", pid);

	if (0 == fork()) {
		cid = getpid();
		printf("I am the child (cid = %d) of (pid = %d)\n", cid, pid);
		child_process();
		exit(0);
	}
	printf("Parent waiting here for the child...\n");
	// If the following line is commented out, child process will be
	// reparent by init, who is then responsible for cleaning up after
	// the child process terminates.
	wait(NULL);
	printf("Child finished, parent quitting too!\n");

	return 0;
}
