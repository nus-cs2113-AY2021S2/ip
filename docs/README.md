# User Guide

## Introduction
This is project Duke, a program which helps you manage your tasks using Command Line Interface (CLI) which is simple to use and efficient.

## Features
1. [Adding a todo task](#1-todo---adds-a-todo-task)
2. [Adding an event](#2-event---adds-an-event)
3. [Adding a deadline](#3-deadline---adds-a-deadline)
4. [List out existing tasks](#4-list---list-out-all-existing-tasks)
5. [Mark task as done](#5-done---mark-a-specific-task-as-done)
6. [Delete task](#6-delete---deletes-a-specific-task-from-the-list-of-tasks)
7. [Find tasks](#7-find---searches-for-all-tasks-with-the-specified-keyword)
8. [Close program](#8-bye---terminates-the-program)
9. [Task storage](#9-task-storage)

### 1. `todo` - Adds a Todo task

Adds a todo task to the list of tasks.

Format: `todo <task description>`

Example of usage: `todo homework`

Expected Outcome: 
```
**********************************************************
[T][ ] homework
Now you have 1 tasks in the list.
**********************************************************
```

### 2. `event` - Adds an event

Adds an event to the list of tasks.

Format: `event <task description> /at <time>`

Example of usage: `event sister's wedding /at 10/3/2021 3pm`

Expected Outcome: 
```
**********************************************************
[E][ ] sister's wedding (at: 10/3/2021 3pm)
Now you have 2 tasks in the list.
**********************************************************
```

### 3. `deadline` - Adds a deadline

Adds a deadline to the list of tasks.

Format: `deadline <task description> /by <time>`

Example of usage: `deadline cs2113 iP /by 5/3/2021`

Expected Outcome: 
```
**********************************************************
[D][ ] cs2113 iP (by: 5/3/2021)
Now you have 3 tasks in the list.
**********************************************************
```

### 4. `list` - List out all existing tasks

List out all existing tasks in the task list regardless if they are done or not.

Format: `list`

Example of usage: `list`

Expected Outcome:
```
************************************************************
Here are the tasks in your list:
1. [T][ ] homework
2. [E][ ] sister's wedding (at: 10/3/2021 3pm)
3. [D][ ] cs2113 iP (by: 5/3/2021)
************************************************************
```

### 5. `done` - mark a specific task as done

mark a specific task with its index number in the task list as done.

Format: `done <index>`

Example of usage: `done 1`

Expected Outcome: 
```
**********************************************************
Awesome! I have marked this task as done: 
[T][x] homework
**********************************************************
```

### 6. `delete` - deletes a specific task from the list of tasks

delete a specific task using its index number in the task list.

Format: `delete <index>`

Example of usage: `delete 2`

Expected Outcome: 
```
************************************************************
Noted. I've removed this task:
[E][ ] sister's wedding (at: 10/3/2021 3pm)
Now you have 2 tasks in the list.
************************************************************
```

### 7. `find` - searches for all tasks with the specified keyword

searches for all the tasks which contains the keyword input by user.

Format: `find <keyword>`

Example of usage: `find homework`

Expected Outcome: 
```
************************************************************
Here are the matching tasks in your list:
1. [T][x] homework
************************************************************
```

### 8. `bye` - terminates the program

Shuts down the program.

Format: `bye`

Example of usage: `bye`

Expected Outcome:
```
############################################################
Bye. Hope to see you again soon!
############################################################
```

### 9. `Task Storage`
#### 9.1 Save list of tasks into a file
For every run of input, Duke will attempt to save what is in the task lists into the *tasks.txt* file automatically. No input is needed.

####9.2 Load list of tasks from a file
When Duke is started by the user, the list of tasks will be loaded from *tasks.txt* automatically. If it is the first time the user is using, a file called *tasks.txt* will be created instead.

##Notes
####1. All commands are case-sensitive and should be typed in lower cases.
####2. The date or time of deadlines and events do not need to follow a specific format.
####3. If commands are entered are invalid or in an invalid format, Duke will ignore the input and prompt user to enter again.