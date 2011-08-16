/************************************************
 * Purpose: to illustrate the use of 'static' on local variables
 * static is the default storage class for global variable
 * ********************************************/

#include <stdio.h>

// function declaration
void func1(void);

// this is the same to
// int count = 10;
static int count = 10;

int main() {
	while (count--)
		func1();
	return 0;
}

void func1(void) {
	static int i=5;
	int j=5;

	i++;
	j++;

	printf("i is %d.\n", i);
	printf("j is %d.\n", j);
}
