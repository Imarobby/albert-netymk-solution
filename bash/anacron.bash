#!/bin/bash -e
for user in $(/home/albertnet/workspace/bash/listusers.bash)
do
    daily=/home/$user/bin/daily.bash
    [ -x $daily ] && $daily
done
