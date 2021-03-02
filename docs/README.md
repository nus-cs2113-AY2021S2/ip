# User Guide

## Features 

### Feature 1: Bye
Exits program

### Feature 2: Todo
Adds a todo task to the list

### Feature 3: Event
Adds an event task to the list

### Feature 4: Deadline
Adds a deadline task to the list

### Feature 5: List
Prints out all the tasks stored in the list

### Feature 6: Done
Marks a task in the list as completed

### Feature 7: Delete
Deletes a task from the list

### Feature 8: Find
Finds a task from the list that contains a certain keyword

## Usage

### `bye` - Exits program

An exit message will be printed before the program exits

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`



### `todo (description)` - Adds a todo task

The task added will be printed to show that the task has been added

Example of usage:

`todo sleep`

Expected outcome:

` Got it. I've added this task:`

`[T][✘] sleep`

`Now you have 1 tasks in the list.`


### `event (description) (/at time)` - Adds an event task

The task added will be printed to show that the task has been added

Example of usage:

`event sleep /at 9pm`

Expected outcome:

` Got it. I've added this task:`

`[E][✘] sleep  (at: 9pm)`

`Now you have 1 tasks in the list.`

### `deadline (description) (/by time)` - Adds a deadline task

The task added will be printed to show that the task has been added

Example of usage:

`deadline sleep /by 9pm`

Expected outcome:

` Got it. I've added this task:`

`[D][✘] sleep  (by: 9pm)`

`Now you have 1 tasks in the list.`


### `list` - Prints all the tasks

All the tasks in the list will be printed

Example of usage:

`list`

Expected outcome:

` Here are the tasks in your list:`

`1.[D][✘] sleep  (by: 9pm)`

### `done (task number)` - Marks a task as complete

The task that is marked as complete will be printed

Example of usage:

`done 1`

Expected outcome:

` Nice! I've marked this task as done:`

`[D][✓] sleep  (by: 9pm)`

### `delete (task number)` - Deletes a task

The tasks that is deleted will be printed

Example of usage:

`delete 1`

Expected outcome:

` Noted. I've removed this task:`

`[D][✓] sleep  (by: 9pm)`

`Now you have 0 tasks in the list.`

### `find (keyword)` - Finds tasks that contains keyword

All tasks that contains the keyword will be printed

Example of usage:

`find sleep`

Expected outcome:

` Here are the matching tasks in your list:`

`1.[D][✓] sleep  (by: 9pm)`