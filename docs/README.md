# Duke _the task manager_
#### by, *Ivan Chong Zhi En*
#### _Last updated:_ `27 February 2021`
Duke is a Command Line Interface (CLI) app for managing your day-to-day tasks.
If you work faster by typing, managing tasks using Duke will be a breeze.

## Contents
1. [Quick start](#1-quick-start)
2. [Features](#2-features)
    * [2.1 Viewing help](#21-viewing-help-help)
    * [2.2 Adding a task](#22-adding-a-task-todo-deadline-event)
        1. [Todo](#a-todo)
        2. [Deadline](#b-deadline)
        3. [Event](#c-event)
    * [2.3 Listing all tasks](#23-listing-all-tasks-list)
    * [2.4 Mark a task as done](#24-mark-a-task-as-done-done)
    * [2.5 Unmark a completed task](#25-unmark-a-completed-task-undo)
    * [2.6 Delete a task](#26-delete-a-task-delete)
    * [2.7 Filtering tasks by keyword](#26-delete-a-task-delete)
    * [2.8 Exiting the program](#28-exiting-the-program-bye)
3. [Transferring data to another device](#3-transferring-data-to-another-device)
4. [Command Summary](#4-command-summary)


## 1. Quick start
1. Ensure you have Java `11` or above installed on your computer.
2. [Download](https://github.com/ivanchongzhien/ip/releases/latest) the latest `Duke.jar`.
3. Move the file to an empty folder. This will be the **home directory** for your Duke application.
4. Open a terminal window in the home directory.
5. To run Duke, enter `java -jar Duke.jar`
6. Enter `help` for a quick list of commands. Refer to [features](#2-features) below for details of each command.

* Note that on first run, a new data file `duke_data.txt` will be created. This file will store data about your tasks
  as you use Duke.
  You are ***strongly advised* not to** edit the text file directly as this may result in reading errors.
  Edit your data directly via Duke.

[**> back to top**](#contents)

## 2. Features
## 2.1. Viewing help: `help`
Shows a summarised list of commands and their respective formats.

**Format:** `help`

[**> back to top**](#contents)


## 2.2 Adding a task: `todo` `deadline` `event`
###    a. Todo
The most basic type of task, consists only of a task description.

**Format:** `todo [description]...`

* _description:_ _**String**_ task description

**Example usage:** `todo organize exam notes`

**Expected output:**
```
Added to list:
    [T][ ] organize exam notes
1 task in the list
```
[**> back to top**](#contents)


###    b. Deadline
Deadline has an additional field to include details on when a task is due.

**Format:** `deadline [description]... /by [due]`

* _description:_ _**String**_ task description

* _due:_ _**String**_ due date of a task. For dates, use format `dd-mm-yyyy`.
  Normal string descriptions are accepted, _eg: tomorrow, Wednesday 2PM, 2nd of May_.

**Example usage:** `deadline CS assignment /by tomorrow midnight`

**Expected output:**
```
Added to list:
   [D][ ] CS assignment (by: tomorrow midnight)
2 tasks in the list
```
[**> back to top**](#contents)


###    c. Event
Event has an additional field to include details on when described event happening.

**Format:** `event [description]... /at [time/date]`

* _description:_ _**String**_ event description

* _time/date:_ _**String**_ time/date of event. For dates, use format `dd-mm-yyyy`.
  Normal string descriptions are accepted, _eg: next week, Thursday 4PM, 5th of May_

**Example usage:** `event John's party /at 25-5-2021`

**Expected output:**
```
Added to list:
   [E][ ] John's party (at: 25 May, 2021)
4 tasks in the list
```
[**> back to top**](#contents)


## 2.3 Listing all tasks: `list`
Shows a list of all tasks currently stored in the task list.

**Format:** `list`

[**> back to top**](#contents)

## 2.4 Mark a task as done: `done`
Marks selected task as completed.
An `[X]` beside a task shows that it has been marked as completed.
* _Note:_ this command does not check if a task is already marked as _done_.
  It will set the task to an _done_ state regardless of its previous state.

**Format:** `done [index]`

* _index:_ _**int**_ index of the task to be marked as done.

* Enter the _index_ following the numbering of the task as shown in `list`.

**Example usage** : `list` followed by `done 2` (to mark the second item on the list)

**Expected output:**
```
TASK LIST:
-------------
1. [T][ ] organize exam notes
2. [D][ ] CS assignment (by: tomorrow midnight)
3. [D][ ] statistics quiz (by: 25 February, 2021)
4. [E][ ] John's party (at: 25 May, 2021)
```
```
Congrats! You've completed:
   [D][X] CS assignment (by: tomorrow midnight)
```
[**> back to top**](#contents)


## 2.5 Unmark a completed task: `undo`
Marks a task as uncompleted. A `[ ]` beside a task shows that it is not done.
* _Note:_ this command does not check if the task is not marked _done_.
  It will set the task to an _undone_ state regardless of its previous state.

**Format:** `undo [index]`

* _index:_ _**int**_ index of the task to be unmarked.
* Enter the _index_ following the numbering of the task as shown in `list`.

**Example usage:** `undo 2`

**Expected output:**
```
Marked undone! Looks like you're not quite done with:
   [D][ ] CS assignment (by: tomorrow midnight)
```
[**> back to top**](#contents)


## 2.6 Delete a task: `delete`
Removes the specified task from the task list.

**Format:** `delete [index]`

* _index:_ _**int**_ index of the task to be deleted.
* Enter the _index_ following the numbering of the task as shown in `list`.

**Example usage:** `delete 3`

**Expected output**:
```
Task 3 has been deleted:
   [D][ ] statistics quiz (by: 25 February, 2021)
Tasks remaining: 3
```
[**> back to top**](#contents)


## 2.7 Filtering tasks by keyword: `find`
List tasks which contain the given keyword.
* search is case-sensitive, _eg:_ `john` will not match `John`.
* only tasks descriptions containing the entire keyword string will be matched, _eg:_ `birthday party`
  will match `John's birhtday party` but **not** `Alice's birthday`.
* Only find matches within task description. Task details (_due date, time etc._) will not be searched.
* Index of search results are the same as index of `list`.

**Format:** `find [keyword]`
* _keyword:_ _**String**_ tasks will be checked if they contain this string.

**Example usage:** `find assignment`

**Expected output:**
```
SEARCH RESULTS FOR "assignment":
1. [D][ ] CS assignment (by: tomorrow midnight)
```
[**> back to top**](#contents)


## 2.8 Exiting the program: `bye`
Exits Duke. No manual saving is required as data is automatically saved by the program.
Undeleted tasks will be loaded up the next time Duke is ran.

**Format:** `bye`

**Expected output:**
```
____________________________________________________________
Bye! Hit me up if you feel like being productive again ;)
____________________________________________________________
```
[**> back to top**](#contents)


## 3. Transferring data to another device
1. Copy the `duke_data.txt` file. **Do NOT** rename the file.
2. Paste it in the same directory as the `Duke.jar` file on your new device.
3. Running `Duke` should load your previous data.

## 4. Command Summary

Command     | Format                                   | Example
 -----------| ---------------------------------------- | --------------------------------------
help        | `help`                                   | `help`
todo        | `todo [description]...`                  | `todo organize exam notes`
deadline    | `deadline [description]... /by[due]`     | `deadline CS assignment /by tomorrow midnight`
event       | `event [description] /at[time/date*]`    | `event John's party /at 25-5-2021`
list        | `list`                                   | `list`
done        | `done [index]`                           | `done 2`
undo        | `undo [index]`                           | `undo 2`
delete      | `delete [index]`                         | `delete 3`
find        | `find [keyword]`                         | `find birthday party`
bye         | `bye`                                    | `bye`

_***Date format:**_ `dd-mm-yyyy`

[**> back to top**](#contents)





