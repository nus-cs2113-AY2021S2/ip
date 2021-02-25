# User Guide

## Features

### Add a Task
Add a task to the task list.<br>
There are 3 types of tasks available - Todo, Deadline and Event.<br>
Deadline and Event tasks have a date and/or time tagged to them.

### List all Tasks
List all tasks in the task list in the order they were added.

### Mark Task as Done
Mark a task in the task list as done.

### Delete a Task
Delete a task from the task list.

### Find a Task
Search for all tasks containing a keyword in their description.<br>
List out all the relevant tasks.

### Save Current Tasks
Information on all tasks are saved automatically after any changes are made.

### Exit the Application
Exit from the application.

## Usage

<div markdown="block" class="alert alert-info">

:information_source: Words in `UPPER_CASE` are the parameters to be supplied by the user.
  
</div>

### Adding a Todo: `todo`

Adds a Todo task to the task list

Format: `todo TASK_DESCRIPTION`

Examples:
* `todo read book`
* `todo return book`

Expected Outcome:
```
------------------------------------------------------------
Got it. I've added this task
[T][N] read book
Now you have 1 task in the list
------------------------------------------------------------
```

### Adding a Deadline: `deadline`

Adds a Deadline task to the task list

Format: `deadline TASK_DESCRIPTION /by DATETIME`

Examples:
* `deadline return book /by June 6th`
* `deadline finish iP /by tonight`

Expected Outcome:
```
------------------------------------------------------------
Got it. I've added this task:
[D][N] return book (by: June 6th)
Now you have 2 tasks in the list
------------------------------------------------------------
```

### Adding an Event: `event`

Adds an Event task to the task list

Format: `event TASK_DESCRIPTION /at DATETIME`

Examples:
* `event project meeting /at Aug 6th 2-4pm`
* `event date ;-) /at 8am`

Expected Outcome:
```
------------------------------------------------------------
Got it. I've added this task:
[E][N] project meetin (at: Aug 6th 2-4pm)
Now you have 3 tasks in the list
------------------------------------------------------------
```

### Listing all Tasks: `list`

Lists out all tasks in the task list in the order they were added.

Format: `list`

Expected Outcome:
```
------------------------------------------------------------
Here are the tasks in your list:
1.[T][N] read book
2.[D][N] return book (by: June 6th)
3.[E][N] project meetin (at: Aug 6th 2-4pm)
------------------------------------------------------------
```

### Marking a Task as Done: `done`

Marks a task in the task list with the corresponding index as done.

Format: `done INDEX`

Examples:
* `done 1`
* `done 3`

Expected Outcome:
```
------------------------------------------------------------
Nice! I've marked this task as done:
[T][Y] read book
------------------------------------------------------------
```

### Deleting a Task: `delete`

Deletes a task from the task list at the specified index.

Format: `delete INDEX`

Example: `delete 1`

Expected Outcome:
```
------------------------------------------------------------
Noted. I've removed this task:
[T][Y] read book
Now you have 2 tasks in the list
------------------------------------------------------------
```

### Finding Tasks: `find`

Searches for all tasks containing a keyword in their description.<br>
Lists out all the relevant tasks.

Format: `find KEYWORD`

Example: `find book`

Expected Outcome:
```
------------------------------------------------------------
Here are the matching tasks in your list:
1.[D][N] return book (by: June 6th)
2.[T][N] read book
------------------------------------------------------------
```

### Exiting Duke: `bye`

Exits the application with a goodby message

Format: `bye`

Expected Outcome:
```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
