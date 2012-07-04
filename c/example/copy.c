/***********************************************
 * Purpose: to copy the content of one file to the other
 * tags: file IO
 *
 * Bug:		The destination must exist
 * ********************************************/

#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 100

int main(int argc, char *argv[]) {
    char buffer[BUFFER_SIZE];
    FILE *in_file, *out_file;
    char *result;

    if ( argc != 3 ) {
	fprintf(stderr, "Error: Not enough arguments.\n");
	exit(8);
    }
    // open input file
    in_file = fopen(argv[1], "r");
    if ( in_file == NULL ) {
	fprintf(stderr, "Error: Can not open %s.\n", argv[1]);
	exit(8);
    }

    // open output file
    out_file = fopen(argv[2], "w");
    if ( out_file == NULL ) {
	fprintf(stderr, "Error: Can not open %s.\n", argv[2]);
	exit(8);
    }

    while (1) {
	result = fgets(buffer, sizeof(buffer), in_file);

	if ( result == NULL ) {
	    // EOF or error
	    if (!feof(in_file)) {
		fprintf(stderr, "Error: Can not read.\n");
		exit(8);
	    }
	    // EOF
	    break;
	}

	fputs(buffer, out_file);
    }

    // clean up
    fclose(in_file);
    fclose(out_file);

    return 0;
}
