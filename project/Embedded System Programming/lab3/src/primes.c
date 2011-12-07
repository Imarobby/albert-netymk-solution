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
	/*
	for(long i=2; i<99999999; ++i) {
		if(is_prime(i)) {
			SYNC(self->lcd, writeInt, i);
		}
	}
	*/
	if(is_prime(x)) {
		SYNC(self->lcd, writeInt, x);
	}
	// ASYNC(self, primes, x+1);
	AFTER(MSEC(200), self, primes, x+1);
}

