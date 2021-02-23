#!/bin/bash
if [[ ! -d javadoc ]]; then
	mkdir javadoc
fi
find src/main/java -name "*.java" > sources.txt
javadoc -d javadoc @sources.txt
rm sources.txt
