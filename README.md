# Duke

Duke is a Command Line Interface (CLI) application that serves as a Personal Assistant Chatbot for managing different Tasks.

* [Quick Start](#quick-start)
* [Tasks](#tasks)
* [Features](#features)
  * [Add todo task: `todo`](#add-todo-task-todo)
  * [Add deadline task: `deadline`](#add-deadline-task-deadline)
  * [Add event task: `event`](#add-event-task-event)
  * [List tasks: `list`](#list-tasks-list)
  * [Search keyword: `find`](#search-keyword-find)
  * [Search date: `schedule`](#search-date-schedule)
  * [Mark task as done: `done`](#mark-task-as-done-done)
  * [Delete task: `delete`](#delete-task-delete)
  * [Exit program: `bye`](#exit-program-bye)
* [Loading and Saving](#loading-and-saving)
* [Summary](#summary)
* [Credits](#credits)

## Quick Start
1. Run `duke.jar`
2. Type in a command and press enter to execute command

### Prerequisites
Java 11

## Tasks
Duke can store and retrieve 3 types of tasks:
1. `Todo` Task with description
2. `Deadline` Task with description and deadline `by`
3. `Event` Task with description and event time `at`

## Features
These are the commands that Duke can execute

### Add todo task: `todo`
Adds a `Todo` task with description to `TaskList`

**Format:** `todo <description>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `todo read book` |
| Output | Got it. I've added this task:<br>[T][✘] read book<br>Now you have 1 tasks in the list. |
| Action | Add `Todo` "read book" to `TaskList` |

### Add deadline task: `deadline`
Adds a `Deadline` task with description and deadline `by` to `TaskList`

**Format:** `deadline <description> /by <yyyy-mm-dd hhmm>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `deadline return book /by 2021-03-01 1800` |
| Output | Got it. I've added this task:<br>[D][✘] return book (by: 01 Mar 2021, 6:00 pm)<br>Now you have 2 tasks in the list. |
| Action | Add `Deadline` "return book" to `TaskList` |

### Add event task: `event`
Adds an `Event` task with description and event time `at` to `TaskList`

**Format:** `event <description> /at <yyyy-mm-dd hhmm>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `event book club meeting /at 2021-12-04 0900` |
| Output | Got it. I've added this task:<br>[E][✘] book club meeting (at: 04 Dec 2021, 9:00 am)<br>Now you have 3 tasks in the list. |
| Action | Add `Event` "book club meeting" to `TaskList` |

### List tasks: `list`
Lists all tasks in `TaskList` in numbered order in which they were added

#### First bracket: Task type
* [T] `Todo`<br>
* [D] `Deadline`<br>
* [E] `Event`

#### Second bracket: Done status
* [✓] Done
* [✘] Not done


**Format:** `list`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `list` |
| Output | Here are the tasks in your list:<br>1.[T][✘] read book<br>2.[D][✘] return book (by: 01 Mar 2021, 6:00 pm)<br>3.[E][✘] book club meeting (at: 04 Dec 2021, 9:00 am) |
| Action | Outputs all tasks in `TaskList` |

### Search keyword: `find`
Lists all tasks in `TaskList` which contains a specified keyword

**Format:** `find <keyword>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `find read` |
| Output | Here are the tasks matching the keyword: read<br>[T][✘] read book |
| Action | Outputs tasks in `TaskList` that contains the keyword "read" |

### Search date: `schedule`
Lists all tasks in `TaskList` which occur at a specified date

**Format:** `schedule <yyyy-mm-dd>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `schedule 2021-03-01` |
| Output | Here are the tasks on 01 Mar 2021<br>[D][✘] return book (by: 01 Mar 2021, 6:00 pm) |
| Action | Outputs tasks in `TaskList` that occur on 01 Mar 2021 |

### Mark task as done: `done`
Marks a `Task` in `TaskList` as done

**Format:** `done <index>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `done 2` |
| Output | Nice! I've marked this task as done:<br>[D][✓] return book (by: 01 Mar 2021, 6:00 pm) |
| Action | Mark `Task` "return book" as done |

### Delete task: `delete`
Deletes a `Task` in `TaskList`

**Format:** `delete <index>`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `delete 1` |
| Output | Noted. I've removed this task:<br>[T][✘] read book<br>Now you have 2 tasks in the list. |
| Action | Delete `Task` "read book" |

### Exit program: `bye`
Exits Duke

**Format:** `bye`

| | Usage |
| --------- | ------------------------------------------------------- |
| Input | `bye` |
| Output | Bye. Hope to see you again soon! |
| Action | Shows goodbye message and terminates Duke |

## Loading and Saving
### Loading
Duke will try to load existing data from `data.txt`.<br>
If file is not found, Duke will initialise an empty `TaskList`.

### Saving
Duke will try to save automatically when there are changes to the `TaskList`.<br>
Duke will save this data to `data.txt`.

## Summary
| Command | Format |
| --------- | ------------------------------------------------------- |
| todo | `todo <description>` |
| deadline | `deadline <description> /by <yyyy-mm-dd>` |
| event | `event <description> /at <yyyy-mm-dd>` |
| list | `list` |
| find | `find <keyword>` |
| schedule | `schedule <yyyy-mm-dd>` |
| done | `done <index>` |
| delete | `delete <index>` |
| bye | `bye` |

## Credits
From the Duke Project Template: [https://github.com/nus-cs2113-AY2021S2/ip](https://github.com/nus-cs2113-AY2021S2/ip)