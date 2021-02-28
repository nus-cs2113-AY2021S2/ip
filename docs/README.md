# User Guide for Duke TodoList Bot

## Features
- Add todo task: `todo`
- Add deadline task: `deadline`
- Add event task: `event`
- Mark task as done: `done`
- Delete task: `delete`
- List tasks: `list`
- Find tasks by a keyword: `find`
- Exit the bot: `bye`
- Saving the data
- Importing the data

## Add todo task: `todo`
Adds a task with no due date or by date to the current list

### Usage

`todo [description of task]`


### Expected outcome:

`Task: [description of task] has been added.`

- At least one character must be entered in the field `[description of task]`

## Add deadline task: `deadline`
Adds a task with a due date to the current list

### Usage

`deadline [description of task] /by [date due]`


### Expected outcome:

`Task: [description of task] has been added.`

- At least one character must be entered in the field `[description of task]`
- At least one character must be entered in the field `[date due]`

## Add event task: `event`
Adds a task with a by date to the current list

### Usage

`event [description of task] /at [date by]`


### Expected outcome:

`Task: [description of task] has been added.`

- At least one character must be entered in the field `[description of task]`
- At least one character must be entered in the field `[date at]`

## Mark task as done: `done`
Resolves the selected task as done

### Usage

`done [task number]`


### Expected outcome:

`Task has marked as done!`

- At least one character must be entered in the field `[task number]`


## Delete task: `delete`
Deletes the selected task

### Usage

`delete [task number]`


### Expected outcome:

`Task has been deleted!`

- At least one numeric character must be entered in the field `[task number]`

## List tasks: `list`
Lists all current tasks

### Usage

`list`

### Expected outcome:

`1. [T] [ ] [description of task]`

`2. [D] [ ] [description of task] (by: [date by])`

`3. [E] [ ] [description of task] (at: [date at])`

- No parameters are required



## Find tasks by a keyword: `find`
Find task entries that contain the input keyword

### Usage

`find [input keyword/sentence]`

### Expected outcome:

`The following task(s) were found:`

`1. [T] [ ] [description of task containing keyword]`

`2. [D] [ ] [description of task containing keyword] (by: [date by])`

`3. [E] [ ] [description of task containing keyword] (at: [date at])`

- At least one character must be entered in the field `[input keyword/sentence]`

## Saving the data
todoList data is saved in the hard disk automatically in the file `data/duke.txt` after any command is input. There is no need to save manually.

## Importing the data
todoList data is imported from the file `data/duke.txt` and will populate the list on startup.