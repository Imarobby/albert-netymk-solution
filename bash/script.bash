# find all files, whose name follow the fiven pattern, and are
# accessed more recentely then the given time
find . -type f -name "Flash*" -newerat 2011-04-18 2>/dev/null
ls -lh $(find . -type f -name "Flash*" -newerat 2011-04-18 2>/dev/null)
