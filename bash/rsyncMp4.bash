#!/bin/bash -e
rsync -rv --exclude="- ballet/" --exclude="- guozhuang" ~/Music /media/disk/music
