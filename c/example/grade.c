#include <stdio.h>

/************************************************
 * Purpose: convert scores to letter grades
 * negative input indicates the end of the input
 * ********************************************/

/*
 * Given one number, generate the corresponding character.
 */

char append(int number) {
	int modular = number % 10;
	if ( modular >= 1 && modular <= 3)
		return '-';
	if ( modular >= 4 && modular <= 7)
		return ' ';
	else 
		return '+';
}

float score; // the input score

int main() {
	while(1) {
		printf("Input the score: ");
		scanf("%f", &score);
		if (score < 0 )
			break;
		else {
			if ( score > 100 )
				printf("The range should be from 0 to 100.\n");
			else if ( score >= 91 )
				printf("The grade is A%c.\n", append((int)score));
			else if ( score >= 81 )                      
				printf("The grade is B%c.\n", append((int)score));
			else if ( score >= 71 )                      
				printf("The grade is C%c.\n", append((int)score));
			else if ( score >= 61 )                      
				printf("The grade is D%c.\n", append((int)score));
			else 
				printf("The grade is F.\n");
		}

	}
	return 0;
}

