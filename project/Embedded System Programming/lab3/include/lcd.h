#include "tinytimber.h"

typedef struct {
	Object super;
} LCD;

#define initLCD() { initObject()}
int  writeDigit(LCD *self, int digitPos);
int  writeInt(LCD *self, int val);
int  segmentOn(LCD *self, int segment);
int  segmentOff(LCD *self, int segment);
