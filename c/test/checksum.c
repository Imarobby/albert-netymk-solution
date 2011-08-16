/*
 * Program: checksum.c
 * Purpose: to calculate some typical checksums
 */
#include <stdio.h>

int main() {
	int datasum = 0;
	int basic_checksum, ones_complement_checksum, twos_complement_checksum;
	int block[] = {0x07, 0x01, 0x20, 0x74, 0x65, 0x64, 0x2E};

	for(int i=0; i<sizeof(block)/sizeof(int); ++i)
		datasum += block[i];

	// get rid of the carries
	datasum &= 0xFF;
	basic_checksum = datasum;
	ones_complement_checksum = 0xFF & (~datasum);
	// negative integers are inmplemented using 2's complement
	twos_complement_checksum = 0xFF & (-datasum);

	printf("The basic checksum is %x.\n", basic_checksum);
	printf("The 1's complement checksum is %x.\n", ones_complement_checksum);
	printf("The 2's checksum is %x.\n", twos_complement_checksum);

	return 0;
}
