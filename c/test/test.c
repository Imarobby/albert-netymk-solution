#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

typedef struct char_print_struct {
	char character;
	int count;
} char_print;

void* print_char(void* argv)
{
	int i;
	char_print *p = argv;
	for(i=0; i<p->count; ++i) {
		fputc(p->character, stdout);
	}
	fputc('\n', stdout);

	return NULL;
}

int main() {
	pthread_t thread1;
	pthread_t thread2;
	char_print *thread1_argv, *thread2_argv;
	thread1_argv = malloc(sizeof(char_print));
	thread2_argv = malloc(sizeof(char_print));

	thread1_argv->character = 'x';
	thread1_argv->count = 20;
	pthread_create(&thread1, NULL, &print_char, thread1_argv);

	thread2_argv->character = 'x';
	thread2_argv->count = 10;
	pthread_create(&thread2, NULL, &print_char, thread2_argv);

	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);

	free(thread1_argv);
	free(thread2_argv);

	return 0;
}
