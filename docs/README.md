# User Guide

## Features 

### Keep track of your tasks
Your tasks can be saved in a tasklist, along with their descriptions, status and due dates.

## Usage

### `todo` - Add Todo task to your tasklist

A Todo task will be added to your tasklist. Todo tasks only have a task description.

Example of usage:

`todo borrow book`

Expected outcome:

`I have added this task:`\
`[T][ ] borrow book`\
`You now have 1 tasks in your tasklist.`

### `deadline ... /by ...` - Add Deadline task to your tasklist

A Deadline task will be added to your tasklist. Deadline tasks have a task description and a deadline, in the format of YYYY-MM-dd.

Example of usage:

`deadline return book /by 2020-03-07`

Expected outcome:

`I have added this task:`\
`[D][ ] return book (by: 7 Mar 2020)`\
`You now have 2 tasks in your tasklist.`

### `event ... /at ...` - Add Event task to your tasklist

An Event task will be added to your tasklist. Event tasks have a task description and a timeslot.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

`I have added this task:`\
`[E][ ] project meeting (at: Mon 2-4pm)`\
`You now have 3 tasks in your tasklist.`


### `list` - Display all current tasks

Prints all tasks currently saved in your tasklist.

Example of usage:

`list`

Expected outcome:

`1.[T][ ] borrow book`\
`2.[D][ ] return book (by: 7 Mar 2020)`\
`3.[E][ ] project meeting (at: Mon 2-4pm)`


### `done` - Mark tasks as done

Given the indexes of the task(s) in the tasklist, the task(s) will be marked as done.

Example of usage:

`done 2`

Expected outcome:

`Great! I've marked this task as done:`\
`[D][X] return book (by: 7 Mar 2020)`\

### `delete` - Delete tasks from tasklist

Given the indexes of the task(s) in the tasklist, the task(s) will be deleted from the task list

Example of usage:

`delete 3`

Expected outcome:

`Okay, I've deleted this task:`\
`[E][ ] project meeting (at: Mon 2-4pm)`\
`You now have 2 tasks in your tasklist.`


### `find` - Find keyword in the tasklist's task descriptions

Given a keyword, tasks with the keyword in the task descriptions will be printed.

Example of usage:

`find book`

Expected outcome:

`Here are the matching tasks in your list:`\
`1.[T][ ] borrow book`\
`2.[D][X] return book (by: 7 Mar 2020)`


### `bye` - Ends program execution

Program will end.

Example of usage:

`bye`

Expected outcome:

`Goodbye. See you again soon :)`

