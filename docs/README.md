# User Guide

## Features 

### 1. Adding todos, deadlines and events
Duke allows the user to quickly track events and reminders through CLI

### Usage

### `todo`

Adds a todo into the task list

Format: `todo [task name]`

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
	[T][✘] read book
Now you have 8 tasks in the list.
```

### `deadline`

Adds a task with a deadline into the task list

Format: `deadline [task name] /by [date/time]`

Example of usage:

`deadline return book /by Sunday 7pm`

Expected outcome:

```
Got it. I've added this task:
	[D][✘] return book (by: Sunday 7pm)
Now you have 9 tasks in the list.
```

### `event`

Adds an event into the task list

Format: `event [event name] /at [date/time]`

Example of usage:

`event project meeting /at Monday 7-8pm`

Expected outcome:

```
Got it. I've added this task:
	[E][✘] project meeting (at: Monday 7-8pm)
Now you have 10 tasks in the list.
```

### 2. Deleting todos, deadlines and events
Should a user want to remove a task, deletion can be done

### Usage

### `delete`

Deletes a specified task at the given index

Format: `delete [index]`

Example of usage:

`delete 4`

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

```
Here are the tasks in your list:
	1. [T][✘] 1
	2. [T][✘] 3
	3. [E][✘] 4 (at: Wednesday)
	4. [D][✘] return book (by: June 6th)
	5. [E][✘] project meeting (at: Sunday)
	6. [T][✘] run
	7. [T][✘] study book
	8. [T][✘] read book
```

### 4. Marking task as done
To signal that task is completed

### Usage

### `done`

Will mark task as completed at given index

Format: `done [index]`

Example of usage:

`done 4`

Expected outcome:

```
Here are the tasks in your list:
	1. [T][✘] 1
	2. [T][✘] 3
	3. [E][✘] 4 (at: Wednesday)
	4. [D][✓] return book (by: June 6th)
	5. [E][✘] project meeting (at: Sunday)
	6. [T][✘] run
	7. [T][✘] study book
	8. [T][✘] read book`
```

### 5. Searching for specified keyword
Should a user want to search for related task, a keyword search is provided

### Usage

### `find`

Will search through task list for all tasks with specified keyword

Format: `find [keyword]`

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
	1. [D][✓] return book (by: June 6th)
	2. [T][✘] study book
	3. [T][✓] read book`
```

### 6. Exiting the program
Exits the program

Format: `bye`

## Saving the data
Duke automatically saves the data after each and every command, ensuring that data is always up to date. There is no need to save manually.

## Editing the data
Duke data is automatically saved as a textfile, `Duke.txt` at the location of `Duke.jar`. Advanced users are welcome to directly edit the textfile.

## FAQ
**Q:** How do I transfer data between computers?

**A:** To transfer data between computers, move the `Duke.txt` file from one computer to the other. Ensure the file is in the same folder as `Duke.jar`.
