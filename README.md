# Duke User Guide

Duke is a personal assistant that allows users to add, delete, check and find tasks. The tasks added will be saved to a text file named **testduke.txt**. 

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
# User guide
## Features
### **todo** 
#### to add a *todo* task into a task list, no date/time needed.
### **event** 
####to add a *event* task into a task list, date/time needs to be specified in the format of [event description]/at [date/time].
### **deadline** 
#### to add a *deadline* task into a task list, date/time needs to be specified in the format of [deadline description]/by [date/time],
### **delete** 
#### to delete a task in the task list that the user previously added in. Format: delete [task number].
### **done** 
#### to mark a task as done, this will be reflected as a tick. Format: done [task number].
### **list**
#### to list out all available tasks that the user has added inside.
### **find**
#### to search for all tasks in the list and find the task that contains the user's input keyword. Format: find [keyword].
### **bye**
#### to end the program 

