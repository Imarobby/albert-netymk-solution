/************************************************
 * Purpose: store one bitmap to one two-dimension array
 * to illustrate the use of bit operation
 *
 * Known issue:
 *
 * Changelog: replace the ceil call with the "?" operator
 * ********************************************/

#include <stdio.h>
#include <math.h>

#define X_SIZE 41
#define Y_SIZE 40

// 0x80 is 0b1000000
// set the bit to be 1
#define SET_BIT(X,Y) graphics[(X)/8][Y] |= (0x80 >> ((X)%8))
// set the bit to be 0
#define CLEAR_BIT(X,Y) graphics[(X)/8][Y] &= ~(0x80 >> ((X)%8))
// return 1 if this bit is 1, otherwise 0
#define TEST_BIT(X,Y) (graphics[(X)/8][Y] & (0x80>>((X)%8)))

// we construct one two-dimension array, with ceil(X_SIZE / 8) columns
// and Y_SIZE rows
char graphics[(X_SIZE / 8.0) == (X_SIZE / 8)? X_SIZE : X_SIZE/8+1][Y_SIZE]; // we pack 8 bits per byte

int main() {
	int loc;
	void print_graphics(void);
	
	printf("First time:\n");
	for (loc=0; loc<X_SIZE; ++loc) {
		SET_BIT(loc,loc);
	}
	print_graphics();

	printf("Second time:\n");
	for (loc=0; loc<X_SIZE; ++loc) {
		CLEAR_BIT(loc,loc);
	}
	print_graphics();

	printf("The size of the graphics is %d x %d.\n", X_SIZE, Y_SIZE);
	printf("The storage we allocate to the graphics is %d x %d.\n", sizeof(graphics)/Y_SIZE, Y_SIZE);
	return 0;
}

void print_graphics(void){
	int x,y;

	for (y=0; y<Y_SIZE; ++y) {
		for (x=0; x<X_SIZE; ++x) {
			if (TEST_BIT(x,y))
				printf("X");
			else
				printf(".");
		}
		printf("\n");
	}
}
