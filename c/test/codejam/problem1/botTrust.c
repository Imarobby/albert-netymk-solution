#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>

#define PUSH 1
int main(int argc, char *argv[]) {
	char line[1001];
	char *token;
	int line_number;
	int steps;
	int previous_step;
	// indicating who made the previous move
	char previous_robot;
	int o_position, b_position;
	int current;
	int number_test;

	char *inputFile = argv[1], *outputFile = argv[2];

	FILE *input_file = fopen(inputFile, "r");
	FILE *output_file = fopen(outputFile, "wt");
	assert(input_file != NULL);
	assert(output_file != NULL);

	// get the number of tests
	assert( fgets(line, sizeof(line), input_file) != NULL );
	sscanf(line, "%d", &number_test);

	line_number = 1;	
	while(1) {
		// initialization
		steps = 0;
		o_position = 1;
		b_position = 1;
		previous_step = 0;
		previous_robot = ' ';

		if( fgets(line, sizeof(line), input_file) == NULL ) {
			// finish reading
			break;
		}
		// number of buttons
		token = strtok(line, " ");
		token = strtok(NULL, " ");
		while(token != NULL) {
			switch(*token) {
				case 'O' :
					token = strtok(NULL, " \n");
					sscanf(token, "%d", &current);
					if(previous_robot == 'O') {
						// move and push
						previous_step += abs(current - o_position) + PUSH;
					} else{
						steps += previous_step;
						if(abs(current - o_position) > previous_step) {
							previous_step = (abs(current - o_position) - previous_step) + PUSH;
						} else {
							previous_step = 1;
						}
					}
					o_position = current;
					previous_robot = 'O';
					break;
				case 'B' :
					token = strtok(NULL, " \n");
					sscanf(token, "%d", &current);
					if(previous_robot == 'B') {
						// move and push
						previous_step += abs(current - b_position) + PUSH;
					} else {
						steps += previous_step;
						if(abs(current - b_position) > previous_step) {
							previous_step = (abs(current - b_position) - previous_step) + PUSH;
						} else {
							previous_step = 1;
						}
					}
					b_position = current;
					previous_robot = 'B';
					break;
			}
			token = strtok(NULL, " ");
		}
		steps += previous_step;
		fprintf(output_file, "Case #%d: %d\n", line_number, steps);
		printf("Case %d finished.\n", line_number);
		line_number++;
	}
	// clean up
	fclose(input_file);
	fclose(output_file);

	return 0;
}
