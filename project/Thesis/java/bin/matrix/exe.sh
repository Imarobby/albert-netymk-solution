#!/bin/bash -e
cd ~/workspace/Thesis/src
javac -cp .:/home/albertnet/bin/*:/home/albertnet/workspace/Thesis/src matrix/DistanceMatrix.java -d /home/albertnet/workspace/Thesis/bin
java -cp .:/home/albertnet/bin/*:/home/albertnet/workspace/Thesis/bin matrix/DistanceMatrix
