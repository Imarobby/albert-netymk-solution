/************************************************
 * Purpose: Illustrate the use of POSIX C Library
 * to show the mode of files
 * ********************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <sys/stat.h>


#define DIRNAME "/"
#define bufsize 255

int main() {
	DIR *dirh;
	struct dirent *dirp;
	struct stat statbuf;

	char pathname[bufsize];
	char linkname[bufsize];

	if ((dirh = opendir(DIRNAME)) == NULL) {
		perror("opendir");
		exit(1);
	}

	for (dirp = readdir(dirh); dirp != NULL; dirp = readdir(dirh)) {
		if (strcmp(".", dirp->d_name) == 0 || strcmp("..", dirp->d_name) == 0) {
			continue;
		}

		if (strcmp("lost+found", dirp->d_name) == 0) {
			continue;
		}

		sprintf(pathname, "%s%s", DIRNAME, dirp->d_name);

		if (lstat(pathname, &statbuf) == -1) {
			perror("stat");
			continue;
		}

		// if we are here, we have found what we are after
		if (S_ISREG(statbuf.st_mode)) {
			printf("%s is a regular file\n", pathname);
		}

		if (S_ISDIR(statbuf.st_mode)) {
			printf("%s is a directory file\n", pathname);
		}

		if (S_ISLNK(statbuf.st_mode)) {
			memset(linkname, '\0', bufsize);
			readlink(pathname, linkname, bufsize);
			printf("%s is a link to %s\n", pathname, linkname);
		}

		printf("The mode of %s is %o\n\n", pathname, statbuf.st_mode & 07777);
	}

	// clean up
	closedir(dirh);

	return 0;
}
