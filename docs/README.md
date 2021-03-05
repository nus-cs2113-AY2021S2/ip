# User Guide
Duke is a task management application which is designed to handle 3 types of tasks: todo, event and deadline.
* [Features](#features)
  * [Adding a task](#1--Adding-a-task)
  * [Listing down all tasks](#2--Listing-down-all-tasks)
  * [Marking a task as done](#3--Marking-a-task-as-done)
  * [Deleting a task](#4--Deleting-a-task)
  * [Finding tasks contain keywords](#5--Finding-tasks-contain-keywords)
  * [Save and exit](#6--Save-and-exit)
## Features 

### 1. Adding a task.
Adding a task into the list.

There are 3 types of Task: Todo, Deadline and Event.

### Usage

#### `todo <task description>` - Adds a task that wants to be done into the task list.
Task type is represented by " [T] ".


Example of usage: 

`todo assignment1`

Expected outcome:

```
Got it. I've added this task:

[T][ ] assignment1

Now you have 1 tasks in the list.
```
<br/>

#### `deadline <deadline description> /by <time>` - Adds a task with a deadline into the task list.
Task type is represented by " [D] ".

Example of usage:

`deadline assignment2 /by Friday 5/3/2021`

Expected outcome:

```
Got it. I've added this task:

[D][ ] assignment2 (by: Friday 5/3/2021)

Now you have 2 tasks in the list.
```
<br/>

#### `event <event description> /at <time>` - Adds a task with an occurring time into the task list.
Task type is represented by " [E] ".

Example of usage:

`event submit assignment3 /at 6/3/2021`

Expected outcome:

```
Got it. I've added this task:

[E][ ] submit assignment3 (at: 6/3/2021)

Now you have 3 tasks in the list.
```
<br/>

### 2. Listing down all tasks.
show all tasks that have been added.
### Usage
#### `list` - display all tasks.
Example of usage:
`list`

Expected outcome:

```
Here are the tasks in your list:

1. [T][ ] assignment1

2. [D][ ] assignment2 (by: Friday 5/3/2021)

3. [E][ ] submit assignment3 (at: 6/3/2021)

```
<br/>

### 3. Marking a task as done.
"[X]" represents the completion of the task and "[ ]" represents the task is not completed.
### Usage
#### `done <task index>` - Marks the selected task as done.
Example of usage:
`done 1`

Expected outcome:
```
Nice! I've marked this task as done:

[T][X] assignment1

```
<br/>

### 4. Deleting a task.
Removes the task from the list.
### Usage
#### `delete <task index>` - Removes the task from the list.
Example of usage:
`delete 1`

Expected outcome:
```
Noted. I've removed this task: 

[T][X] assignment1

Now you have 2 tasks in the list.

```
<br/>

### 5. Finding tasks contain keywords.
Uses a keyword to find the tasks.
### Usage
#### `find <keyword>` - Lists down all tasks in the list contain a certain keyword.
Example of usage:
`find submit`

Expected outcome:
```
Here are the matching tasks in your list:

3. [E][ ] submit assignment3 (at: 6/3/2021)

```
<br/>

### 6. Save and exit.
Saves the list as a txt file in the local disk and exits the application.

### Usage
#### `bye` - Saves the progress and terminates the application.

Expected outcome:
```
 Bye. Hope to see you again soon!

```