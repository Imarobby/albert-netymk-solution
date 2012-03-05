/************************************************
 * Program: multicore.c
 * Purpose: To illustrate the pros and cons of multi-thread
 * programming.
 *
 * Description: If the running env has only one core, multi-thread
 * will degreate the program's performance. If the env has more than
 * one core, then proper implementation will increase the performance.
 * ************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/time.h>

#define MAX 10000
#define CPU 2
void child()
{
	long sum = 0;
	for(int i=0; i<MAX; ++i) {
		sum += i*5;
	}
	printf("Child terminateing with %ld\n", sum);
}

long timediff(struct timeval *start, struct timeval *end) {
	// return (end->tv_sec-start->tv_sec) * 1000000 + (long)(end->tv_usec-start->tv_usec);
	// return (end->tv_sec-start->tv_sec) * 1000000;
	// printf("start :%ld", start->tv_usec);
	// printf("end :%ld", end->tv_usec);
	// return (long)(end->tv_usec-start->tv_usec);
	return (end->tv_sec-start->tv_sec) * 1000000 + (long)(end->tv_usec-start->tv_usec);
}

int main() {
	struct timeval start, end;
	gettimeofday(&start, NULL);
	for(int i=0; i<CPU; ++i) {
		child();
	}
	gettimeofday(&end, NULL);
	printf("Without threads: ellapsed time is %ld\n", timediff(&start, &end));
	gettimeofday(&start, NULL);
	for(int i=0; i<CPU; ++i) {
		if(0 == fork()) {
			child();
			exit(0);
		}
	}
	for(int i=0; i<CPU; ++i) {
		wait(NULL);
	}
	gettimeofday(&end, NULL);
	printf("With threads: ellapsed time is %ld\n", timediff(&start, &end));
	return 0;
}
