# User Guide

Spark V3.0 is a personal planner application, optimized for use via a Command Line Interface (CLI),
while still having the benefits of an understandable Graphical User Interface (GUI). 

- [Quick Start](#quick-start) 
- [Features](#features) 
  - [Viewing list of tasks: `list`](#viewing-list-of-tasks-list)
  - [Adding a TODO task: `todo`](#adding-a-todo-task-todo)
  - [Adding an EVENT task: `event`](#adding-an-event-task-event)
  - [Adding a DEADLINE task: `deadline`](#adding-a-deadline-task-deadline)
  - [Marking a task as completed: `done`](#marking-a-task-as-completed-done)
  - [Deleting a task from the list: `delete`](#deleting-a-task-from-the-list-delete)
  - [Searching for a task with a keyword: `find`](#searching-for-a-task-with-a-keyword-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Loading of saved data file](#loading-of-saved-data-file)
- [FAQ](#FAQ)
- [Command Summary](#command-summary)

---

## Quick Start 

1. Ensure you have Java 11.
2. Download the latest Spark.jar from here (INSERT LINK).
3. Copy the file to the folder you want to use as the *home folder* for Spark.
4. Double-click the file to start the program. The CLI similar to below should appear within a few seconds. 


**INSERT PICTURE**
5. Type the command in the command box and press *Enter* to execute it. e.g. typing `list` and pressing *Enter* will
display a list of current tasks.

<br/><br/>
## Features

### Viewing list of tasks: `list`
Displays the list of tasks already input by user.

~~insertpicture~~

Format: `list`
<br/><br/>
### Adding a TODO task: `todo`
Adds a TODO task into the list

Format: `todo DESCRIPTION_OF_TASK`

* Task will have a `[T]` tagged to it.
* Command `todo` is case-insensitive.
* Requires a field after the `todo` command. 
* Does not accept blank inputs.

Examples:
* `todo Return book` Stores the task `Return book` with a `[T]` tag.
* `todo Collect delivery` Stores the task `Collect delivery` with a `[T]` tag.
  <br/><br/>
### Adding an EVENT task: `event`
Adds an EVENT task into the list

Format: `event DESCRIPTION_OF_TASK /DATE_OF_EVENT [ADDITIONAL_COMMENTS_ON_EVENT]`

* Task will have a `[E]` tagged to it.
* Command `event` is case-insensitive.
* Requires task description at `DESCRIPTION`.
* Requires the date of event in either text or numerical form with the `/` prefix. Date will be recorded at first instance of `/`
* Optional to add in additional comments on the task after entering the date. Additional comments
will append to the end of the date.
  
Examples:
* `event Attend concert /23/12/2019`
  Stores the task `Attend concert` with a `[E]` (EVENT) tag, and the event date `23/12/2019`.
* `event Sam's birthday party /today 3pm. Remember to bring cake!`
  Stores the task `Sam's birthday party` with a `[E]` (EVENT) tag, and the event date `today 3pm`.
  <br/><br/>
### Adding a DEADLINE task: `deadline`
Adds a DEADLINE task into the list

Format: `deadline DESCRIPTION_OF_TASK /DATE_OF_DEADLINE [ADDITIONAL_COMMENTS_ON_DEADLINE]`

* Task will have a `[D]` tagged to it.
* Command `deadline` is case-insensitive.
* Requires task description at `DESCRIPTION`.
* Requires the date of deadline in either text or numerical form with the `/` prefix. Date will be recorded at first instance of `/`
* Optional to add in additional comments on the task after entering the date. Additional comments
  will append to the end of the date.

Examples:
* `deadline Complete CS2113T assignment /23rd December` 
  Stores the task `Complete CS2113T assignment` with a `[D]` (Deadline) tag, and the deadline `23rd December`.
* `deadline Pay water bill /5pm` 
  Stores the task `Pay water bill` with a `[D]` (Deadline) tag, and the deadline `5pm`.
  <br/><br/>
### Marking a task as completed: `done`
Marks the specified task number as completed.

Format: `done TASK_POSITION_IN_LIST`

* Command `done` is case-insensitive.
* `TASK_POSITION_IN_LIST` must be an integer.
  
* `TASK_POSITION_IN_LIST` must be within range of the current list.
  
* `TASK_POSITION_IN_LIST` can be viewed with `list` command.
  
* Corrupted tasks cannot be marked as completed.
  <br/><br/>
### Deleting a task from the list: `delete`
Removes the specified task from the list.

Format: `delete TASK_POSITION_IN_LIST`

* Command `delete` is case-insensitive.
* `TASK_POSITION_IN_LIST` must be an integer.

* `TASK_POSITION_IN_LIST` must be within range of the current list.

* `TASK_POSITION_IN_LIST` can be viewed with `list` command.
  
* Corrupted tasks should be deleted from the list.
  <br/><br/>
### Searching for a task with a keyword: `find`
Searches for tasks in the list with the specified keyword.

Format: `find KEYWORD`
* Command `find` is case-insensitive.
* `KEYWORD` can be a short sentence, and need not be a singular word.
* Requires `KEYWORD` to run. Field cannot be left blank.
* List of tasks with `KEYWORD` in it will be displayed on the screen in the order they were listed.
  <br/><br/>
### Exiting the program: `bye`
Exits the program

Format: `bye`

* Command `bye` is case-insensitive.
* List stored in the program will be auto saved upon execution of `bye`.
  <br/><br/>
### Saving the data
List in the program's cache will be auto saved upon exit of program. 
There is no need to save manually.
<br/><br/>
### Editing the data file
The list data is saved as a .TXT file.
If changes to the data file makes its format invalid, Spark will mark that particular task as corrupted.
Upon `list` command, corrupted tasks are labelled `Corrupted. Please delete.`
<br/><br/>
### Loading of saved data file
The data file will be automatically restored when Spark starts up. 
There is no need to manually load the data file with the list contents.

---

## FAQ

**Q**: Are commands case-sensitive?<br/>
**A**: Commands are not case-sensitive.

**Q**: What if I do not have an existing saved .TXT file on my computer?<br/>

**A**: The program will detect this and create a new .TXT file automatically.


---

## Command Summary

 **Command** | **Format, examples** 
 :--- | :---
 **List**| `list` |
 **Add TODO task** | `todo DESCRIPTION_OF_TASK`, e.g. `todo Return book`
 **Add EVENT task** | `event DESCRIPTION_OF_TASK /DATE_OF_EVENT [ADDITIONAL_COMMENTS_ON_EVENT]`, e.g.`event Attend concert /23/12/2019`
 **Add DEADLINE task** | `deadline DESCRIPTION_OF_TASK /DATE_OF_DEADLINE [ADDITIONAL_COMMENTS_ON_DEADLINE]`,e.g.`deadline Complete CS2113T assignment /23rd December`
 **Mark as done** | `done TASK_POSITION_IN_LIST`, e.g. `done 3`
 **Delete** | `delete TASK_POSITION_IN_LIST`,e.g. `delete 3`
 **Find** | `find KEYWORD`, e.g. `find book`
 **Exit** | `bye`

---
