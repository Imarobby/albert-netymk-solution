#include "primes.h"

static int is_prime(long number)
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

int primes(PrimeCalculator *self, int x)
{
	if(is_prime(x)) {
		ASYNC(self->lcd, writeInt, x);
	}
	AFTER(MSEC(200), self, primes, x+1);
}

