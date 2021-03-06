# Duke User Guide

Duke is a tool for users to keep track of their to-dos, deadlines and events. Users may create, delete, search and mark tasks as done.

## Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### On IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. 

## Functions

### Add a to-do task: todo

This adds a simple to-do task to the list.

Format: todo <TASK>

Example: todo read a book

### Add an event: event

This adds an event to the list.

Format: event <TASK> /at <DETAILS ABOUT THE DATE/TIME>

Example: event birthday party /at 31 dec 2021

### Add a task with deadline: deadline

This adds a task with a deadline to the list.

Format: deadline <TASK> /by <DETAILS ABOUT THE DATE/TIME>

Example: deadline essay report /by next monday

### Display list of tasks: list

This displays the list of tasks the user has added.

Format: list

Example: list

### Mark a task as done: done

This marks a task as done.

Format: done <TASK NUMBER>

Example: done 1

Note: <TASK NUMBER> can be determined by using the list command.

### Delete a task: delete

This deletes a task from the list.

Format: delete <TASK NUMBER>

Example: delete 1

Note: <TASK NUMBER> can be determined by using the list command.

### Find a task from list: find

This finds a task from the list using a keyword. If there are mutiple tasks that contain the keyword, all will be displayed.

Format: find <KEYWORD>

Example: find essay

### Add a to-do task: bye

This quits the program.

Format: bye

Example: bye

## Functions Summary

| Function  | Format | Example |
| ------------- | ------------- | ------------- |
| todo  | todo <TASK>  | todo read a book  |
| event  | event <TASK> /at <DETAILS ABOUT THE DATE/TIME>  | event birthday party /at 31 dec 2021  |
| deadline  | deadline <TASK> /by <DETAILS ABOUT THE DATE/TIME>  | deadline essay report /by next monday  |
| list  | list  | list  |
| done  | done <TASK NUMBER>  | done 1  |
| delete  | delete <TASK NUMBER>  | delete 1  |
| find  | find <KEYWORD>  | find essay  |
| bye  | bye  | bye  |
