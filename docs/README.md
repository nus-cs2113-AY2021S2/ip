# User Guide

TaskTracker is a **desktop app for tracking daily task, optimized for use 
via a Command Line Interface (CLI)**. If you can type fast, TaskTracker 
can get your task schedule faster than other task tracking application. 

## Features 
* Adding a task: `todo`, `deadline`, `event`
* Listing all the task: `list`
* Mark a task as done: `done`
* Locating the tasks by a keyword: `find`
* Deleting a task: `delete`
* Exiting the application: `bye`
* Loading the data
* Saving the data

## Adding a task: `todo, deadline, event`
Adds a task based on the type of task into the task list and return 
the type of task follow by the number of tasks in the list.

Format`todo` : `todo /description`\
Format`deadline` : `deadline /description /by date`\
Format`event` : `event /description /at date` 

### Usage

#### `todo`, `deadline`, `event` - add the specific task into the task list

Example of usage:

1. `todo borrow book`
2. `deadline return book /by June 6th`
3. `event attend party /at Friday 4 to 6 pm`

Expected outcome:

1. `Got it. I've added this task:`\
   `[T][ ] borrow book`\
   `Now you have 1 tasks in the list.`


2. `Got it. I've added this task:`\
   `[D][ ] return book (by: June 6th)`\
   `Now you have 2 tasks in the list.`


3. `Got it. I've added this task:`\
   `[E][ ] attend party (at: Friday 4 to 6 pm)`\
   `Now you have 3 tasks in the list.`

## Listing all the task: `list`
Return the list of task with their task type in the list..

Format: `list`

### Usage
#### `list` - Shows the task in the list

Example of usage:

* `list`

Expected outcome:

* `Here are the task in your list:`\
  `1.[T][ ] borrow book`\
  `2.[D][ ] return book (by: June 6th)`\
  `3.[E][ ] attend party (at: Friday 4 to 6 pm)`
    

## Mark as done: `done`
Mark a specific task in the list as done and return a cross on the task.

Format: `done /number`

### Usage
#### `done` - Mark the task in the list as done

Example of usage:

* `done 1`

Expected outcome:

* `Nice! I've marked this as done:`\
  `1.[T][X] borrow book`

## Locating the tasks by a keyword: `find`
Find the list of item that contain the key word and return the item that contain it.

Format: `find /keyword`

### Usage
#### `find` - find the keyword entered by using in the task list

Example of usage:

* `find book`

Expected outcome:

* `Here are the matching tasks in your list:`\
  `1.[T][X] borrow book`\
  `2.[D][ ] return book (by: June 6th)`

## Deleting a task: `delete`
Delete a specific item in the list and return the number of item left in the list.

Format: `delete /number`

### Usage
#### `delete` - Delete a specific task in the list

Example of usage:

* `delete 2`

Expected outcome:

* `Noted! I've removed this task:`\
  `[D][ ] return book (by: June 6th)`\
  `Now you have 1 tasks in the list`

## Exiting the application: `bye`- Close the application
Exits the program.

Format: `bye`

### Usage
#### `bye` - Exits the program

Example of usage:

* `bye`

Expected outcome:

* `Exiting task tracker...`\
  `Good Bye. Hope to see you again soon!`
    
## Loading the data
Task tracker automatically load data from the hard disk.

If the file path does not exist, task tracker automatically create a directory
from the user directory.

## Saving the data
Task tracker automatically save data to the hard disk after 
any command that changes the data. There is no need to save manually.
