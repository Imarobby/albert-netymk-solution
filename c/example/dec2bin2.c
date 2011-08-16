/************************************************
 * Program: dec2bin2
 * Purpose: to convert one decimal to binary
 *
 * Algorithm: several bytes are concatenated, the right most bit is
 * used to indicate the most significant bit. The limiation is
 * determined by the recursion level and the number of bytes.
 *************************************************/


#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// how many bytes are used for the binary
// the binary is expressed as one string
#define MAX 2

// set the bit to be 1
#define SET_BIT(I) *(result+((I)/MAX)) |= (1 << ((I)%MAX))
// set the bit to be 0
#define CLEAR_BIT(I) *(result+((I)/MAX)) &= ~(1 << ((I)%MAX))
// return 1 if this bit is 1, otherwise 0
#define TEST_BIT(I) *(result+((I)/MAX)) & (1 << ((I)%MAX))

// index
int i = -1;
int main() {
	char *buffer = malloc(10);

	int n;		// the decimal number
	char *result = malloc(MAX);

	// function declaration
	void dec2bin( int, char* );
	void showbin( char* );

	fgets(buffer, sizeof(buffer), stdin);
	// empty input
	if ( *buffer == '\n' ){
		fprintf(stderr, "Invalid input.\n");
		exit(8);
	}

	sscanf(buffer, "%d", &n);

	dec2bin(n, result);
	showbin(result);
	return 0;
}


static void dec2bin( int n, char *result ) {
	if ( n == 0 ){
		return;							// stop point for recursion
	}
	else {
		dec2bin( n/2, result );			// deal with the quotient
		i++;							// move the index pointer
		if ( n%2 == 1 )
			SET_BIT(i);					// deal with the remainder
	}
}

static void showbin( char *result ) {
	for ( int j = 0; j <= i; ++j ){
		if ( TEST_BIT(j) )
			printf("%d", 1);
		else
			printf("%d", 0);
	}
	printf("\n");
}
