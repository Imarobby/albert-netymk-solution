/************************************************
 * Program: fibonacci
 * Purpose: calculate the first x fibonacci numbers
 *************************************************/

#include <iostream>
using namespace std;

int main() {
	const int number = 10;	// the number of fibonacci numbers

	int previous=1;
	int current=1;
	int next;

	cout << 1 << " " << 1 << " ";
	int i;
	// we begin from the third number
	for(i=3; i<=number; ++i) {
		next = previous + current;
		cout << next << " ";
		previous = current;
		current = next;
	}
	cout << "\n";

	return 0;
}
