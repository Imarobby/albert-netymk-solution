/************************************************
 * Program: biggest
 * Purpose: to illustrate the use of reference 
 *		by finding the biggest element in one array
 *************************************************/

#include <iostream>
using namespace std;

int main() {
	// function declaration
	int &biggest(int array[], int size);

	int array[] = {1, 2, 3, 4, 5};
	
	cout << "The biggest element is " << biggest(array, sizeof(array)/sizeof(int)) << ".\n";

	return 0;
}

const int &biggest(int array[], int size) {
	int i; 
	int biggest = 0;


	for(i=1; i< size; ++i) {
		if(array[biggest] < array[i])
			biggest = i;
	}

	return array[biggest];
}
