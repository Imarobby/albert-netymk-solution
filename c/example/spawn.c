/**
 * Program: spawn.c
 * Purpose: To illustrate how to spawn one new process.
 */
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int spawn(char *program, char **arg_list)
{
	pid_t child_pid;
	child_pid = fork();
	if(child_pid != 0) {
		// parent process
		return child_pid;
	} else {
		execvp(program, arg_list);
		fprintf(stderr, "An error occurs in execvp.\n");
		abort();
	}
}

int main() {
	// When we type "ls" in one terminal, the shell set argv[0] to be
	// the name of the program, which is "ls" in this case. Now, we
	// are calling "ls" directly, which means we are responsible for
	// set the argv[0] argument.
	char *arg_list[] = { "ls", "-l", "/", NULL };

	spawn("ls", arg_list);

	printf("Done with main.\n");

	return 0;
}
