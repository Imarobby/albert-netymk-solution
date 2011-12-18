#include "sound.h"

void setFrequency(Sound *self, int freq)
{
	self->f = freq;
}

void setStatus(Sound *self, int status)
{
	self->status = status;
}

void play(Sound *self, int freq) 
{
	self->status = 1;
	self->f = freq;
	ASYNC(self, playRecursion, 1);
}

/**
 * The only difference between the hacked version and the normal one
 * is that this method will hog the thread "forever", for it has
 * higher priority.
 * Putting the recursion call out of the if condition will ensure that
 * there will always be one message in the queue, which further
 * ensures that this method will get the thread before the prime
 * method.
 */
void playHacked(Sound *self, int state)
{
	if(self->status) {
		if(state) {
			ASYNC(self->p, turnOn, 0);
		} else {
			ASYNC(self->p, turnOff, 0);
		}
	}
	// Use deadline to increase the priority.
	// AFTER(RESOLUTION(31250/(self->f))/2, self, playHacked, !state);
	WITHIN(RESOLUTION(31250/(self->f)), MSEC(10), self, 
			playHacked, !state);
}

void playRecursion(Sound *self, int state)
{
	if(self->status) {
		if(state) {
			ASYNC(self->p, turnOn, 0);
		} else {
			ASYNC(self->p, turnOff, 0);
		}
		WITHIN(RESOLUTION(31250/(self->f)), MSEC(10), 
				self, playRecursion, !state);
	}
}
