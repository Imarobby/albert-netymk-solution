/*
 * Program: interpret.c
 * Purpose: to find out how computer interprets the binary value
 */
#include <stdio.h>

#define TEST_BIT(X) (a & (0x00000001<<(X)))

int main() {
	int a = -2;
	for(int i=sizeof(a)*8 - 1; i>=0; --i) {
		if(TEST_BIT(i))
			printf("1");
		else
			printf("0");
	}
	printf("\n");
	return 0;
}
