#!/bin/bash -e
# generate the partition table for sfdisk

# in Megabyte
first=20
#second=200

#partitionSizes=( $((1024*$first)) $((1024*$second)) NA ) ; # size in blocks, NA=unspecified
#partitionTypes=( 83 83 83 )               ; # see available types: sfdisk -T
#partitionFlags=( bootable NA NA )
partitionSizes=( $((1024*$first))  NA ) ; # size in blocks, NA=unspecified
partitionTypes=( 83 83 )               ; # see available types: sfdisk -T
partitionFlags=( bootable NA )
blockSize=1024
start=1

# get CHS of one hard drive

GetGeometry () {
	numberOfBlocks=`sfdisk -s $1`
	bytes=$(($numberOfBlocks * 1024))

	geometry=(`sfdisk -g $1`)
	cylinders=${geometry[1]}
	heads=${geometry[3]}
	sectors=${geometry[5]}

	cylinderSize=`echo "scale=4; $numberOfBlocks / $cylinders" | bc`
}


WritePartitionInfo () {
	partitionNumber=$(($index+1))

	partitionFlag=${partitionFlags[$index]}
	[ "$partitionFlag" != "NA" ] || partitionFlag=

	partitionType=${partitionTypes[$index]}
	[ "$partitionType" != "NA" ] || partitionType=

	if [ "${partitionSizes[$index]}" = "NA" ]; then
		cylindersNeeded=
	else
		cylindersNeeded=`echo "${partitionSizes[$index]} /  $cylinderSize " | bc`
	fi

	echo "${1}$partitionNumber: start=, size=$cylindersNeeded, Id=$partitionType, $partitionFlag" 
}


# ---------------------------------------------------------------------- #
# ------------------------       MAIN     ------------------------------ #
# ---------------------------------------------------------------------- #


[ $# -eq 1 ] || { echo "usage: partition.sh device (e.g. /dev/hda)"; exit 1;}
[ -b $1 ] || { echo "$1 not a block device"; exit 1;}
# remove the /dev/ pattern
outputFile=${1#/dev/}_proposed

GetGeometry $1

echo "bytes:     $bytes"
echo "blocks:    $numberOfBlocks"
echo "cylinders: $cylinders"
echo "heads:     $heads"
echo "sectors:   $sectors"

echo "unit: cylinders"

for index in `seq 0 $((${#partitionSizes[@]}-1))`
do
	WritePartitionInfo $1
done
