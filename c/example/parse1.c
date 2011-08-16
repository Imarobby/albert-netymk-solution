/************************************************
 * Purpose: To illustrate how to parse options.
 *
 * Usage: print [options] [file]
 *
 * Options:
 *  -v		Produce verbose messages
 *  -o<file> output to file
 *  -l<line> set the number of lines (default is 66)
 *  ********************************************/

#include <stdio.h>
#include <stdlib.h>

const char *program_name = "print"; // hold the name of this program
int verbose = 0; 					// set verbose to 0 defaultly
char *out_file = "print.out";		// the name of output file
int line_max = 66;					// number of lines

int main(int argc, char *argv[]) {

	void usage(void);
	void execute(char*);

	while (argc > 1 && argv[1][0] == '-') {
		switch (argv[1][1]) {
			case 'v' :
				// set verbose
				verbose = 1;
				break;
			case 'l' :
				// set the number of lines
				line_max = atoi(&argv[1][2]);
				break;
			case 'o' :
				out_file = &argv[1][2];
				break;
			default :
				// unknown options
				// output error and display the usage
				fprintf(stderr, "Unknown options %s\n", argv[1]);
				usage();
		}
		// change the loop variable
		--argc;
		++argv;
	}
	// now all the options have been processed
	// it is time to deal with the input files
	// the current options are program name, potential input file list
	
	// first check if we have any input files
	if ( argc == 1 ) {
		execute("print.in");		// we use the default input
	} else {
		while ( argc > 1 ) {
			execute(argv[1]);

			// change the loop variable
			--argc;
			++argv;
		}
	}
}
/*
 * execute: dummy body of this program
 *
 * param:
 * 	name: the name of the input file
 */

void execute(char *name) {
	printf("Verbose %d, output %s, line %d, input %s\n",
			verbose, out_file,line_max, name);
}

/*
 * usage: tell the user how to use this program and exit.
 * Used when user input some unknown options.
 */

void usage(void) {
	fprintf( stderr, "Usage is %s [option] [file]\n", program_name);
	fprintf( stderr, "Options\n");
	fprintf( stderr, "		-v			verbose\n");
	fprintf( stderr, "		-l<number>	Number of lines\n");
	fprintf( stderr, "		-o<name>	Set output filename\n");
	exit(8);
}
