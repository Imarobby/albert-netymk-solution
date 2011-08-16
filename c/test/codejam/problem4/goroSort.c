#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>

void quickSort( int a[], int l, int r);
int partition( int a[], int l, int r);
int hits;
int main(int argc, char *argv[]) {
	char line[1000];
	int line_number;
	char *token;

	int *array;
	int size;
	int i;


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
		array = NULL;
		hits = 0;
		if( fgets(line, sizeof(line), input_file) == NULL ) {
			// finish reading
			break;
		}
		// reading this line
		token = strtok(line, " ");
		sscanf(token, "%d", &size);
		array = malloc(size*sizeof(int));
		assert(array != NULL);
		fgets(line, sizeof(line), input_file);
		token = strtok(line, " ");
		sscanf(token, "%d", array);
		for(i=1; i<size; ++i) {
			token = strtok(NULL, " ");
			sscanf(token, "%d", array+i);
		}
		quickSort(array, 0, size-1);

		fprintf(output_file, "Case #%d: %d\n", line_number, hits);

		printf("Case %d finished.\n", line_number);
		line_number++;

		free(array);
	}
	// clean up
	fclose(input_file);
	fclose(output_file);

	return 0;
}

void quickSort( int a[], int l, int r)
{
	int j;

	if( l < r ) {
		// divide and conquer
		j = partition( a, l, r);
		quickSort( a, l, j-1);
		quickSort( a, j+1, r);
	}

}

int partition( int a[], int l, int r)
{
	int pivot, i, j, t;
	pivot = a[l];
	i = l; j = r+1;

	while(1) {
		do ++i; while( a[i] <= pivot && i <= r );
		do --j; while( a[j] > pivot );
		if( i >= j ) break;
		t = a[i]; a[i] = a[j]; a[j] = t;
		hits+=2;
	}
	if(l != j) {
		t = a[l]; a[l] = a[j]; a[j] = t;
		hits+=2;
	}
	return j;
}
