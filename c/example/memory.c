/**
 * Program: memory.c
 * Purpose: to illustrate how stack and heap grow
 */
#include <stdio.h>
#include <stdlib.h>

int main() {
	int i,j;
	printf("for stack, address grows from high address to low address.\n");
	printf("%p\n", &i);
	printf("%p\n", &j);

	printf("for heap, address grows from low address to high address.\n");
	for(i=0; i<5; ++i) {
		printf("the content of %d is %p\n", i, malloc(8));
	}

	return 0;
}
