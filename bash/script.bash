# find all files, whose name follow the given pattern, and are
# accessed more recently then the given time
find . -type f -name "Flash*" -newerat 2011-04-18 2>/dev/null
ls -lh $(find . -type f -name "Flash*" -newerat 2011-04-18 2>/dev/null)
