#include "primes.h"

static int is_prime(unsigned int number)
{
	if(number < 2) {
		return 0;
	}
	for (int i = 2; i < number; ++i) {
		if (number % i == 0) {
			return 0;
		}
	}
	return 1;
}

int primes(PrimeCalculator *self, unsigned int x)
{
	if(is_prime(x)) {
		// TODO if the prime number is calculating from 0, it goes
		// back to 0 after 170
		// SYNC(self->lcd, writeInt, x);
		ASYNC(self->lcd, writeInt, x);
	}
	AFTER(MSEC(100), self, primes, x+1);
}
