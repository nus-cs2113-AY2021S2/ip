# User Guide

Spark V3.0 is a personal planner application, optimized for use via a Command Line Interface (CLI),
while still having the benefits of an understandable Graphical User Interface (GUI). 

- Quick Start
- Features 
  - Viewing list of tasks: list
  - Adding a TODO task: todo 
  - Adding a EVENT task: event
  - Adding a DEADLINE task: deadline
  - Mark a task as completed: done
  - Delete a task from the list: delete
  - Search for a task with a keyword: find
  - Exiting the program: bye
  - Saving the data
  - Editing the data file
  - Loading of saved data file
- FAQ
- Command Summary

---


## Quick Start 

1. Ensure you have Java 11.
2. Download the latest Spark.jar from here (INSERT LINK).
3. Copy the file to the folder you want to use as the *home folder* for Spark.
4. Double-click the file to start the program. The CLI similar to below should appear within a few seconds. 


**INSERT PICTURE**
5. Type the command in the command box and press *Enter* to execute it. e.g. typing `list` and pressing *Enter* will
display a list of current tasks.
   
## Features

### Viewing list of tasks: `list`
Displays the list of tasks already input by user.

~~insertpicture~~

Format: `list`

### Adding a TODO event: `todo`
Adds a TODO task into the list

Format: `todo DESCRIPTION_OF_TASK`

* Task will have a `[T]` tagged to it.
* Command `todo` is case-insensitive.
* Requires a field after the `todo` command. 
* Does not accept blank inputs.

Examples:
* `todo Return book` Stores the task `Return book` with a `[T]` tag.
* `todo Collect delivery` Stores the task `Collect delivery` with a `[T]` tag.

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

  
### Marking a task as completed: `done`
Marks the specified task number as completed.

Format: `done TASK_POSITION_IN_LIST`

* Command `done` is case-insensitive.
* `TASK_POSITION_IN_LIST` must be an integer.
  
* `TASK_POSITION_IN_LIST` must be within range of the current list.
  
* `TASK_POSITION_IN_LIST` can be viewed with `list` command.

### Deletes a task from the list: `delete`
Removes the specified task from the list.

Format: `delete TASK_POSITION_IN_LIST`

* Command `delete` is case-insensitive.
* `TASK_POSITION_IN_LIST` must be an integer.

* `TASK_POSITION_IN_LIST` must be within range of the current list.

* `TASK_POSITION_IN_LIST` can be viewed with `list` command.

## Search for a task with a keyword: `find`
Searches for tasks in the list with the specified keyword.

Format: `find KEYWORD`

* Command `find` is case-insensitive.
* `KEYWORD` can be a short sentence, and need not be a singular word.
* Requires `KEYWORD` to run. Field cannot be left blank.
* List of tasks with `KEYWORD` in it will be displayed on the screen in the order they were listed.


## Exiting the program: `bye`
Exits the program

Format: `bye`

* Command `bye` is case-insensitive.
* List stored in the program will be auto saved upon execution of `bye`.


## Saving the data
List in the program's cache will be auto saved upon exit of program. 
There is no need to save manually.


## Editing the data file
The list data is saved as a .TXT file.
If changes to the data file makes its format invalid, Spark will discard all data
and start with an empty data file at the next run.


## Loading of saved data file
The data file will be automatically restored when Spark starts up. 
There is no need to manually load the data file with the list contents.

---

## Command Summary

 Command | Format, examples 
 --- | ---
 **List**| To list |
 **Add todo task** | test 
 **Add event** | test 
 **Add deadline task** | test 
 **Mark as done** | test 
 **Delete** | test 
 **Find** | test 

---
### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
