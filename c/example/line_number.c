/************************************************
 * Purpose: to count the number of newline in one
 * 			file
 * Note:	fopen, fclose are functions from c library
 * 			buffered I/O
 * ********************************************/

#include <stdio.h>

int main(int argc, char *argv[]) {
	FILE *in_file;

	int count = 0;		// to count the number of newline
	int ch;			// the character from input file
    // because we want to test whether it is EOF, we set the type
	// integer.


	in_file = fopen(argv[1], "r");

	// in case this file can not be opened
	if ( in_file == NULL ) {
		fprintf(stderr, "Error: Can not open %s.\n", argv[1]);
	}

	while(1) {
		ch = fgetc(in_file);

		if ( ch == EOF ) {
			break;			// EOF
		}
		
		if ( ch == '\n' ) {
			count++;
		}
	}

	printf("Number of lines in %s is %d\n", argv[1], count);

	// clean up
	fclose(in_file);

	return 0;
}
