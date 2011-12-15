#include "melody.h"

#define a 440
#define b 494
#define c 262
#define d 294
#define e 330
#define f 349
#define g 392
#define h 311

#define x 500
#define y 250
#define z 1250
#define SS 2000
#define t 1500
static void playDianaHackedRecursion(Melody *self, int index);
static void playDianaNonStopRecursion(Melody *self, int index);

static int freq[50] = {e, e, e, e, e, e, e, f, f, f,
	f, g, f, d, e, e, e, e, e, e,
	e, f, f, f, f, g, f, d, g, g,
	g, g, a, a, a, a, a, a, a, h, 
	h, h, c, c, a, a, g, h, d, c};

static int duration[50] = {x, x, x, x, x, y, z, x, x, x, 
	x, x, y, z, x, x, x, x, x, y,
	z, x, x, x, x, x, y, z, x, x, 
	x, x, x, y, z, x, x, x, x, x, 
	y, z, SS, SS, t, x, t, x, x, t};

/**
 * There is no stop between two tones.
 * This method exists only because of the limitation of the current
 * CPU. This architecture only supports 3 threads.
 */
void playDianaNonStop(Melody *self, int index)
{
	ASYNC(self->s, playRecursion, freq[index]);
	ASYNC(self, playDianaNonStopRecursion, index+1);
}

/**
 * This method works the best, for it's the hacked version. It takes
 * the advantage of this particular architecture.
 */
void playDianaHacked(Melody *self, int index)
{
	ASYNC(self->s, playHacked, freq[index]);
	ASYNC(self, playDianaHackedRecursion, index+1);
}

/**
 * This method is the one I like best. Unfortunately, it doesn't work
 * very good, for the limitation of this architecture.
 */
void playDianaRecommended(Melody *self, int index)
{
	if(index == 50) {
		index = 0;
	}
	BEFORE(MSEC(duration[index]), self->s, play, freq[index]);

	WITHIN(MSEC(duration[index]), MSEC(10), self->s, setStatus, 0);
	AFTER(MSEC(duration[index] + 10), self, playDianaRecommended, index+1);
}

static void playDianaNonStopRecursion(Melody *self, int index)
{
	if(index == 50) {
		index = 0;
	}
	// ASYNC(self->s, play, freq[index]);
	// BEFORE(MSEC(duration[index]), self->s, play, freq[index]);
	BEFORE(MSEC(duration[index]), self->s, setFrequency, freq[index]);

	// WITHIN(MSEC(duration[index]), MSEC(duration[index] + 10), self->s, setStatus, 0);
	// WITHIN(MSEC(duration[index]), MSEC(10), self->s, setStatus, 0);
	AFTER(MSEC(duration[index] + 10), self, playDianaRecommended, index+1);
}

static void playDianaHackedRecursion(Melody *self, int index)
{
	if(index == 50) {
		index = 0;
	}
	BEFORE(MSEC(duration[index]), self->s, setFrequency, freq[index]);
	BEFORE(MSEC(duration[index]), self->s, setStatus, 1);

	WITHIN(MSEC(duration[index]), MSEC(10), self->s, setStatus, 0);
	AFTER(MSEC(duration[index] + 10), self, playDianaHackedRecursion, index+1);
}

