/************************************************
 * Program: dec2bin
 * Purpose: to convert one decimal to binary
 *************************************************/

#include <stdio.h>
#include <stdlib.h>

// how many bytes are used for the binary
// each byte is used to express either 1 or 0
#define MAX 10


int i = -1;

int main() {
	char *buffer = malloc(10);

	int n;		// the decimal number
	char *result = malloc(MAX);

	// function declaration
	void dec2bin( int, char* );

	fgets(buffer, sizeof(buffer), stdin);
	// empty input
	if ( *buffer == '\n' ){
		fprintf(stderr, "Invalid input.\n");
		exit(8);
	}

	sscanf(buffer, "%d", &n);

	dec2bin(n, result );
	printf("The binary for %d is %s\n", n, result);
	return 0;
}


static void dec2bin( int n, char *result ) {
	if ( n == 1 ){
		i++;
		*(result+i) = '0' + 1;		// stop point for recursion
	} else {
		dec2bin( n/2, result );		// deal with the quotient
		i++;
		*(result+i) = '0' + n%2;	// deal with the remainder
	}
}
