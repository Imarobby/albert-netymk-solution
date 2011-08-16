/**
 * Program: unsignedInteger.c
 * Purpose: to illustrate the arithmetic of signed and unsigned
 * integer.
 */
#include <stdio.h>

int main() {
	unsigned int a = 1;
	printf("Without int casting: ");
	printf("%f\n", (a-2)*0.1);
	printf("With int casting: ");
	printf("%f\n", (int)(a-2)*0.1);
	return 0;
}
