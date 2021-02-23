# CI User Guide

## Features 

### Task List
Able to track three types of tasks:
1. **ToDos**: tasks without any date/time attached to it *e.g., visit new theme park*
2. **Deadlines**: tasks that need to be done before a specific date/time *e.g., submit report by 11/10/2019 5pm*
3. **Events**: tasks that start at a specific time and ends at a specific time *e.g., team project meeting on 2/10/2019 2-4pm*

#### List Manipulation
Able to add, delete, show all tasks, and find tasks.

### Save and Load
Save the tasks in a file in the user's documents folder whenever the user exits the program.

When CI starts up, that file is read and loaded.

## Usage

### `-h` - Shows a list of available commands

Example of usage:

`-h`

### `todo` - Adds a todo to the user's list

The description should follow the keyword.

Example of usage:

`todo <task description>`

Expected outcome:

`Got it. I've added this task:`

`[T][✘] do homework`

### `deadline` - Adds a deadline to the user's task list

The description should follow the keyword and the date and time should be after "/by".

Date and time format: "yyyy-MM-dd HH:mm"

Example of usage:

`deadline <task description> /by <date and time>`

Expected outcome:

`Got it. I've added this task:`

`[D][✘] go to bed  (by: Feb 23 2021 22:00)`

### `event` - Adds an event to the user's task list

The description should follow the keyword and the date and time should be after "/at".

Date and time format: "yyyy-MM-dd HH:mm"

Example of usage:

`event <task description> /at <date and time>`

Expected outcome:

`Got it. I've added this task:`

`[E][✘] sister's wedding  (at: Feb 23 2021 22:00)`

### `done` - Mark task as done

Marks the task at the task index as done.

Example of usage:

`done <task index>`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][✓] do homework`

### `list` - Displays task list

Example of usage:

`list`

Expected outcome:

`There are 3 tasks in your list:`

`1.[T][✓] do homework`

`2.[T][✘] do housework`

`3.[D][✘] go to bed  (by: Feb 23 2021 22:00)`

### `delete` - Delete's a task from the list

Deletes the task at the task index provided.

Example of usage:

`delete <task index>`

Expected outcome:

`Got it. I've deleted this task:`

`[T][✘] he`

### `Find` - Find a task by searching for a keyword

Filters and shows all tasks with the keyword in their description.

Example of usage:

`find work`

Expected outcome:

`Finding tasks with "work" in their description...`

`6.[T][✘] do homework`

`7.[T][✘] do housework`
