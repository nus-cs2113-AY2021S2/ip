# Duke User Guide
This is a Command Line Interface (CLI) application for managing tasks. It allows users to add and manage their various types of tasks.

- [Features](#features)
    - [Adding Todo](#adding-a-todo-task--todo)
    - [Adding Deadline](#adding-a-deadline-task--deadline)
    - [Adding Event](#adding-an-event-task--event)
    - [Displaying tasks](#displaying-tasks--list)
    - [Marking task as done](#changing-status-of-a-task--done)
    - [Removing task](#removing-a-task--delete)
    - [Finding keyword](Searching-for-a-keyword--find)
    - [Exiting Duke](#exiting-duke--bye)
- [Command summary](#command-summary)

## Features

### Notes about command format:
- Words in UPPER_CASE are the parameters to be supplied by the user. 
    - e.g. in `todo DESCRIPTION`, DESCRIPTION is a parameter which can be used 
      as `todo CS2113T Quiz`.
- Every task will be displayed in the following format:`[Task_Type][Status] Description`
    - e.g. `[T][ ] CS2113T Quiz`
    - 3 Types of task:
      - Todo: `[T]`
      - Deadline: `[D]`
      - Event: `[E]`
    - 2 Types of statuses:
      - Completed: `[X]`
      - Not Complete: `[ ]`

### Adding a Todo task : `todo`
Adds a Todo task to the task list.

Format: `todo DESCRIPTION`

Example:
- `todo Study for midterms`

Expected outcome:
	
  `Added: [T][ ] Study for midterms`

### Adding a Deadline task : `deadline`
Adds a Deadlne task to the task list.

Format: `deadline DESCRIPTION /by END_TIME`

Example:
- `deadline Study for midterms /by this thursday`

Expected outcome:
	
  `Added: [D][ ] Study for midterms (by: this thursday)`

### Adding an Event task : `event`
Adds a Event task to the task list.

Format: `event DESCRIPTION /at EVENT_TIME`

Example:
- `event Midterms /at this friday`

Expected outcome:
	
  `Added: [E][ ] Midterms (at: this friday)`

### Displaying tasks : `list`
Displays all the tasks in the task list.

Format: `list`

Expected outcome:	
```markdown
1.[D][ ] Cs3235 quiz (by: this friday)
2.[E][ ] Cs2113t meeting (at: this sunday 10am)
3.[T][X] Meet my friend
```

### Changing status of a task : `done`
Marks a task as done.

Format: `done TASK_NUMBER`

Example:
- `done 2`

Expected outcome:
```markdown
1.[D][ ] Cs3235 quiz (by: this friday)
2.[E][X] Cs2113t meeting (at: this sunday 10am)
3.[T][X] Meet my friend
```
### Removing a task : `delete`
Deletes a task from the task list.

Format: `delete TASK_NUMBER`

Example:
- `delete 3`

Expected outcome:
```markdown
Noted. I've removed this task:
[T][X] Meet my friend
```
### Searching for a keyword : `find`
Searches for keyword in the description of all the tasks. Keyword is **case-sensitive**.

Format: `find KEYWORD`

Example:
- `find Cs`

Expected outcome:
```markdown
[D][ ] Cs3235 quiz (by: this friday)
[E][X] Cs2113t meeting (at: this sunday 10am)
```

### Exiting Duke : `bye`
Exits the program.

Format: `bye`

Expected outcome:
```markdown
Bye. Hope to see you again soon!
```

### Storing and Loading of data

The tasks in the task list will be automatically stored in a text file when exiting Duke.

The tasks will also be automatically loaded into the task list from the text file when Duke starts.

## Command Summary

Command | Format | Example
------- | ------ | -------
`todo` | `todo DESCRIPTION` | `todo Study for midterms`
`deadline` | `deadline DESCRIPTION /by END_TIME` | `deadline Study for midterms /by this thursday`
`event` | `event DESCRIPTION /at EVENT_TIME` | `event Midterms /at this friday`
`list` | `list` | `list`
`done` | `done TASK_NUMBER` | `done 2`
`delete` | `delete TASK_NUMBER` | `delete 3`
`find` | `find KEYWORD` | `find Cs`
`bye` | `bye` | `bye`
