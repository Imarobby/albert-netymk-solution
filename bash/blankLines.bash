# -0 to set the separator. 777 is invalid separator, which will
# undefine the $\, then the whole file is slurped into mem
# Delete the blank lines on the top
# This is hardly used, for the paragraph slurp mode will do the work.
#perl -0777 -i -pe 's!\A\n*!!' <filename>

# Delete the consecutive lines, and leave only the first two.
#perl -00 -i -pe '' <filename>

#perl -0777 -i -pe 's!\n{2,}\Z!\n!' <filename>

if [ $# -ne 1 ]; then
	echo "One argument, please";
	exit;
fi

target="$1";

find "$target" -name "*.h" -exec perl -00 -i -pe '' {} +
find "$target" -name "*.c" -exec perl -00 -i -pe '' {} +

find "$target" -name "*.h" -exec perl -0777 -i -pe 's!\n{2,}\Z!\n!' {} +
find "$target" -name "*.c" -exec perl -0777 -i -pe 's!\n{2,}\Z!\n!' {} +
