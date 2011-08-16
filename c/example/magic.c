/*
 * Program; magic.c
 * Purpose: Using Siamese method to construct one magic square of
 *		odd order.
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int size;					// the size of the magic square
int *square;

int main() {
	char line[10];				// put user's input in this temporary area
	int i,j;

	// sum of each row, column or diagonal to check the validity
	int total;

	// get the value for size from the user
	printf("Please input the dimension of the magic square:\n");
	fgets(line, sizeof(line), stdin);
	sscanf(line, "%d ", &size);

	// function declaration
	void init(int value);
	void fill(int x, int y);
int * access(int x, int y);
	// initialization
	init(0);

	// construct the magic square
	fill(0,size/2);

	// print out
	for(i=0;i<size;++i) {
		for(j=0;j<size;++j) {
			printf("%d ", *access(i, j));
		}
		printf("\n");
	}

	// row test
	for(i=0;i<size;++i) {
		total = 0;
		for(j=0;j<size;++j) {
			total = total + *access(i, j);
		}
		printf("The sum of %d row is %d\n", i+1, total);
	}
	// column test
	for(i=0;i<size;++i) {
		total = 0;
		for(j=0;j<size;++j) {
			total = total + *access(j,i);
		}
		printf("The sum of %d column is %d\n", i+1, total);
	}

	return 0;
}
/*
 * allocate the storage according to size and
 * initialize the value of each element
 * value:		the initial value
 * global:
 *		size:	the size of the two-dimension square
 */
static void init(int value) {
	square = (int *)malloc(size * size);
	if(square == NULL) {
		fprintf(stderr, "Unable to obtain %d x %d byte memory.\n", size, size);
		exit(1);
	}

	for(int i=0; i<size; ++i) {
		for(int j=0; j<size; ++j) {
			*(square+i*size+j) = value;
		}
	}
}
static void fill(int x, int y) {
	// function declaration
	int * access(int x, int y);

	static int element = 1;

	if(element > size*size)
		return;			// out job is finished

	*access(x,y) = element;
	++element;

	if (*access(x-1,y+1) == 0)
		fill(x-1, y+1);
	else
		fill(x+1,y);
}
/*
 * the access to the square is achieved using access function
 * x, y:		the rwo and the column, starting from 0
 */
inline static int * access(int x, int y) {
	x = x % size;
	y = y % size;

	if(x < 0)
		x = x + size;
	if(y < 0)
		y = y + size;

	return square+x*size+y;
}
