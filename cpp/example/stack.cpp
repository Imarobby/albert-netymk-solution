/************************************************
 * Program: stack
 * Purpose: to illustrate the use of call in cpp
 *  ************************************************/

#include <iostream>
using namespace std;

const int SIZE = 10;

class stack {
	private:
		int data[SIZE];
		int index;
	public:
		// constructor
		stack() {
			index=-1;
		}

		// copy constructor
		stack(const stack &old) {
			int i;
			for(i=0; i<old.index; ++i) {
				data[i] = old.data[i];
			}
			index = old.index;
		}

		// destructor
		~stack() {}

		void push(int element) {
			++index;
			data[index] = element;
		}

		int pop(void) {
			--index;
			return data[index+1];
		}
};

int main() {
	stack one_stack;

	one_stack.push(1);
	one_stack.push(2);
	one_stack.push(3);

	cout << "Expect 3, and the result is " << one_stack.pop() << ".\n";
	cout << "Expect 2, and the result is " << one_stack.pop() << ".\n";
	cout << "Expect 1, and the result is " << one_stack.pop() << ".\n";

	return 0;
}
