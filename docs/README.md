# User Guide
---

## Features

### Create a list of tasks to complete.
The user is able to create up to 100 tasks of different types (i.e todo, deadline).

### Easily find tasks with a keyword.
The user is able to locate tasks easily by entering a keyword.

### View all tasks
The user can easily look at all the created task.

### Marking a task
The user can mark a task as done.

### Delete a task
The user can delete a task from the list.

---
## Usage

### `todo` - Creates a todo for the user.

Creates a todo-type task and adds it to the list of tasks that he user has. A todo requires a descirption of the task. A confirmation message will pop up and the number of tasks in the list will be displayed.

Example of usage: 

```
todo read book
```

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task.
[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```
### `deadline` - Creates a deadline for the user.

Creates a deadline-type task and adds it to the list of tasks that he user has. A deadline requires a description of the task and the date and/or time that it is due.  A confirmation message will pop up and the number of tasks in the list will be displayed.

Example of usage: 

```
deadline return book /by 2nd March
```

Expected outcome:

```
____________________________________________________________
Got it! I've added this task:
[D][ ] return book (by: 2nd March)
Now you have 2 tasks in the list.
____________________________________________________________
```
### `event` - Creates an event for the user.

Creates a event-type task and adds it to the list of tasks that he user has. An event requires a description of the task and, the date and timeslot of when it would take place. A confirmation message will pop up and the number of tasks in the list will be displayed.

Example of usage: 

```
event project meeting /at 3rd March 3-5pm
```

Expected outcome:

```
____________________________________________________________
Got it! I've added this task:
[E][ ] project meeting (at: 3rd March 3-5pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
### `list` - Displays all tasks that the user has created.

Prints out a list of all tasks which the user has added based. Each task will be marked with its task-type and whether it has been completed.

Example of usage: 

```
list
```

Expected outcome:

```
____________________________________________________________
Here are the tasks in your list
1. [T][ ] read book
2. [D][ ] return book (by: 2nd March)
3. [E][ ] project meeting (at: 3rd March 3-5pm)
____________________________________________________________
```
### `done` - Marks a task as completed.

Marks a specified task as completed and displays a confirmation message.

Example of usage: 

```
done 1
```

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```
### `delete` - Removes a task that the user has created.

Removes a specified task and updates the user on the number of tasks that he/she has now.

Example of usage: 

```
delete 3
```

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task.
[E][ ] project meeting (at: 3rd March 3-5pm)
Now you have 2 tasks in the list.
____________________________________________________________
```
### `find` - Displays tasks that contains a keyword specified by the user.

Displays tasks that contains a keyword specified by the user. Only one key word allowed.

Example of usage: 

```
find book
```

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: 2nd March)
____________________________________________________________
```
### `bye` - Ends user's current session.

Shutsdown app and saves user's current list of tasks.

Example of usage: 

```
bye
```

Expected outcome:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
