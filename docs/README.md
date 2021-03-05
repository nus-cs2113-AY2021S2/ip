# User Guide

## Features 

### 1. Shows the current list of task that the user has while using Duke.


### 2. Adds tasks of different types to the list
- Todo task: tasks without specific time stamp
- Event task: tasks occurring at specific date
- Deadline task: tasks need to be done be a specific date

### 5. Deletes the specified task in the list.
If user found a task with wrong information in the current list, he is able to delete it.
### 6. Marks the specified task in the list as done.
After user finished a certain task, he is able to mark it as done.
### 7. Searches and show tasks by keywords.
Given a keyword from the user, Duke
will search and show tasks that matches
the keyword.

### 8. Searches and show tasks by date.
Given a certain date from the user, Duke
will search and show tasks that need to 
be done /at or /by that date.

### 9. Exits the system.
Save the current list of tasks and close Duke.

### 10. Provides user guide to new user.
Provides a list of commands that Duke supports for the user.


## Usage

### `list` - Listing all tasks

Shows a list of all tasks(todos, events, deadlines) in Duke.
- Format: `list`

- Example of usage: `list`

- Expected outcome:
````
1. [T][ ]task1
2. [T][ ]task2
3. [E][ ]PLAY (at: Mar 3 2020)
4. [E][ ]prepare meeting (at: Mar 4 2020)
5. [D][ ]submit (by: Mar 4 2020)
````

### `todo` - Adding a todo

Adds a todo to Duke.
- Format: `todo [desc]`

- Example of usage: `todo reading books`

- Expected outcome:
````
Got it. I've added this task:
[T][ ]reading books
Now you have 6 tasks in the list.
````

### `deadline` - Adding a deadline
Adds a deadline to Duke. A deadline is a task that should be done by a certain time.
- Format: `deadline [desc] /by yyyy-mm-dd`
- Example of usage: `deadline tut5 /by 2020-3-20`

- Expected outcome:

````
Got it. I've added this task:
[D][ ]tut5 (by: Mar 20 2020)
Now you have 7 tasks in the list.
````

### `event ` - Adding a event

Adds an event to Duke. An event is something that happens on the specific day.
- Format: `event [desc] /at yyyy-mm-dd`
- Example of usage: `event CS3223 meeting /at 2020-3-21`

- Expected outcome:

````
Got it. I've added this task:
[E][ ]CS3223 meeting (at: Mar 21 2020)
Now you have 8 tasks in the list.
````

### `done` - Marking a task as done

Marks the specified task as done.

- Format: `done [index]`
- Example of usage: `done 3`

- Expected outcome:

````
Nice! I've marked this task as done:
[E][X]PLAY (at: Mar 3 2020)
````

### `delete` - Deleting a task

Deletes the specified task from Duke.
- Format: `delete [index]`
- Example of usage: `delete 1`

- Expected outcome:

```
Noted. I've removed this task: 
[T][ ]task1
Now you have 7 tasks in the list.
```

### `find` - Locating tasks by keywords
Find tasks whose descriptions contain any of the given keywords.

- Format: `find [keyword]`
- Example of usage: `find meeting`

- Expected outcome:

```
[E][ ]prepare meeting (at: Mar 4 2020)
[E][ ]CS3223 meeting (at: Mar 21 2020)
```

### `filter` - Locating tasks by date
Filter tasks that need to be done /at or /by that date.
- Format: `filter yyyy-mm-dd`

- Example of usage: `filter 2020-03-04`

- Expected outcome:

```
[E][ ]prepare meeting (at: Mar 4 2020)
[D][ ]submit (by: Mar 4 2020)
```

### `help` - Viewing help
Provides a list of commands that Duke supports for the user.
- Format: `help`

- Example of usage: `help`

- Expected outcome:

```
User Guide: 
1. todo ...
2. event ... /at yyyy-mm-dd
3. deadline ... /by yyyy-mm-dd
4. done [index]
5. delete [index]
6. list
7. filter yyyy-mm-dd
8. find ...
9. bye
10. help
```

### `bye` - Exiting Duke
Save the current list of tasks and close Duke.

- Format: `bye`

- Example of usage: `bye`

- Expected outcome:

```
Bye. Hope to see you again soon!
```