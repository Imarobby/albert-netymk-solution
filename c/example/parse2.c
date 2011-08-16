/************************************************
 * Purpose: Illustrate the parsing of options using
 * getopt_long
 * ********************************************/


#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>

const char *program_name;

int main(int argc, char *argv[]) {
	// function declaration
	void print_usage(FILE*, int);

	int next_option;
	int verbose;
	const char* output_file; 
	const char* const short_options = "ho:v";

	const struct option long_options[] = {
		{ "help", 		0, NULL, 'h'},
		{ "output", 	1, NULL, 'o'},
		{ "verbose", 	0, NULL, 'v'},
		{  NULL,	 	0, NULL,  0 } // required at the end of array
	};

	output_file = NULL;
	verbose = 0; 

	program_name = argv[0];

	next_option = getopt_long(argc, argv, short_options, long_options, NULL);

	while (next_option != -1) {
		switch (next_option) {
			case 'h':
				print_usage(stdout, 0);
			case 'o':
				output_file = optarg;
				break;
			case 'v':
				verbose = 1;
				break;
			case '?':
				// unknown option
				print_usage(stderr, 1);
			default:
				abort();
		}
	next_option = getopt_long(argc, argv, short_options, long_options, NULL);
	}

	// main body this program
	
	return 0;
}

void print_usage(FILE* stream, int exit_code) {
	fprintf (stream, "Usage:...");
	exit(exit_code);
}

