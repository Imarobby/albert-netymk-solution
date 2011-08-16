/************************************************
 * Purpose: binary search
 * Usage:	run it and input the number you want to find, or '-1' to
 * 			quit
 * File:	numbers.dat stores a pool of number in ascending order
 * ********************************************/

#include "fileRead.h"
#include <stdio.h>
#include <stdlib.h>

/*
 * This program reads data from numbers.dat, accepts input from user,
 * and try to find the number from the pool
 */
int main()
{
	// the volume of the number pool
	const int MAX = 1000;

	char *filename = "numbers.dat";

	// internal storage of the number pool
	int data[MAX];

	// for storing the line we get using fgets
	char line[10];
	int count;
	int target;

	// binary search
	int low, middle, high;

	// TODO parsing options

	count = fileRead(filename, data, MAX);
	if( count <= 0) {
		fprintf(stderr, "something is wrong with data reading.\n");
		exit(-1);
	}

	// we are finishing read data now
	// outer loop to accept the user input
	while(1) {
		printf("Please input one number\n");
		fgets(line, sizeof(line), stdin);
		sscanf(line, "%d", &target);
		if( target == -1 )
			break; // the user want to quit

		if ( target < data[0] || target > data[count-1] ) {
			printf("The range should be from %d to %d.\n", data[0], data[count-1]);
			continue;
		}

		// inner loop for binary search
		low = 0;
		high = count - 1;
		while(1) {
			middle = (low+high)/2;
			if( target == data[middle] ){
				printf("Target is found.\n");
				break;
			}
			if ( low > high ){
				printf("This should never happen. low is %d, and high is %d.\n", low, high);
				break;
			}

			if( low == high ){
				printf("This number is not in the pool.\n");
				break;
			}

			if( target > data[middle] )
				low = middle + 1;
			else
				high = middle - 1;
		}
	}

	return 0;
}
