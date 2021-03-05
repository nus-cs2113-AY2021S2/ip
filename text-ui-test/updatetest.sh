#!/usr/bin/env bash

# remove data folder
if [ -e "./data" ]
then
    rm -rf ./data
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/duke -Xlint:none -d ../bin ../src/main/java/duke/*.java ../src/main/java/duke/tasks/*.java ../src/main/java/duke/commands/*.java ../src/main/java/duke/common/*.java ../src/main/java/duke/parser/*.java ../src/main/java/duke/storage/*.java ../src/main/java/duke/ui/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke/Duke < input.txt > EXPECTEDUPDATE.TXT
