# User Guide

## Features 

### View tasks in list: `list`
Lists down the tasks that has been added.

#### Format: `list`

Expected outcome:
```text
Here are the tasks in your list:
1.[T][ ]  run
2.[D][ ]  return book (by: Sunday)
3.[E][ ]  attend lecture (at: 5pm)
```
### Mark task as completed: `done`
Marks a task at a specific index as completed

#### Format: `done INDEX`

Expected outcome:
```text
Nice! I've marked this task as done:
[T][✘]  run
```
### Add a todo task: `todo`
Add a todo task to the task list

#### Format: `todo DESCRIPTION`

Expected outcome:
```text
Got it. I've added this task: 
[T][ ]  run
Now you have 1 tasks in the list.
```
### Add a deadline task: `deadline`
Add a deadline task to the task list

#### Format: `deadline DESCRIPTION /by TIME`

Expected outcome:
```text
Got it. I've added this task: 
[D][ ]  submit assignment (by: Sunday 2359)
Now you have 4 tasks in the list.
```
### Add an event task: `event`
Add an event task to the task list

#### Format: `event DESCRIPTION /at TIME`

Expected outcome:
```text
Got it. I've added this task: 
[E][ ]  attend tutorial (at: Monday 1430)
Now you have 5 tasks in the list.
```
### Find a task: `find`
Find a task from the task list

#### Format: `find DESCRIPTION`

Expected outcome:
```text
Here are the matching tasks in your list: 
1.[D][ ]  submit assignment (by: Sunday 2359)
```

