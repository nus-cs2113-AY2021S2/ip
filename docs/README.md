# User Guide

## Features 

### Feature 1 
`Users are able to print and list out all the tasks created so far on the command prompt for display`

### Feature 2
`Users are able to create 3 types of tasks. They are todo, event and deadline task. Todo task has 2 attributes that users need to specify, which are the description of the task and the completion status of the task. Whereas for event and deadline task, both of them have 3 attributes that need to be specified, which are the description of the task, the completion status of the task and also the location of the event for event task or date of the deadline task.`

### Feature 3
`Users are able mark tasks as done through the application once they completed the respective tasks.`

### Feature 4
`Users are able to delete any tasks on the application. The targeted task may have been created wrongly in the first time or is longer needed.`

### Feature 5
`Users are able to look for tasks by using the find function provided by the application. In return, the application will display all the tasks that matches the keyword input by the users.`

### Feature 6
`Users may exit the application when they no longer need to use it.`

## Usage

### `list` - This action lists all the tasks created in the system to date.

Example of usage: 

`list`

Expected outcome:
```
-------------------------------
Here are the tasks in your list:
1.[T][✓]read book
-------------------------------
```
### `todo` - This action creates a todo task in the system.

Example of usage: 

`todo join sports club`

Expected outcome:
```
-------------------------------
Got it. I've added this task:
[T][✘]join sports club
Now you have 4 tasks in the list.
-------------------------------
```
### `event` - This action creates a event task in the system.

Example of usage: 

`event project meeting /at Aug 6th 2-4pm`

Expected outcome:
```
-------------------------------
Got it. I've added this task:
[E][✘]project meeting  (at:  aug 6th 2-4pm)
Now you have 3 tasks in the list.
-------------------------------
```
### `deadline` - This action creates a deadline task in the system.

Example of usage: 

`deadline return book/by June 6th`

Expected outcome:
```
-------------------------------
Got it. I've added this task:
[D][✘]return book (by:  june 6th)
Now you have 4 tasks in the list.
-------------------------------
```
### `done` - This action updates the completion status of an existing task as done in the system.

Example of usage: 

`done 2`

Expected outcome:
```
-------------------------------
Nice! I've marked this task as done:
[T][✓]join sports club
-------------------------------
```
### `delete` - This action remove an existing task from the system.

Example of usage: 

`delete 2`

Expected outcome:
```
-------------------------------
Noted. I've removed this task:
[T][✓]join sports club
Now you have 3 tasks in the list.
-------------------------------
```
### `find` - This action find all the existing tasks in the system that matches the keyword input by the users

Example of usage: 

`find book`

Expected outcome:
```
-------------------------------
Here are the matching tasks in your list:
1.[T][✓]read book
3.[D][✘]return book (by:  june 6th)
-------------------------------
```
### `bye` - This action will help user terminates the program.

Example of usage: 

`bye`

Expected outcome:
```
-------------------------------
-------------------------------
```
