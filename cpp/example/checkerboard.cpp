/************************************************
 * Program: checkerboard
 * Purpose: print a checkerboard (x-by-y grid).
 *************************************************/

#include <iostream>
using namespace std;

int main() {
	const int x = 8;	// the number of grids in row dimension
	const int y = 8;	// the number of grids in column dimension

	int i,j;

		cout << "+";
		for(j=1; j<=y; ++j) {
			cout << "-----+";
		}
		cout << "\n";
	for(i=1; i<=x; ++i) {
		cout << "|";
		for(j=1; j<=y; ++j) {
			cout << "     |";
		}
		cout << "\n";
		cout << "|";
		for(j=1; j<=y; ++j) {
			cout << "     |";
		}
		cout << "\n";
		cout << "|";
		for(j=1; j<=y; ++j) {
			cout << "     |";
		}
		cout << "\n";

		cout << "+";
		for(j=1; j<=y; ++j) {
			cout << "-----+";
		}
		cout << "\n";
	}

	return 0;
}
