#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>

#define PUSH 1

typedef struct {
	char robot;
	signed char position;
} unit;

int main(int argc, char* argv[]) {
	char line[1000];
	char *token;
	int size;
	int line_number;
	int steps;
	int previous_step;
	int o_position, b_position;
	// index for the collection
	int i;
	int tmp;

	unit *collection;

	char *inputFile = argv[1], *outputFile = argv[2];

	FILE *input_file = fopen(inputFile, "r");
	FILE *output_file = fopen(outputFile, "wt");
	assert(input_file != NULL);
	assert(output_file != NULL);

	// get the number of tests
	assert( fgets(line, sizeof(line), input_file) != NULL );

	line_number = 1;	
	while(1) {
		if( fgets(line, sizeof(line), input_file) == NULL ) {
			// finish reading
			break;
		}
		// initialization
		steps = 0;
		o_position = 1;
		b_position = 1;
		previous_step = 0;

		// number of buttons; size of collection
		token = strtok(line, " ");
		sscanf(token, "%d", &size);
		collection = malloc(size*sizeof(unit));
		assert(collection != NULL);

		// reading this line
		token = strtok(NULL, " ");
		i=0;
		while(token != NULL) {
			(collection+i)->robot = *token;
			token = strtok(NULL, " ");
			// sscanf(token, "%c", &((collection+i)->position));
			sscanf(token, "%d", &tmp);
			(collection+i)->position = (char)tmp;
			token = strtok(NULL, " ");
			i++;
		}
		/*
		for(i=0; i<size; ++i) {
			printf("robot is %c.\n", (collection+i)->robot);
			printf("position is %d.\n", (collection+i)->position);
		}
		*/
		// begin processing this line
		i = 0;
		while(i<size) {
			if(i<size && (collection+i)->robot == 'O') {
				// the first element of this potential sequence
				if(abs((collection+i)->position - o_position) >= previous_step) {
					previous_step = (abs((collection+i)->position - o_position) - previous_step) + PUSH;
				} else {
					previous_step = PUSH;
				}
				o_position = (collection+i)->position;
				i++;
				while(i<size && (collection+i)->robot == 'O') {
					previous_step += (abs((collection+i)->position-o_position)+PUSH);
					o_position = (collection+i)->position;
					i++;
				}
				steps += previous_step;
			}

			if(i<size && (collection+i)->robot == 'B') {
				if(abs((collection+i)->position - b_position) >= previous_step) {
					previous_step = (abs((collection+i)->position - b_position) - previous_step) + PUSH;
				} else {
					previous_step = PUSH;
				}
				b_position = (collection+i)->position;
				i++;
				while(i<size && (collection+i)->robot == 'B') {
					previous_step += (abs((collection+i)->position-b_position)+PUSH);
					b_position = (collection+i)->position;
					i++;
				}
				steps += previous_step;
			}
		}
		fprintf(output_file, "Case #%d: %d\n", line_number, steps);
		printf("Case %d finished.\n", line_number);
		line_number++;
		// clean up
		free(collection);
	}
	// clean up
	fclose(input_file);
	fclose(output_file);

	return 0;
}
