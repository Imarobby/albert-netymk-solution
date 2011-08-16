#!/bin/bash
# generate sql query that indicates that NEWWHLOCCODE is one among the pattern 4 letters and seven digits and OLDWHLOCCODE is not one among the pattern
# four letters and seven digits or LU series.

#echo "select UNITCODE, UBY, UDATE, OLDWHCODE, NEWWHCODE, OLDWHLOCCODE, NEWWHLOCCODE, ACTCODE  from hylte  WHERE (" > old.sql
#
#for i in `seq 1 319`
#do
#	echo " NEWWHLOCCODE = \"$(grep -E -m $i ^[A-Z]{4}[0-9]{7} /home/albertnet/Downloads/thesis/material/data/analysis/OLDWHLOCCODE | tail -n 1)\" or" >>old.sql;
#done
#
#echo ") and (" >> old.sql;
#
#for i in `seq 1 9`
#do
#	echo " OLDWHLOCCODE != \"LU0$i\" and" >> old.sql; 
#done
#
#echo " OLDWHLOCCODE != \"LU10\" );" >> old.sql;

# generate sql query to all the data ordered by UNITCODE and Udate. 

for i in `seq 400 200 10000`
do 
	echo "select UNITCODE, UBY, UDATE, OLDWHLOCCODE, NEWWHLOCCODE, ACTCODE, OLDORDECODE, NEWORDECODE from hylte order by UNITCODE, UDATE limit $i, 200" >> sequence.sql;
done
