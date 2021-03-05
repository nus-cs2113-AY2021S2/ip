# User Guide

## Features 

### Feature 1. Add different types of tasks
1. Users are able to add a to-do item to the task list.
2. Users are able to add a deadline with time constraint specified to the task list.
3. Users are able to add an event with venue information specified to the task list.

### Feature 2. Mark completion status of task
Users are able to mark any to-do item as done.

### Feature 3. Delete task
Users are able to delete a to-do item from the task list.

### Feature 4. View the task list maintained by the bot
Users are able to see an indexed list of the task items, in the sequence of time they were added.

### Feature 5. Find the tasks with a specified keyword
Users are able to look for all tasks that contains the specifeid keyword.

### Feature 6. View the tasks data via text file
Users are able to view a readable task list stored in a text file under the data sub-directory in the jar-file directory.

## Usage

### `todo` - Adds a todo item to the task list.

Adds a Todo item to the task list. Parameters: description.

Example of usage: 

`todo test the project`

Expected outcome:

`Got it. I've added this task: [T][]test the project`

`Now you have 1 tasks in the list.`

### `deadline` - Adds a deadline to the task list.

Adds a deadline to the task list. Parameters: description, time(in the format yyyy-mm-dd(denoted by `by/`))

Example of usage: 

`deadline complete quiz by/ 2021-03-05`

Expected outcome:

`Got it. I've added this task: [D][]complete quiz (by: Mar 05 2021)`

`Now you have 2 tasks in the list.`

### `event` - Adds an event to the task list.

Adds an event to the task list. Parameters: description, venue(denoted by `at/`)

Example of usage: 

`event participate in audition at/ music room`

Expected outcome:

`Got it. I've added this task: [E][]participate in audition (at: music room)`

`Now you have 3 tasks in the list.`

### `list` - Display the current task list.

Display the current task list (indexed from 1).

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1. [T][]test the project`

`2. [D][]complete quiz (by: Mar 05 2021)`

`3. [E][]participate in audition (at: music room)`

### `find` - Find tasks that have the keyword in the current task list.

Display the tasks that have the keyword as a list (indexed from 1).

Example of usage: 

`find com`

Expected outcome:

`Here are the matching tasks in your list:`

`1. [D][]complete quiz (by: Mar 05 2021)`

### `done` - Mark the task as done.

Mark the task complete, identified by the index number used in the last listing.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done: complete quiz`

### `delete` - Delete the task.

Delete the task item, identified by the index number used in the last listing.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task: participate in audition`


### `bye` - Exit the bot.

Exit.

Example of usage: 

`bye`

Expected outcome:

bot exits, showing message.
