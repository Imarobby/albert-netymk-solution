#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>

typedef struct {
	char combine_characters[3];
} combine;
typedef struct {
	char opposed_characters[2];
} opposed;

int main(int argc, char *argv[]) {
	char line[1000];
	int line_number;
	char *token;

	int c, d;
	combine *combine_sequence;
	opposed *opposed_sequence;
	char *element_list;
	char *target;
	int length;
	// i is used to iterate through the original string
	// j is used to look up the combine characters
	// k is used to backtrace the opposed characters
	int i,j,k;
	// index is used to keep track of the target
	int index;
	int combine_test=0, opposed_test=0;

	char *inputFile = argv[1], *outputFile = argv[2];
	FILE *input_file = fopen(inputFile, "r");
	FILE *output_file = fopen(outputFile, "wt");
	assert(input_file != NULL);
	assert(output_file != NULL);
	// get the number of tests
	assert( fgets(line, sizeof(line), input_file) != NULL );

	// initialization
	line_number = 1;	
	while(1) {
		combine_sequence = NULL;
		opposed_sequence = NULL;
		element_list = NULL;
		target = NULL;
		if( fgets(line, sizeof(line), input_file) == NULL ) {
			// finish reading
			break;
		}
		// reading this line
		// number of combine
		token = strtok(line, " ");
		sscanf(token, "%d", &c);
		if(c > 0) {
			combine_sequence = malloc(c*sizeof(combine));
			assert(combine_sequence != NULL);
			for(i=0; i<c; ++i) {
				token = strtok(NULL, " ");
				strcpy((combine_sequence+i)->combine_characters,
						token);	
			}
		}
		// number of opposed
		token = strtok(NULL, " ");
		sscanf(token, "%d", &d);
		if(d > 0) {
			opposed_sequence = malloc(d*sizeof(opposed));
			assert(opposed_sequence != NULL);
			for(i=0; i<d; ++i) {
				token = strtok(NULL, " ");
				strcpy((opposed_sequence+i)->opposed_characters,
						token);	
			}
		}
		token = strtok(NULL, " ");
		sscanf(token, "%d", &length);
		element_list = malloc(length * sizeof(char));
		assert(element_list != NULL);
		token = strtok(NULL, " ");
		strcpy(element_list, token);	

		target = malloc(length * sizeof(char));
		assert(target != NULL);
		// memset(target, '\0', length);
		// index is pointing to the character under investigation
		// -1 indicates it is empty
		index = -1;

		// begin processing this line
		// for(i=0; i<length; ++i) {
		i=0;
		while(1) {
			/*
			   if(i==3) {
			   printf("Inside if: target is %s, i is %d, index is %d.\n", target, i, index);
			   }
			   */
			if(index == -1 || index == 0) {
				if(i<length) {
					target[++index] = element_list[i++];
					continue;
				} else {
					break;
				}
			}
			// at least two characters in target
			for(j=0; j<c; ++j) {
				// try to find combine character
				if((target[index] == (combine_sequence+j)->combine_characters[0])
						&& (target[index-1] == (combine_sequence+j)->combine_characters[1]))  {
					target[--index] = (combine_sequence+j)->combine_characters[2];
					combine_test = 1;
					break;
				} else if((target[index] == (combine_sequence+j) ->combine_characters[1])
						&& (target[index-1] == (combine_sequence+j) ->combine_characters[0]))  {
					target[--index] = (combine_sequence+j)->combine_characters[2];
					combine_test = 1;
					break;
				}
			}
			if(combine_test == 1) {
				combine_test = 0;
				continue;
			}
			for(j=0; j<d; ++j) {
				// try to find opposed character
				if(target[index] == (opposed_sequence+j)->opposed_characters[0]) {
					for(k=index-1; k>=0; --k) {
						if(target[k] == (opposed_sequence+j)->opposed_characters[1]) {
							index = k-1;
							break;
						}
					}
				} else if(target[index] == (opposed_sequence+j)->opposed_characters[1]) {
					for(k=index-1; k>=0; --k) {
						if(target[k] == (opposed_sequence+j)->opposed_characters[0]) {
							index = k-1;
							break;
						}
					}
				}
			}
			if(i>=length) {
				break;
			}
			target[++index] = element_list[i++];
		}

		fprintf(output_file, "Case #%d: [", line_number);
		for(i=0; i<=index; ++i) {
			fprintf(output_file, "%c, ", target[i]);
		}
		fprintf(output_file, "]\n"); 

		printf("Case %d finished.\n", line_number);
		line_number++;

		free(combine_sequence);
		free(opposed_sequence);
		free(element_list);
		free(target);
	}
	// clean up
	fclose(input_file);
	fclose(output_file);

	return 0;
	}
