
#Duke

## User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command 
Line Interface** (CLI). If you can type fast, Duke can enable task list 
management faster than traditional GUI apps.

1. [Quick Start](#1-quick-start)
1. [Features](#2-features)\
   2.1 [Adding a to-do task: `todo`](#21-adding-a-to-do-task-todo)\
   2.2 [Adding a deadline: `deadline`](#22-adding-a-deadline-deadline)\
   2.3 [Adding an event: `event`](#23-adding-an-event-event)\
   2.4 [Listing all tasks: `list`](#24-listing-all-tasks-list)\
   2.5 [Marking a task done](#25-marking-a-task-done-done)\
   2.6 [Deleting a task](#26-deleting-a-task-delete)\
   2.7 [Finding a task](#27-finding-a-task-find)\
   2.8 [Exiting the program](#28-exiting-the-program-bye)\
   2.9 [Saving the data](#29-saving-the-data)\
   2.10 [Loading the date](#210-loading-the-data)\
   2.11 [Editing the data file](#211-editing-the-data-file)
1. [Frequently Asked Questions (FAQ)](#3-faq)
1. [Command Summary](#4-command-summary)

## 1. Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
1. Download the latest `Duke.jar` from [here](https://github.com/tzexern/ip/releases/download/A-Release/Duke.jar).
1. Copy the file to the folder you want to use as the *home folder* for your Duke.
1. For Windows users, pull up your **Command Prompt**.\
   For Mac users, pull up your **Terminal**.\
   Navigate to the directory containing the `Duke.jar` file.\
   i.e. `cd FILEPATH`, `FILEPATH` is the address of your file.
1. Run the Duke.jar file by using the `java -jar Duke.jar` command.


## 2. Features

>##### Notes about the command format:
>* Words in `UPPER_CASE` are the parameters to be supplied by the user.\
  e.g. in `todo TASKNAME`, `TASKNAME` is a parameter which can be used as 
  `todo watch CS2102 webcast`
>* Parameters must be in order as suggested in the respective format.\
  e.g. `deadline TASKNAME /by DATE` must be used as 
  `deadline assignment /by 03-28-2021`. Any reordering of parameters will 
  result in an error.
>* Extraneous parameters for all commands will result in an error.


### 2.1 Adding a to-do task: `todo`
Adds a to-do task to the task list.

Format: `todo TASKNAME`

Examples:
* `todo book bandroom`
* `todo retrieve parcel`
* Shows a message upon successful addition:\
![todo example output](https://github.com/tzexern/ip/blob/master/docs/img/Todo%20Example%20Output.png?raw=true)

### 2.2 Adding a deadline: `deadline`
Adds a task with an associated deadline.

Format: `deadline TASKNAME /by DATE`

* `DATE` must be a **future date**.
* `DATE` must be of the format **MM-DD-YYYY**.
* `DATE` must have a **valid month, day and year**.\
   e.g. `... /by 03-28-2021` where current date is 02-28-2021.

Examples:
* `deadline CS2113T Deliverables 2 /by 03-28-2021`
* `deadline CS3235 Quiz 4 /by 03-05-2021`
* Shows a message upon successful addition:\
  ![deadline example output](https://github.com/tzexern/ip/blob/master/docs/img/Deadline%20Example%20Output.png?raw=true)

### 2.3 Adding an event: `event`
Adds an event with a date.

Format: `event TASKNAME /at DATE`
* `DATE` must be a **future date**.
* `DATE` must be of the format **MM-DD-YYYY**.
* `DATE` must have a **valid month, day and year**.\
  e.g. `... /by 03-28-2021` where current date is 02-28-2021.

Examples:
* `event CNY gathering /at 02-11-2021`
* `event Valentines Date /at 02-14-2021`
* Shows a message upon successful addition:\
  ![event example output](https://github.com/tzexern/ip/blob/master/docs/img/Event%20Example%20Output.png?raw=true)

### 2.4 Listing all tasks: `list`
Shows a list of all the tasks currently saved in Duke.

Format: `list`

Examples:
* Here's a sample output of an existing list of task:\
  ![list example output](https://github.com/tzexern/ip/blob/master/docs/img/List%20Example%20Output.png?raw=true)

### 2.5 Marking a task done: `done`
Marks an existing task in the list as done.

Format: `done INDEX`
* Marks the task specified at `INDEX` as done.
* The `INDEX` refers to the index number shown in the displayed list of tasks.
* `INDEX` must be **a positive integer** 1, 2, 3,...
* `INDEX` must be referring to an existing task.

Examples:
* `done 2`\
  ![done example output](https://github.com/tzexern/ip/blob/master/docs/img/Done%20Example%20Output.png?raw=true)

### 2.6 Deleting a task: `delete`
Deletes an existing task in the list.

Format: `delete INDEX`
* Deletes the task specified at `INDEX`.  
* The `INDEX` refers to the index number shown in the displayed list of tasks.
* `INDEX` must be **a positive integer** 1, 2, 3,...
* `INDEX` must be referring to an existing task.

Examples:
* `delete 3`\
  ![delete example output](https://github.com/tzexern/ip/blob/master/docs/img/Delete%20Example%20Output.png?raw=true)

### 2.7 Finding a task: `find`
Searches for  a task and shows a list of the search results with their corresponding
index numbers in the original displayed list.

Format: `find SEARCH_KEYWORD`
* Searches for task names that matches the `SEARCH_KEYWORD` provided.
* `SEARCH_KEYWORD` must not be left empty.

Examples: 
* `find CS2113T` returns the following results:\
  ![find example output](https://github.com/tzexern/ip/blob/master/docs/img/Find%20Example%20Output.png?raw=true)
  
### 2.8 Exiting the program: `bye`
Exits the program.

Format: `bye`

### 2.9 Saving the data
Duke data are **saved automatically** in the hard disk after any commands that modifies
data. There is no need to save manually.

### 2.10 Loading the data
The save file will be **loaded automatically** if there is an existing one.\
Save file will be **created automatically** if it does not.

Examples:
* Here's a sample output of a save file being **loaded** successfully:\
  ![save file loaded](https://github.com/tzexern/ip/blob/master/docs/img/Save%20File%20Loaded.png?raw=true)
* Here's a sample output of a save file being **created** successfully:\
  ![save file created](https://github.com/tzexern/ip/blob/master/docs/img/Save%20File%20Created.png?raw=true)
  
### 2.11 Editing the data file
Duke data are saved as a text file `[JAR file location]/duke.txt`.\
Advanced users are welcome to update data directly by editing that data file.
> ***CAUTION***: If your changes to the data file makes its format invalid, you
> will have to manually delete the `duke.txt` file for the saving mechanism to
> work as intended.

## 3. FAQ

**Q**: How do I locate my duke.txt folder? I can't seem to find it.\
**A**: For Windows users, type `duke.txt` in the search panel and you should be able
to find its location. For Mac users, use Spotlight or Finder for the above actions.

## 4. Command Summary

Action | Format, Examples
------ | ----------------
**Add todo** | `todo TASKNAME` e.g. `todo book bandroom`
**Add deadline** | `deadline TASKNAME /by DATE` e.g. `deadline CS2113T Deliverables 2 /by 03-28-2021`
**Add event** | `event TASKNAME /at DATE` e.g. `event Valentines Date /at 02-14-2021`
**List** | `list`
**Done** | `done INDEX` e.g. `done 2`
**Delete** | `delete INDEX` e.g. `delete 3`
**Find** | `find SEARCH_KEYWORD` e.g. `find assignment`
**Exit** | `bye` 
