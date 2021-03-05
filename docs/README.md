# User Guide

Welcome! Come and plan your tasks with Duke! :)

#### Types of tasks:

1. ToDo
2. Event
3. Deadline

* [Quick Start](#quick-Start)
* [Features](#features)
    * [List all Tasks : `list`](#list-all-tasks--list)
    * [Add Todo : `todo`](#add-todo--todo)
    * [Add Event : `event`](#add-event--event)
    * [Add Deadline : `deadline`](#add-deadline--deadline)
    * [Delete Task : `delete`](#delete-task--delete)
    * [Mark Task as Done : `done`](#mark-task-as-done--done)
    * [Find by Keyword : `find`](#find-by-keyword--find)
    * [Exit : `bye`](#exit--bye)
* [Command Summary](#command-summary)

&nbsp;

## Quick Start

Download `Duke.jar` from
here:

**Requirements:**

Ensure you have Java 11 or above installed.

**Usage:**

1. Store the .jar file in a folder
2. Run the .jar file
3. Enter your command!

&nbsp;

## Features

### List all Tasks : `list`

Displays all the tasks currently in your task list.

> `[T]`  Todo<br>
> `[E]`  Event<br>
> `[D]`  Deadline<br>

> `[X]`  Done<br>
> `[ ]`  Not done

**Format:** `list`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `list` |
| Output | `Here are the tasks in your list: ` <br>`1.[T][ ] Exercise`<br>`2.[E][X] Meet friends (at: May 22nd 5pm)`<br>`3.[D][ ] Watch lecture (by: Sunday 3pm)` |

&nbsp;

### Add ToDo : `todo`

Adds a Todo with description.<br>
**Format:** `todo <description>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `todo eat apple` |
| Output | `Got it. I've added this task:`<br>`[T][ ] eat apple`<br>`Now you have 4 tasks in the list.` |

&nbsp;

### Add Event : `event`

Adds an event with description and datetime.<br>
**Format:** `event <description> /at <datetime>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `Celebrate May's birthday /at 05/06/2021 1900` |
| Output | `Got it. I've added this task:`<br>`[E][ ] party (at: 05/06/2021 1900)`<br>`Now you have 5 tasks in the list.` |`

&nbsp;

### Add Deadline : `deadline`

Adds a deadline with description and datetime.<br>
**Format:** `deadline <description> /by <datetime>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `deadline Finish iP /by Sunday 3pm` |
| Output | `Got it. I've added this task:`<br>`[D][ ] Finish iP (by: Sunday 3pm)`<br>`Now you have 6 tasks in the list.` |

&nbsp;

### Delete Task : `delete`

Deletes a task using specified index<br>
**Format:** `delete <index> `

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `delete 6` |
| Output | `Noted, I've removed this task:`<br>`[D][ ] Finish iP (by:  Sunday 3pm)` |

&nbsp;

### Mark Task As Done : `done`

Marks task as done<br>
**Format:** `done <index>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `done 5` |
| Output | `Great job! I've marked this task as done:`<br>`[X] party` |

&nbsp;

### Find by Keyword : `find`

Displays all tasks containing the keyword<br>
**Format:** `find <keyword>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `find project` |
| Output | `Here are the matching tasks in your list:`<br>`1. [T][ ] do project` |

&nbsp;

### Exit : `bye`

Exits Application.<br>
**Format:** `bye`

&nbsp;

## Command summary

| Command | Format |
| --------- | ------------------------------------------------------- |
| list | `list` |
| todo | `todo <description>` |
| event | `event <description> /at <datetime>` |
| deadline | `deadline <description> /by <datetime>` |
| delete | `delete <index>` |
| done | `done <index>` |
| find | `find <keyword>` |
| bye | `bye` |