#ifndef _sound_h
#define _sound_h

#include "TinyTimber.h"
#include "piezo.h"

typedef struct {
	Object super;
	Piezo *p;
	int f;
} Sound;

#define initSound(p, f) { initObject(), p, f }

void changeFrequency(Sound *self, int freq);
void play(Sound *self, int nothing);
void setStatus(Sound *self, int status);
#endif
