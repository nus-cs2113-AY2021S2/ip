# User Guide

## Features 

### Add different Tasks to your list
add todos, events, or deadlines

## Usage

### `todo` - add a todo

Example of usage: 

`todo (name)`

Expected outcome:

`Got it. I've added this task:
(Task name)
Now you have (number) tasks in the list`

### `event` - add an event

Example of usage:

`event (name) /at (time)`

Expected outcome:

`Got it. I've added this task:
(Task name)
Now you have (number) tasks in the list`

### `deadline` - add a deadline

Example of usage:

`deadline (name) /by (time)`

Expected outcome:

`Got it. I've added this task:
(Task name)
Now you have (number) tasks in the list`

### `list` - lists all tasks entered

Example of usage:

`list`

Expected outcome:

`1. (Task) 
2.(task)
...`

### `done` - marks a task as complete

Example of usage:

`done (index)`

index is the position of the task in the list
as shown by the list command.

Expected outcome:

`Nice! I've marked this task as done:
(task)`

### `find` - find a task containing this word of phrase

Example of usage:

`find (string)`

Expected outcome:

`(list of tasks that match the word entered)`

### 'bye' - End the program

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
