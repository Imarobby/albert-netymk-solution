/*
 * Program: gray
 * Purpose: to convert binary numbers to gray code
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
	char *binary[16];			// an array of pointers
	char *gray[16];

	char *buffer = malloc(5);
	int current;
	int i, j;

	// init
	for(i=0; i<16; ++i) {
		binary[i] = malloc(5);			// containing four bits
		gray[i] = malloc(5);
	}

	// construct the binary array
	for(i=0; i<16; ++i) {
		current = i;
		buffer[4] = '\0';
		for(j=3; j>=0; --j) {
			if(current % 2 == 0) {
				buffer[j] = '0';
			} else {
				buffer[j] = '1';
			}
			current /= 2;
		}
		strcpy(binary[i], buffer);
	}
	// construct the gray code array
	for(i=0; i<16; ++i) {
		// beginning from the right
		for(j=3; j>0; --j) {
			if(binary[i][j] == binary[i][j-1]) {
				buffer[j] = '0';
			} else {
				buffer[j] = '1';
			}
		}
		// add 0 to the most significant bit
		if(binary[i][0] == '0') {
			buffer[0] = '0';
		} else {
			buffer[0] = '1';
		}
		strcpy(gray[i], buffer);
	}
	// output
	for(i=0; i<16; ++i) {
		printf("%d\t%s\t%s\n", i, binary[i], gray[i]);
	}
	return 0;
}
