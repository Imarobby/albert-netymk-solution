#include "fileRead.h"
#include <stdio.h>
#include <stdlib.h>

int fileRead(char *filename, int *data, int max)
{
	FILE *file = fopen( filename, "r" );
	char line[100];
	if ( file == NULL ) {
		fprintf(stderr, "can't open file.\n");
		return -1;
	}

	int count = 0;
	while(1) {
		if(count >= max) {
			fprintf(stderr, "did not finish reading due to the limit of MAX.\n");
			break;
		}
		if( (fgets(line, sizeof(line), file))== NULL ) {
			break; // we are finishing reading
		}
		printf("This line is %s", line);
		sscanf(line, "%d", &(data[count]));
		count++;
	}
	// clean up
	fclose(file);

	return count;
}
