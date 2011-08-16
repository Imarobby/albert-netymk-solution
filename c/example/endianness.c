/**
 * Program: endianness.c
 * Purpose: To illustrate how little endianness works.
 */
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

	// suppose the larger address is the on the left, and lower
	// address is on the right
	// lowest address is least significant
void test1()
{
	int x = 65;
	// pointer points the rightest byte in less endianness machine
	int *pointer = &x;

	printf("The first is %d.\n", (*pointer >> 24) & 0xFF);
	printf("The second is %d.\n", (*pointer >> 16) & 0xFF);
	printf("The forth is %d.\n", (*pointer >> 8) & 0xFF);
	// get it
	printf("The fifth is %d.\n", (*pointer >> 0) & 0xFF);

	// get it
	printf("The first is %c.\n", *((char *)pointer+0));
	printf("The second is %c.\n", *((char *)pointer+1));
	printf("The third is %c.\n", *((char *)pointer+2));
	printf("The forth is %c.\n", *((char *)pointer+3));

}

void test2()
{
	int a[5];
	a[3] = 65;
	printf("This happens in stack.\n");
	// a is in stack, which grows from high address to low address
	// this will print the character, whose ascii is 65
	printf("The first byte of a[3] is %c.\n", ((char *)a)[12]);
	printf("The second byte of a[3] is %c.\n", ((char *)a)[13]);
	printf("The third byte of a[3] is %c.\n", ((char *)a)[14]);
	printf("The forth byte of a[3] is %c.\n", ((char *)a)[15]);

	printf("This happens in heap.\n");
	short *b = malloc(sizeof(short));
	assert(b!=NULL);
	*b = 65;
	// this will print the character, whose ascii is 65
	printf("The first byte of b[0] is %c.\n", ((char *)b)[0]);
	printf("The second byte of b[1] is %c.\n", ((char *)b)[1]);

}
int main() {
	printf("*******************test1*************************.\n");
	test1();
	printf("*******************test2*************************.\n");
	test2();

	return 0;
}
