/************************************************
 * Purpose: print multiplication table
 * ********************************************/

#include <stdio.h>

int i,j; // index to iterate through the multiplication table

int main() {
	for (i=1; i<=9; ++i) {
		for (j=1; j<=i; ++j) {
			printf("%d x %d = %2d   ", i, j, i*j);
		}
		printf("\n");
	}
	return 0;
}

