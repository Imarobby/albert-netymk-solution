#include "primes.h"

int primes(PrimeCalculator *self, int x)
{
	for(long i=2; i<999999; ++i) {
		if(is_prime(i)) {
			ASYNC(self->lcd, writeInt, i);
		}
	}
}

