# User Guide

Duke is a command line application for managing tasks such as todos, deadlines and events. It is an individual project based on the Software Engineering & Object-Oriented Programming module (CS2113T) offered by NUS for AY2020/21 Semester 2. Details about the project can be found on the [module website](https://nus-cs2113-ay2021s2.github.io/website/admin/ip-overview.html).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a todo: `todo`](#adding-a-todo-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
  - [Finding tasks by date: `date`](#finding-tasks-by-date-date)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [Command summary](#command-summary)


## Quick Start

1. Ensure you have Java `11` or above installed in your Computer with environment variables set.
1. Download the latest `ip.jar` from [here](https://github.com/isaharon/ip/releases).
1. Copy the file to the folder where you want to use as the application's _home folder_. (User data will be stored here in a `data` folder.)
1. On your favourite command-line app, run `java -jar ip.jar` in the chosen _home folder_.
1. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will list the usage information of the app.

Some example commands you can try:

* `todo Read Quick Start guide` : Adds a todo task with the description provided to Duke.
* `list` : List all the tasks added.
* `done 1` : Marks the 1st item in the task list as done.
* `bye` : Exits the app.

## Features


>Notes about command format:
>
>* Words surrounded by `<angle brackets>` are the parameters to be supplied by the user.
>
>* Parameters must be in order and following the format specified.
>
>* Date follows the format `d/M/yyyy` (e.g. `25/12/2021`).
>
>* Time follows the 24h format `HHmm` (e.g. `1730` to describe 5.30pm).
>
>* Both date and time has to be in the future from current date and/or time when adding tasks.
>
>* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will be ignored. (e.g. if the command specifies `bye duke`, it will be interpreted as `bye`).


### Viewing help: `help`

Shows usage information and the commands available to the user.

### Adding a todo: `todo`

Adds a todo task to the task list.

Format: `todo <task>`

Sample input: `todo Update Duke User Guide`

Expected output:

```
────────────────────────────────────────────────────────────
Got it. I've added this task:
        [T][ ] Update Duke User Guide
Now you have 1 tasks in the list.
────────────────────────────────────────────────────────────
```

### Adding a deadline: `deadline`

Adds a deadline task to the task list.

Format: `deadline <task> /by <deadline date>`

Sample input: `deadline Publish Final Version /by 2/3/2021`

Expected output:

```
────────────────────────────────────────────────────────────
Got it. I've added this task:
        [D][ ] Publish Final Version (by: 2 Mar 2021)
Now you have 2 tasks in the list.
────────────────────────────────────────────────────────────
```

### Adding an event: `event`

Adds an event task to the task list.

Format: `event <task> /at <event date> <event time>`

Sample input: `event CS2113T Tutorial /at 3/3/2021 0900`

Expected output:

```
────────────────────────────────────────────────────────────
Got it. I've added this task:
        [E][ ] CS2113T Tutorial (at: 3 Mar 2021 09:00 AM)
Now you have 3 tasks in the list.
────────────────────────────────────────────────────────────
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Expected output:

```
────────────────────────────────────────────────────────────
Here are the tasks in your list:
 1. [T][ ] Update Duke User Guide
 2. [D][ ] Publish Final Version (by: 2 Mar 2021)
 3. [E][ ] CS2113T Tutorial (at: 3 Mar 2021 09:00 AM)
────────────────────────────────────────────────────────────
```

### Marking a task as done: `done`

Sets the task specified as done.

Format: `done <task number>`

* Requires knowledge of the task number to be set done. Use `list`.

Sample input: `done 1`

Expected output:

```
────────────────────────────────────────────────────────────
Nice! I've marked this task as done:
        [T][✘] Update Duke User Guide
────────────────────────────────────────────────────────────
```

### Deleting a task: `delete`

Deletes the task specified from the task list.

Format: `delete <task nunber>`

* Requires knowledge of the task number to be deleted. Use `list`.

Sample input: `delete 3`

Expected output:

```
────────────────────────────────────────────────────────────
Got it. I've deleted this task:
        [E][ ] CS2113T Tutorial (at: 3 Mar 2021 09:00 AM)
────────────────────────────────────────────────────────────
```

### Finding tasks by keyword: `find`

Finds tasks with task description that contain the keyword.

Format: `find <keyword>`

Sample input: `find Final`

Expected output:

```
────────────────────────────────────────────────────────────
Here are the matching tasks in your list:
 2. [D][ ] Publish Final Version (by: 2 Mar 2021)
────────────────────────────────────────────────────────────
```

### Finding tasks by date: `date`

Finds events/deadlines that is due/occur at the given date.

Format: `date <dd/MM/yyyy>`

Sample input: `find 2/3/2021`

Expected output:

```
────────────────────────────────────────────────────────────
Here are deadlines/events on 2 Mar 2021:
 2. [D][ ] Publish Final Version (by: 2 Mar 2021)
────────────────────────────────────────────────────────────
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Expected output:

```
────────────────────────────────────────────────────────────
 Bye. Hope to see you again soon!
────────────────────────────────────────────────────────────
```

### Saving the data

Duke's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Editing of the data file is possible but not recommended although Duke tries to handle any malformed data file appropriately by discarding the malformed data file and creating a new one.

## Command summary

| Action | Format                       | Examples                                     |
| ------ | ---------------------------- | -------------------------------------------- |
| Add    | `todo/deadline/event <task>` | `deadline Duke Command Summary /by 1/3/2021` |
| List   | `list`                       | `list`                                       |
| Done   | `done <task number>`         | `done 3`                                     |
| Delete | `delete <task number>`       | `delete 1`                                   |
| Find   | `find <keyword>`             | `find CS2113T`                               |
| Date   | `date <dd/MM/yyyy>`          | `date 28/2/2021`                             |
| Bye    | `bye`                        | `bye`                                        |
| Help   | `help`                       | `help`                                       |
