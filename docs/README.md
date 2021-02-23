# Track-The-Mushroom User Guide

By: `Lee Han Yong Andy` Since: `February 2021`

![logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1LaKa9rD5VVLnenXNNu_K41jslGmqFI6amA&usqp=CAU)

- [Track-The-Mushroom User Guide](#track-the-mushroom-user-guide)
    * [1. Introduction](#1-introduction)
    * [2. Quick Start in Intellij](#2-quick-start-in-intellij)
    * [3. Features](#3-features)
      + [3.1 Add a Todo task: `todo`](#31-add-a-todo-task-todo)
      + [3.2 Add a Deadline task: `deadline`](#32-add-a-deadline-task-deadline)
      + [3.3 Add an Event task: `event`](#33-add-an-event-task-event)
      + [3.4 List all the tasks: `list`](#34-list-all-the-tasks-list)
      + [3.5 Complete a task: `done`](#35-complete-a-task-done)
      + [3.6 Delete a task: `delete`](#36-delete-a-task-delete)
      + [3.7 Search task by keyword: `find`](#37-search-task-by-keyword-find)
      + [3.8 Exit the program: `bye`](#38-exit-the-program-bye)
      + [3.9 Task Storage](#39-task-storage)
    * [4. Frequently Asked Question (FAQ)](#4-faq)
    * [5. Command Summary](#5-command-summary)

## 1. Introduction 

Track-The-Mushroom is a beginner-friendly console application to manage all your track.

## 2. Quick Start in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
2. Set up the correct JDK version, as follows:
    1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
    2. If JDK 11 is listed in the drop-down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
    3. Click `OK`
3. Import the project into Intellij as follows:
    1. Click `Open or Import`.
    2. Select the project directory, and click `OK`
    3. If there are any further prompts, accept the defaults.
4. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see a welcome message:

```
Welcome to Track-The-Mushroom!

Mushroom Tracker is here to serve you!
```

## 3. Features

### 3.1 Add a Todo task: `todo`

This operation will add a Todo task to the task list.

Format: `todo <Task Description>`

Example: `todo Grow Mushroom`

Expected Output:
```
Got it. I've added this task:
[T][ ] Grow Mushroom
Now you have 1 tasks in the list.
```
### 3.2 Add a Deadline task: `deadline`

This operation will add a Deadline task to the task list.

Format: `deadline <Task Description> /by <yyyy/MM/dd>`

Example: `deadline Plant Mushroom /by 2020-02-25`

Expected Output:
```
Got it. I've added this task:
[D][ ] Plant Mushroom (by: 2020-02-25)
Now you have 2 tasks in the list.
```

### 3.3 Add an Event task: `Event`

This operation will add an Event task to the task list.

Format: `event <Task Description> /at <Date or Day>`

Example: `event Mushroom Concert /at Sunday`

Expected Output:
```
Got it. I've added this task:
[E][ ] Mushroom Concert (at: Sunday)
Now you have 3 tasks in the list.
```

### 3.4 List all the tasks: `list`

This operation will list all the tasks found in the task list.

Format: `list`

Expected Output:
```
Below are all your task:
1.[T][ ] Grow Mushroom
2.[D][ ] Plant Mushroom (by: 2020-02-25)
3.[E][ ] Mushroom Concert (at: Sunday)
```

### 3.5 Complete a task: `done`

This operation will mark the task as completed.

Format: `done <Task Number>`

Example: `done 2`

Expected Output:
```
Nice! I've marked the task as done:
[D][X] Plant Mushroom (by: 2020-02-25)
```

### 3.6 Delete a task: `delete`

This operation will delete a task from the task list.

Format: `delete <Task Number>`

Example: `delete 2`

Expected Output:
```
Noted. I will remove this task:
[D][X] Plant Mushroom (by: 2020-02-25)
Now you have 2 tasks in the list.
```

### 3.7 Search task by keyword: `find`

This operation will search all the tasks with the keyword found in the task description.
The keyword is case insensitive.

Format: `find <keyword>`

Example: `find mushroom`

Expected Output:
```
Processing keyword searches, please wait.
1.[T][ ] Grow Mushroom
2.[E][ ] Mushroom Concert (at: Sunday)
Search is complete!
```

### 3.8 Exit the program: `bye`

This operation will exit the program.

Format: `bye`

Expected Output:
```
Goodbye! Hope to see you again soon!
```

### 3.9 Task Storage

#### 3.9.1 Automatically load data from an existing file to the program.

* Expected Outcome if successful:
```
Loading file...
Loading successful!
```
   
* Expected outcome if not successful:
     
```
Loading file...
Loading failed - Unable to detect file.
```
#### 3.9.2 Automatically save the current task list to a file.

* After entering `bye`, program will automatically store all the tasks into a task_logs.txt file.

## 4. FAQ

**Q1**: Where is the task_logs.txt stored after I exit the program?

> The file `task_logs.txt` is stored in the directory you ran the program in.

**Q2**: Do I need to create a task_logs.txt file?

> No. The `task_logs.txt` will be automatically created for you if you do not have the file.

## 5. Command Summary

Command | Format | Example |
------- | ------- | ------- | 
todo | `todo <Task Description>` | `todo Grow Mushroom` |
deadline | `deadline <Task Description> /by <yyyy/MM/dd>` | `deadline Plant Mushroom /by 2020-02-25` |
event | `event <Task Description> /at <Date or Day>`      | `event Mushroom Concert /at Sunday` |
list | `list` | `list` |
done | `done <Task Number>` | `done 2` |
delete | `delete <Task Number>` | `delete 2` |
find | `find <KeywordD>` | `find mushroom` |
bye | `bye` | `bye` |
