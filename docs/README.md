# Duke User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Duke can get your task management done faster than traditional GUI apps.

__________________________

### Quick start
1. Ensure you have Java `11` or above installed in your Computer.


2. Download the latest `duke.jar` from [here](https://github.com/Tyuanyuan/ip/releases).


3. Copy the file to the folder you want to use as the *home folder* for your Duke.


4. Double-click the file to start the app. 


5. Type the command in the command box and press Enter to execute it.

   Some example commands you can try:
    * `list` : Lists all tasks.
    * `todo task 1` : Add a todo task to task list.
    * `deadline task 2 /by today` : Add a deadline task to task list.
    * `event task 3 /at tomorrow` : Add an event task to task list.
    * `delete 1` : Delete the task in the task list that has task number 1.
    * `done 1` : Mark the task in the task list that has task number 1 as done.
    * `find task` : Find the tasks in the task list that contain the keyword.
    * `bye` : Exits the app.


6. Refer to the Features below for details of each command.

__________________________

### Features

<span style="color:green">
Notes about the command format: <br>
Words in UPPER_CASE are the parameters to be supplied by the user. 
</span>

1. Listing all tasks : `list`

   Shows a list of all tasks in the task list.

   Format: `list`


2. Adding a todo task: `todo`

   Adds a todo task to the task list.

   Format: `todo TODO`

   Example:

    * `todo task 1`


3. Adding a deadline task: `deadline`

   Adds a deadline task to the task list.

   Format: `deadline DEADLINE /by BYTIME`

   Example:

    * `deadline task 2 /by today`


4. Adding an event task : `event`

   Adds an event task to the task list.

   Format: `event EVENT /at ATTIME`

   Example:

    * `event task 3 /at tomorrow`


5. Deleting a task : `delete`

   Deletes the specified task from the task list.

   Format: `delete INDEX`

    * Deletes the task at the specified `INDEX`.
    * The index refers to the task number shown in the displayed task list.

   Examples:

    * `list` followed by `delete 1` deletes the task that has task number 1.
    * `find task` followed by `delete 2` deletes the task in the results of the `find` command that has task number 2.


6. Marking a task as done : `done`

   Marks the specified task from the task list as done.

   Format: `done INDEX`

    * Marks the task at the specified `INDEX` as done.
    * The index refers to the task number shown in the displayed task list.

   Examples:

    * `list` followed by `done 1` marks the task that has task number 1 as done.
    * `find task` followed by `done 2` marks the task in the results of the `find` command that has task number 2 as done.


7. Locating tasks by task name : `find`

   Finds tasks whose task names contain any of the given keywords.

   Format: `find KEYWORD`

    * The search is case-sensitive. e.g. `task` will not match `Task`.
    * The order of the keywords matters. e.g. `task 1` will not match `1 task`.
    * The task names, by time and at time can all be searched.
    * Partial keywords can be matched with full keywords. e.g. `task` will match `task 1`.

   Examples:

    * `find task` returns `task 1` and `task 2`.
    * `find today` returns `task 1 (at: today)`.


8. Exiting the program : `bye`

   Shows goodbye quotes and exits the program.

   Format: `bye`


9. Saving the data

   Duke data are saved in the hard disk automatically after exiting the program properly. There is no need to save manually.

__________________________

### Command Summary
Action | Format, Examples
------------ | -------------
List | `list`
Todo | `todo TODO`<br />e.g. `todo task 1`
Deadline | `deadline DEADLINE /by BYTIME`<br />e.g. `deadline task 2 /by today`
Event | `event EVENT /at ATTIME`<br />e.g. `event task 3 /at tomorrow`
Delete | `delete INDEX`<br />e.g. `delete 1`
Done | `done INDEX`<br />e.g. `done 1`
Find | `find KEYWORD`<br />e.g. `find task`
Bye | `bye`
