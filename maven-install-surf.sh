#!/bin/bash

mvn install:install-file -Dfile="ImageJ_SURF_2009-12-01_08.19.jar" -DgroupId=com.labun \
	-DartifactId=surf -Dversion="1.0" -Dpackaging=jar
