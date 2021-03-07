# User Guide

## Features

### Feature 1: Add task.
The app can add tasks to the user's task list.

### Feature 2: List out the tasks.
The app will list out all the task in the user's task list.

### Feature 3: Mark task as done.
The app will mark the task of the user's choosing as finished/done.

### Feature 4: Remove task.
The app will remove a task from the user's task list.

### Feature 5: Search keyword.
The app will search for a keyword inside user's tasks and print the relevant tasks as output.

### Feature 6: Save file.
The app will remember the user's task list even after the user has exited the app.

## Usage

### `todo` - Add a to-do item to the task list.
The app will record the task.

Example of usage:
`todo going to the library`

Expected outcome:

```
    --------------------------------------------------------------------------
     Got it. I've added this task:
     [T][X] going to the library
     Now you have 1 tasks in this list.
    --------------------------------------------------------------------------     
```

### `deadline` - Add a deadline item to the task list.
The app will record the task and the time separated by the `/by` command.

Example of usage:
`deadline return book /by 6th Aug`

Expected outcome:
```
    --------------------------------------------------------------------------
     Got it. I've added this task:
     [D][X] return book (by: 6th Aug)
     Now you have 2 tasks in this list.
    -------------------------------------------------------------------------- 
```

### `event` - Add an event item to the task list.
The app will record the task and the time separated by the `/at` command.

Example of usage:
`event reading activity /at 2-4pm 6th Aug`

Expected outcome:
```
    --------------------------------------------------------------------------
     Got it. I've added this task:
     [E][X] reading activity (at: 2-4pm 6th Aug)
     Now you have 3 tasks in this list.
    -------------------------------------------------------------------------- 
```

### `done` - Mark a task as done.
The app will mark a chosen task as done.

Example of usage:
`done 2`

Expected outcome:
```
    --------------------------------------------------------------------------
     Nice! I've marked this task as done:
      [D][✓] return book (by: 6th Aug)
    --------------------------------------------------------------------------
```

### `list` - List out all the tasks in the task list.
The app will list out all the tasks in the user's task list.

Example of usage:
`list`

Expected outcome:
```
    --------------------------------------------------------------------------
     Here are the tasks in your list:
     1.[T][X] going to the library
     2.[D][✓] return book (by: 6th Aug)
     3.[E][X] reading activity (at: 2-4pm 6th Aug)
    --------------------------------------------------------------------------
```

### `find` - Find task that contains a keyword.
The app will list out tasks that contains the user's input keyword.

Example of usage:
`find book`

Expected outcome:
```
    --------------------------------------------------------------------------
     Here are the matching tasks in your list:
     1.[D][✓] return book (by: 6th Aug)
    --------------------------------------------------------------------------
```

### `delete` - Delete a task from the task list.
The app will delete a task by user's input number.

Example of usage:
`delete 1`

Expected outcome:
```
    --------------------------------------------------------------------------
     Noted. I've removed this task:
       [T][X] going to the library
     Now you have 2 tasks in this list.
    --------------------------------------------------------------------------
list
    --------------------------------------------------------------------------
     Here are the tasks in your list:
     1.[D][✓] return book (by: 6th Aug)
     2.[E][X] reading activity (at: 2-4pm 6th Aug)
    --------------------------------------------------------------------------
```

### `bye` - Exit program.

Example of usage:
`bye`

Expected outcome:
```
    --------------------------------------------------------------------------
     Bye. Hope to see you again soon!
    --------------------------------------------------------------------------
```