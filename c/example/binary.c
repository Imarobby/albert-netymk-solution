/************************************************
 * Purpose: to illustrate the use of binary I/O
 * ********************************************/


#include <stdio.h>
#include <stdlib.h>

struct rec {
	int x,y,z;
};

int main() {
	// basic variables
	int counter;
	FILE *ptr_myfile;
	struct rec my_record;

	// write binary data to test.txt
	ptr_myfile=fopen("test.txt","w");
	if (!ptr_myfile) {
		fprintf(stderr, "Unable to open file!");
		exit(8);
	}

	for ( counter=1; counter <= 10; counter++) {
		my_record.x= counter;
		fwrite(&my_record, sizeof(struct rec), 1, ptr_myfile);
	}
	fclose(ptr_myfile);

	// read binary data from test.txt
	ptr_myfile = fopen("test.txt", "r");
	if (!ptr_myfile) {
		fprintf(stderr, "Unable to open file!");
		exit(8);
	}

	for ( counter=1; counter <= 10; counter++) {
		fread(&my_record, sizeof(struct rec), 1, ptr_myfile);
		printf("%d\n", my_record.x);
	}
	fclose(ptr_myfile);

	// read binary data from test.txt using fseek
	ptr_myfile = fopen("test.txt", "r");
	if (!ptr_myfile) {
		fprintf(stderr, "Unable to open file!");
		exit(8);
	}

	for ( counter=9; counter >= 0; --counter) {
		fseek(ptr_myfile, sizeof(struct rec)*counter, SEEK_SET);
		fread(&my_record, sizeof(struct rec), 1, ptr_myfile);
		printf("%d\n", my_record.x);
	}
	fclose(ptr_myfile);

	return 0;
} 
