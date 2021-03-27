# User Guide

## Features 

### Adding of different types of tasks
~.~
Dukchess supports the addition of three types of tasks:
- Todo: anything that you must do
- Event: anything you must do **at** a certain time
- Deadline: anything you must do **by** a certain time

### Listing of all tasks added
Dukchess lists all the tasks that you have previously 
added seamlessly.

### Setting the status of each task
Dukchess allows you to set whether a task has been
completed. If completed / finished, an 'X' will show up
in the checkbox beside a task, e.g. `[T][X] buy milk` vs
`[T][ ] gonna buy milk soon`

### Deletion of tasks that are no longer valid
Dukchess allows you to delete any tasks that were
previously added. Yay destruction!

### Finding tasks based on one keyword
Dukchess allows you to find tasks previously added, using
a single keyword search. It ain't google, but its something

### Data persistence for tasks
Dukchess ensures that the state of all tasks added or
modified will be stored in the filesystem if the program
is closed gracefully.

Now you won't have to rely on your brain so much to
remember things, yay stupidity!

## Usage

### `list` - List all tasks added

Example of usage: `list`

Expected outcome (if these three tasks were added prior):

```markdown
Here are the tasks in your list, Sir/Madam/Other:
>>> 1. [T][X] buy milk
>>> 2. [E][ ] meeting (at: 9.30)
>>> 3. [D][ ] retire (by: 40)
```

### `todo` - Add a new todo

Example of usage: `todo buy milk`

Expected outcome (if these three tasks were added prior):

```markdown
Gotcha, added this todo: [T][ ] buy milk
```

### `event` - Add a new event

Syntax: `event <description> /at <timing description>`

Example of usage: `event meeting /at 9.30`

Expected outcome:

```markdown
Gotcha, added this event: [E][ ] meeting (at: 9.30)
```

### `deadline` - Add a new deadline

Syntax: `deadline <description> /by <deadline time description>`

Example of usage: `deadline retire /by 40`

Expected outcome:

```markdown
Gotcha, added this deadline: [D][ ] retire (by: 40)
```

### `deadline` - Add a new deadline

Syntax: `deadline <description> /by <deadline time description>`

Example of usage: `deadline retire /by 40`

Expected outcome:

```markdown
Gotcha, added this deadline: [D][ ] retire (by: 40)
```

### `done` - Set a task to done

Syntax: `done <task-id>`

Example of usage: `done 1`

Expected outcome:

```markdown
Setting to done, original task status: [T][ ] buy milk
>>> New task status: [T][X] buy milk
```

### `delete` - Delete a task

Syntax: `delete <task-id>`

Example of usage: `delete 2`

Expected outcome:

```markdown
Noted, I've removed this task:
[E][ ] meeting (at: 9.30)
```

### `find` - Find a task using a keyword

Syntax: `find <keyword>`

Example of usage: `find retire`

Expected outcome:

```markdown
Here are the tasks matching "retire":
>>> 1. [D][ ] retire (by: 40)
```

### `bye` - Close program gracefully

Example of usage: `bye`

Expected outcome:

```markdown
Goodbye, hope to see you soon!
```