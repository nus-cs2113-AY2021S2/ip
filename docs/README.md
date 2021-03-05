# User Guide

Plan your Tasks with Duke! :memo:

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
    * [Mark as Done : `done`](#mark-as-done--done)
    * [Search by Keyword : `find`](#search-keyword--find)
    * [Exit : `bye`](#exit--bye)
* [Command Summary](#command-summary)

&nbsp;

## Quick Start

Download `TaskManager.jar` from
here: [https://github.com/cloudy3/ip/releases/tag/A-Release](https://github.com/cloudy3/ip/releases/tag/A-Release)

**Requirements:**

Java 11 and above

**Usage:**

1. Store the .jar file in a folder
2. Run the .jar file
3. Enter your command!

&nbsp;

## Features

### List all Tasks : `list`

List out all the tasks currently in your task list.

> `[T]`  Todo<br>
> `[E]`  Event<br>
> `[D]`  Deadline<br>

> `[✓]`  Done<br>
> `[ ]`  Not done

**Format:** `list`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `list` |
| Output | `1.[T][ ] Read Book`<br>`2.[E][✓] Tutorial (at: 02 of November 2021, 06:00 PM)`<br>`3.[D][ ] apple (by: 11 of November 0021, 08:00 AM)` |

&nbsp;

### Add ToDo : `todo`

Adds a Todo with description.<br>
**Format:** `todo <description>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `todo eat burger` |
| Output | `Got it. I've added this task:`<br>`[T][ ] eat burger`<br>`Now you have 4 tasks in the list.` |

&nbsp;

### Add Event : `event`

Adds an event with description and datetime.<br>
**Format:** `event <description> /at <DD/MM/YYYY HHmm>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `event party /at 05/03/2021 1800` |
| Output | `Got it. I've added this task:`<br>`[E][ ] party (at: 05 of March 2021, 06:00 PM)`<br>`Now you have 5 tasks in the list.` |`

&nbsp;

### Add Deadline : `deadline`

Adds a deadline with description and datetime.<br>
**Format:** `deadline <description> /by <DD/MM/YYYY HHmm>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `deadline finish ip /by 05/03/2021 2359` |
| Output | `Got it. I've added this task:`<br>`[D][ ] finish ip (by: 05 of March 2021, 11:59 PM)`<br>`Now you have 6 tasks in the list.` |

&nbsp;

### Delete Task : `delete`

Deletes a task using specified index<br>
**Format:** `delete <index> `

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `delete 6` |
| Output | `Noted. I've removed this task:`<br>`[D][ ] finish ip (by: 05 of March 2021, 11:59 PM)` |

&nbsp;

### Mark As Done : `done`

Marks task as done<br>
**Format:** `done <index>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `done 5` |
| Output | `Nice! I've marked this task as done:`<br>`[✓] party` |

&nbsp;

### Search by Keyword : `find`

List all tasks containing the search keyword<br>
**Format:** `find <keyword>`

| | Example |
| --------- | ------------------------------------------------------- |
| Input | `find burger` |
| Output | `Here are the matching tasks in your list:`<br>`1. [T][ ] eat burger` |

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
| event | `event <description> /at <DD/MM/YYYY HHmm>` |
| deadline | `deadline <description> /by <DD/MM/YYYY HHmm>` |
| delete | `delete <index>` |
| done | `done <index>` |
| find | `find <keyword>` |
| bye | `bye` |
