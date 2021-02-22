# Duke User Guide

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### On IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the output below.

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
### User guide
#### Features
1. **todo** --> to add a *todo* task into a task list, no date/time needed.
2. **event** --> to add a *event* task into a task list, date/time needs to be specified in the format of [event description]/at [date/time].
3. **deadline** --> to add a *deadline* task into a task list, date/time needs to be specified in the format of [deadline description]/by [date/time],
4. **delete** --> to delete a task in the task list that the user previously added in. Format: delete [task number].
5. **done** --> to mark a task as done, this will be reflected as a tick. Format: done [task number].
6. **list** --> to list out all available tasks that the user has added inside.
7. **find** --> to search for all tasks in the list and find the task that contains the user's input keyword. Format: find [keyword].
8. **bye** --> to end the program 

