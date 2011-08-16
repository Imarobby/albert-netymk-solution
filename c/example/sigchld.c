/**
 * Program: sigchld.c
 * Purpose: To illustrate how to clean up children asynchronously.
 */
#include <signal.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

sig_atomic_t child_exit_status;

void clean_up_child_process(int signal)
{
	int status;
	// The termination information is extracted by wait(), and the child process
	// is deleted.
	wait(&status);
	child_exit_status = status;
}

int main() {
	struct sigaction sigchld_action;
	memset(&sigchld_action, 0, sizeof(sigchld_action));
	sigchld_action.sa_handler = &clean_up_child_process;
	// SIGCHLD will be sent by the child, when it finishes execution.
	sigaction(SIGCHLD, &sigchld_action, NULL);

	return 0;
}
