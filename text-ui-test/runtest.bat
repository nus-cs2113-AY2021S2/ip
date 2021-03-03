@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java ..\src\main\java\duke\task\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run multiple test cases
REM format (start, step, stop)
for /l %%x in (1, 1, 3) do (
    echo RUNNING TEST #%%x

    REM run the program, feed commands from input file and redirect the output to the ACTUAL.TXT
    java -classpath ..\bin duke.Duke < INPUT_%%x.TXT > ACTUAL.TXT

    REM compare the output to the expected output
    FC ACTUAL.TXT EXPECTED_%%x.TXT
)
