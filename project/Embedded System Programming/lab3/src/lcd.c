#include "lcd.h"


int  writeDigit(LCD *self, int digitPos)
{
	writeChar(digitPos/10, digitPos%10);
	return 0;
}

int  writeInt(LCD *self, int val)
{
	writeLong(val);
	return 0;
}

int  segmentOn(LCD *self, int segment)
{
	LCDDR13 &= 0xfe;
}

int  segmentOff(LCD *self, int segment)
{
	LCDDR13 |= 0x01;
}
