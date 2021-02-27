#!/usr/bin/env bash
# use command "dos2unix runtest.sh" to convert file to unix if end of file not found


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

# delete previous arthur file if exists
if [ -e "./arthur.txt" ]
then
    rm arthur.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Arthur < input1.txt > ACTUAL.TXT

# convert to UNIX format
cp expected1.txt EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
    echo "Test 1 result: PASSED"
else
    echo "Test 2 result: FAILED"
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Arthur < input2.txt > ACTUAL.TXT

# convert to UNIX format
cp expected2.txt EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
    echo "Test 2 result: PASSED"
else
    echo "Test 2 result: FAILED"
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Arthur < input3.txt > ACTUAL.TXT

# convert to UNIX format
cp expected3.txt EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
    echo "Test 3 result: PASSED"
else
    echo "Test 3 result: FAILED"
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Arthur < input4.txt > ACTUAL.TXT

# convert to UNIX format
cp expected4.txt EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
    echo "Test 4 result: PASSED"
else
    echo "Test 4 result: FAILED"
fi
exit 0