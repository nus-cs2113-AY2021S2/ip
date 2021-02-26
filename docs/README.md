# Friday - User Guide
**Hi, I am Friday, your personal assistant.**  \
\
Friday is a desktop task management applictaion based on the Duke project, which is a greenfield Java project named after the Java mascot Duke. It is designed for use via **Command Line Interface (CLI)**.


---
## Table of Content
* [Quick start](README.md#quick-start)
* [Features](README.md#features)
    - [Adding a to-do type of task : `todo`](README.md#adding-a-to-do-type-of-task--todo)
    - [Adding a deadline type of task : `deadline`](README.md#adding-a-deadline-type-of-task--deadline)
    - [Adding an event type of task : `event`](README.md#adding-an-event-type-of-task--event)
    - [Viewing all tasks currently in the task list : `list`](README.md#viewing-all-tasks-currently-in-the-task-list--list)
    - [Marking a task as done : `done`](README.md#marking-a-task-as-done--done)
    - [Deleting a task from the task list : `delete`](README.md#deleting-a-task-from-the-task-list--delete)
    - [Searching a task in the task list : `find`](README.md#Searching-a-task-in-the-task-list--find)
    - [Exiting the application : `bye` & `exit`](README.md#exiting-the-application--exit-or-bye)
* [Command summary](README.md#command-summary)

---
## Quick start
This is a cross-platform application, you can run it on any operating system as long as **Java 11** is installed. 
> To verify if you have **Java 11** installed, simply type command `java --version` in your terminal.
1. Download the `ip.jar` file from the [latest release](https://github.com/song0180/ip/releases).
1. Open a new terminal at the folder where the `ip.jar` file is located.
1. Run the applictaion use command `java -jar ip.jar`
1. Refer to the [Features](README.md#features) section below for more info on commands.

## Features 
> **Note:**
> * All commands should be input in `lower_case`. It is always the first word supplied by the user.
> 
>   e.g. `help`, `list`, `todo haha`, etc.
> 
> 
> * Parameters of a command are denoted in `UPPER_CASE`. They must be supplied by the user, otherwise the command will not be recognized.
>   The actual input of the parameters can be in any case. 
>   
>   e.g. in `todo TASK_CONTENT`, `TASK_CONTENT` is a parameter which can be used as `todo Submit My Assignment`.
> 
> 
> * Redundant parameters for single-word commands (such as `help` and `list`) will be ignored. 
> 
>    e.g. if the command `help hahahaha` is input, it is equivalent to `help`.
> 
> * After the appliction starts running, it checks if a `data` folder exists in the same directory where `ip.jar` is located.
>   If the folder does not exist, the application will create a new folder `data`, 
>   which is used to store the `task.txt` file which contains details of the user's tasks.
>
> * The text file `tasks.txt` will be automatically updated in correspondance to the user's task list. 
> 
>   i.e. the content of the text file will be updated after operations such as adding/removing tasks in the task list.


### Get help from instructions on how to use this app : `help`
Adds a to-do type of task to the task list.

Format: `help`

Example: `help`

Expected outcome:
```
_______________________________________________________________________________________________________________________________________________________________________________________
 'help'     : Display tips on using this application
 'exit'     : Exit the application
 'bye'      : Exit the application
 'list'     : List all type of tasks you added to your task list
 'done'     : Mark a task as done
	 e.g. 'done 2' will mark the second task as done
 'todo'     : Add a new todo task
	 e.g. 'todo read book' will add "read book" to your task list
 'deadline' : Add a new deadline task
	 e.g. 'deadline read book /by YYYY-DD-MM' will add "read book" to your task list with deadline task by date in format YYYY-DD-MM
 'event'    : Add a new event task
	 e.g. 'event read book /at YYYY-DD-MM' will add "read book" to your task list with an event task at date in format YYYY-DD-MM
 'delete'   : Delete a task using the index
	 e.g. 'delete 1' will delete the task with index of 1
 'find'     : Search tasks using strings
	 e.g. 'find eat' will display a list of tasks that contains the string 'eat'
_______________________________________________________________________________________________________________________________________________________________________________________

```


### Adding a to-do type of task : `todo`
Adds a to-do type of task to the task list.

Format: `todo TASK_CONTENT`

Example: `todo Order food for dinner`

Expected outcome:
```
_____________________________________________________________
A new to-do task is added:
Task content  :Order food for dinner
Now you have 1 task in the list.
_____________________________________________________________
```


### Adding a deadline type of task : `deadline`
Adds a deadline type of task to the task list.

Format: `deadline TASK_CONTENT /by DEADLINE_DATE`

> Note that the `DEADLINE_DATE` parameter must be specified in the format `YYYY-MM-DD`.

Example: `deadline Submit Lab report /by 2021-02-27`

Expected outcome:
```
_____________________________________________________________
A new deadline task is added:
Task content  :Submit Lab report
Task deadline :2021-02-27
Now you have 2 tasks in the list.
_____________________________________________________________
```


### Adding a event type of task : `event`
Adds a event type of task to the task list.

Format: `event TASK_CONTENT /at EVENT_DATE`

> Note that the `EVENT_DATE` parameter must be specified in the format `YYYY-MM-DD`.

Example: `event Graduation ceremony /at 2023-05-10`

Expected outcome:
```
_____________________________________________________________
A new event task is added:
Task content  :Graduation ceremony
Task period   :2023-05-10
Now you have 3 tasks in the list.
_____________________________________________________________
```


### Viewing all tasks currently in the task list : `list`
Displays all tasks currently the task list, along with the task type and task status.

> There are 3 types of task:  
> * `T` - to-do type of task  
> * `D` - deadline type of task  
> * `E` - event type of task  

> There are 2 status of task:  
> * `✖️` - the task is not done yet  
> * `☑️` - the task is marked as done  

Format: `list`

Example: `list`

Expected outcome 1:
```
_____________________________________________________________

This is your current task list:
1. [T] [✖️] Order food for dinner
2. [D] [✖️] Submit Lab report (by: SATURDAY, 27 FEBRUARY 2021)
3. [E] [✖️] Graduation ceremony (at: WEDNESDAY, 10 MAY 2023)
_____________________________________________________________
```
Expected outcome 2 (When there is no task in the task list):
```

```


### Marking a task as done : `done`
Marks a task as done using its index in the task list.

Format: `done TASK_INDEX`

> Note that the `TASK_INDEX` parameter must be an `Integer` within the range of the task list.

Example: `done 1`

Expected outcome:
```
_____________________________________________________________
The task object: 
 | Order food for dinner |
is marked as done.

This is your current task list:
1. [T] [☑️] Order food for dinner
2. [D] [✖️] Submit Lab report (by: SATURDAY, 27 FEBRUARY 2021)
3. [E] [✖️] Graduation ceremony (at: WEDNESDAY, 10 MAY 2023)
_____________________________________________________________
```


### Deleting a task from the task list : `delete`
Deletes a task using its index in the task list.

Format: `delete TASK_INDEX`

> Note that the `TASK_INDEX` parameter must be an `Integer` within the range of the task list.

Example: `delete 1`

Expected outcome:
```
_____________________________________________________________
The task below is successfully removed from your task list:)
	[T] [☑️] Order food for dinner
Now you have 2 tasks in the list.
_____________________________________________________________
```


### Searching a task in the task list : `find`
Searches for a task using that contains the keyword input.

Format: `find KEY_WORD`

> Note that the `KEY_WORD` parameter is case sensitive. The keyword input must match the exact case of the desired task content, otherwise no task will be found.

Example: `find Submit`

Expected outcome:
```
_____________________________________________________________
Here are the matching tasks in your list:
1. [D] [✖️] Submit Lab report (by: SATURDAY, 27 FEBRUARY 2021)
_____________________________________________________________
```


### Exiting the application : `exit` or `bye`
Terminates the application.

Format: `exit` or `bye`

> Note that the `KEY_WORD` parameter is case sensitive. The keyword input must match the exact case of the desired task content, otherwise no task will be found.

Example 1: `exit`  
Example 2: `bye`

Expected outcome:
```
_____________________________________________________________
 Thank you for getting in touch.
 See you next time.
✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ
_____________________________________________________________
```


---
## Command summary

Action | Format, Examples
--- | ---
Help | `help`
Add Todo type of task| `todo TASK_CONTENT` e.g. `todo Order food for dinner`
Add Deadline type of task| `deadline TASK_CONTENT /by DEADLINE_DATE` e.g. `deadline Submit Lab report /by 2021-02-27` 
Add Event type of task| `event TASK_CONTENT /at EVENT_DATE` e.g. `event Graduation ceremony /at 2023-05-10`
List | `list`
Done | `done TASK_INDEX` e.g. `done 1`
Delete | `delete TASK_INDEX` e.g. `delete 1`
Find | `find KEY_WORD` e.g. `find Submit`
Exit | `bye`, `exit`

