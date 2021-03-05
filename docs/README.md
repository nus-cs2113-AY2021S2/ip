# User Guide

Duke is a desktop app for managing your todos, events and deadlines, optimised for use via a Command Line Interface(CLI).
* [Features](#features)
    * [Adding todos, deadlines and events](#1-adding-todos-deadlines-and-events)
        * [Adding a todo](#adding-a-todo-todo)
        * [Adding a deadline](#adding-a-deadline-deadline)
        * [Adding an event](#adding-an-event-event)
    * [Deleting a task](#2-deleting-todos-deadlines-and-events-delete)
    * [Listing all tasks](#3-listing-all-tasks-list)
    * [Marking task as done](#4-marking-task-as-done-done)
    * [Seaching for specified keyword](#5-searching-for-specified-keyword-find)
    * [Exiting the program](#6-exiting-the-program-bye)
* [FAQ](#faq)

## Features

### 1. Adding todos, deadlines and events
Adding tasks.

### Usage

### Adding a todo: `todo`

Adds a todo into the task list

Format: `todo [task name]`

Example of usage:

`todo run 2.4 km`

Expected outcome:

```
-------------------------------------------
Got it. I've added this task:
  [T][ ] run 2.4 km
Now you have 1 tasks in the list.
-------------------------------------------
```

### Adding a deadline: `deadline`

Adds a task with a deadline into the task list

Format: `deadline [task name] /by [date/time]`

Example of usage:

`deadline run 2.4 km /by Friday`

Expected outcome:

```
-------------------------------------------
Got it. I've added this task:
  [D][ ] run 2.4 km  (by: Friday)
Now you have 2 tasks in the list.
-------------------------------------------
```

### Adding an event: `event`

Adds an event into the task list

Format: `event [event name] /at [date/time]`

Example of usage:

`event run 2.4 km /at Monday evening`

Expected outcome:

```
-------------------------------------------
Got it. I've added this task:
  [E][ ] run 2.4 km  (at: Monday Evening)
Now you have 3 tasks in the list.
-------------------------------------------
```


### 2. Deleting todos, deadlines and events: `delete`

Deletes a task at the given index

### Usage

Format: `delete [index]`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task
	[T][ ] run 2.4 km
Now you have 2 tasks in the list.
-------------------------------------------
```

### 3. Listing all tasks: `list`
For a quick overview on all added tasks, printed in chronological order.

### Usage

Example of usage:

`list`

Expected outcome:

```
-------------------------------------------
1. [D][ ] run 2.4 km  (by: Friday)
2. [E][ ] run 2.4 km  (at: Monday Evening)
-------------------------------------------
```


### 4. Marking task as done: `done`
To mar a task as completed

### Usage

Format: `done [index]`

Example of usage:

`done 1`

Expected outcome:

```
Nice! Following task is now marked as done:
[X] run 2.4 km  (by: Friday)
-------------------------------------------
```


### 5. Searching for specified keyword: `find`
Return all tasks with specified keyword

### Usage

Format: `find [keyword]`

Example of usage:

`find run`

Expected outcome:

```
Here are the matching tasks in your list:
-------------------------------------------
1. [D][X] run 2.4 km  (by: Friday)
2. [E][ ] run 2.4 km  (at: Monday Evening)
-------------------------------------------
```
or 

`find sleep`

Expected outcome: (If there is no tasks with the specified keyword.)

```
Here are the matching tasks in your list:
-------------------------------------------
No results.
-------------------------------------------
```


### 6. Exiting the program: `bye`
Ending the program

Format: `bye`

## FAQ
**Q:** How is my data stored?

**A:** Data or user's list of tasks are stored in a text file named `Duke.txt`. When the programs reboot, it will automatically load the data from this text file and save the latest state of textfile when the program exits. 