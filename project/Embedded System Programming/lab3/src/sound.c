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
 * is that this method will hog the thread forever, which eliminates
 * the unnecessary relinquishing and reassigning of this thread.
 */
void playHacked(Sound *self, int state)
{
	if(self->status) {
		if(state) {
			BEFORE(RESOLUTION(31250/(self->f))/2, self->p, turnOn, 0);
		} else {
			BEFORE(RESOLUTION(31250/(self->f))/2, self->p, turnOff, 0);
		}
	}
	WITHIN(RESOLUTION(31250/(self->f)), MSEC(10), self, playHacked, !state);
}

void playRecursion(Sound *self, int state)
{
	if(self->status) {
		if(state) {
			BEFORE(RESOLUTION(31250/(self->f))/2, self->p, turnOn, 0);
		} else {
			BEFORE(RESOLUTION(31250/(self->f))/2, self->p, turnOff, 0);
		}
		WITHIN(RESOLUTION(31250/(self->f)), MSEC(10), self, playRecursion, !state);
	}
}
