# User Guide

## Features 

### 1. Adding Tasks 
This allows the program to have the ability to store text entered by the user and display them back to the user when requested.
The program breaks down the adding of tasks using 3 different keywords.
#### i. Deadline 
#### ii. Todo
#### iii. Event

### 2. Deleting Tasks 
Add support for deleting tasks from the list.

### 3. Listing Tasks 
Allows the program to display the lists of task currently in the program when requested.

### 4. Marking Tasks as Done 
Allows the program to mark certain tasks in the list as done. 

### 5. Finding Tasks 
Give users a way to find a task by searching for a keyword.

## Usage

### `deadline` - Adding a task to remind the user of a deadline to meet.

Example of usage: 

`deadline return book /by Sunday`

Expected outcome:

`Got it. I've added this task:`
` [D][ ] return book (by: Sunday)`
`Now you have 6 tasks in the list.`

### `todo` - Adding a task to remind the user of a tasks that needs to be done.

Example of usage: 

`todo borrow book`

Expected outcome:

`Got it. I've added this task:`  
 ` [T][ ] borrow book`  
 `Now you have 5 tasks in the list`

### `event` - Adding a task to remind the user of an event scheduled.

Example of usage: 

`event project meeting /at Mon 2-4pm`

Expected outcome:

`Got it. I've added this task:` 
` [E][ ] project meeting (at: Mon 2-4pm)`
`Now you have 7 tasks in the list.`
 
### `delete` - Deletes the task specified from the current tasks list.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:`\ 
` [E][ ] project meeting (at: Aug 6th 2-4pm)`\
`Now you have 4 tasks in the list.`

### `list` - To display the tasks list currently in the program.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`\
` 1.[T][X] read book`\
` 2.[D][ ] return book (by: June 6th)`\
` 3.[E][ ] project meeting (at: Aug 6th 2-4pm)`

### `done` - Allow the task indicated to be marked as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`\ 
` [T][X] return book`
 
### `find` - To search through the current tasks list to find any task matching a keyword.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`\
` 1.[T][X] read book`\
` 2.[D][X] return book (by: June 6th)`

