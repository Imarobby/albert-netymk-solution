#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	char line[1000];
	int line_number;
	char *token;

	int size;
	int *pool;
	int group1_sum, group2_sum;
	int i,j;

	char *inputFile = argv[1], *outputFile = argv[2];
	FILE *input_file = fopen(inputFile, "r");
	FILE *output_file = fopen(outputFile, "wt");
	assert(input_file != NULL);
	assert(output_file != NULL);
	// get the number of tests
	assert( fgets(line, sizeof(line), input_file) != NULL );

	while(1) {
		// initialization
		group1_sum = 0;
		group2_sum = 0;
		pool = NULL;

		if( fgets(line, sizeof(line), input_file) == NULL ) {
			// finish reading
			break;
		}
		// reading this line
		token = strtok(line, " ");
		sscanf(token, "%d", &size);
		pool = malloc(size * sizeof(int));
		assert(pool != NULL);
		for(i=0; i<size; ++i) {
			token = strtok(NULL, " ");
			sscanf(token, "%d", pool+i);
		}
		// begining processing

		group1_sum = *pool;
		for(i=1; i<size; ++i) {
			group2_sum ^= (*(pool+i));
		}
		fprintf(output_file, "Case #%d: [", line_number);

		printf("Case %d finished.\n", line_number);
		line_number++;
		free(pool);
	}
	// clean up
	fclose(input_file);
	fclose(output_file);

	return 0;
}
