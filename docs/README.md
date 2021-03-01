# User Guide

## Features 

### 1. Adding todos, deadlines and events
Duke allows the user to quickly track events and reminders through CLI

### Usage

### `todo`

Will add a todo into the task list

Example of usage: 

`todo [something you need to do e.g read book]`

Expected outcome:

```
Got it. I've added this task:
	[T][✘] read book
Now you have 8 tasks in the list.
```

### 2. Deleting todos, deadlines and events
Should a user want to remove a task, deletion can be done

### Usage

### `delete`

Will delete a specified task at the given index

Example of usage:

`delete [index based on list, e.g 4]`

Expected outcome:

`Done! One less worry for you :)`

### 3. Listing all tasks
For a quick overview on all added tasks

### Usage

### `list`

Will print all added tasks in chronological order

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:
	1. [T][✘] 1
	2. [T][✘] 3
	3. [E][✘] 4 (at: Wednesday)
	4. [D][✘] return book (by: June 6th)
	5. [E][✘] project meeting (at: Sunday)
	6. [T][✘] run
	7. [T][✘] study book
	8. [T][✘] read book`

### 4. Marking task as done
To signal that task is completed

### Usage

### `done`

Will mark task as completed at given index

Example of usage:

`done 4`

Expected outcome:

`Here are the tasks in your list:
	1. [T][✘] 1
	2. [T][✘] 3
	3. [E][✘] 4 (at: Wednesday)
	4. [D][✓] return book (by: June 6th)
	5. [E][✘] project meeting (at: Sunday)
	6. [T][✘] run
	7. [T][✘] study book
	8. [T][✘] read book`

### 5. Searching for specified keyword
Should a user want to search for related task, a keyword search is provided

### Usage

### `find`

Will search through task list for all tasks with specified keyword

Example of usage:

`find book`

Expected outcome:
`Here are the matching tasks in your list:
	1. [D][✓] return book (by: June 6th)
	2. [T][✘] study book
	3. [T][✓] read book`
