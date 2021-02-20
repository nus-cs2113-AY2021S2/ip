# 8K's Task Planner

8K's Task Planner is a Command Line Interface (CLI) app for planning of tasks. 

* Types of tasks
* Quick start
* Features
  * View all commands : `help`
  * List all tasks : `list`
  * Add todo : `todo`
  * Add event : `event`
  * Add deadline : `deadline`
  * Delete task : `delete`
  * Mark as done : `done`
  * Mark as undone : `undo`
  * Search keyword : `find`
  * Exit : `bye`
* Storage
* Command summary
* Credits


## Quick start
Download from here: https://github.com/8kdesign/ip/releases

**Requirement:** 
1. Java 11 and above

**Usage:**
1. Transfer the program to a folder of your liking. (Note: Data from the app will be stored in the same folder)
2. Double-click to start.
3. Type in command and press enter to execute.
For more information about commands, please refer to the list below.


## Types of tasks
8K's Task Planner comes with 3 type of tasks are available:
1. ToDo - Basic task with checkbox.
1. Event - Task with checkbox and information field.
1. Deadline - Task with checkbox and due date field.


## Features

### View all commands : `help`
List out list of commands and how to use them.
| Input | help |
| ---------| -------------------------------------------------------|
| Output | 8K: bye - Exit programme.
    help - Show list of commands.
    list - Show list of saved values.
    todo <name> - Creates new todo task.
    event <name> /at <info> - Creates new event.
    deadline <name> /by <DD-MM-YYYY> - Creates new deadline.
    delete <indices> - Deletes selected tasks.
    done <indices> - Mark selected tasks as done.
    undo <indices> - Mark selected tasks as not done.|

### List all tasks : `list`
List out all the tasks currently in your task list.

### Add todo : `todo`
Adds a todo with specified name into task list.

### Add event : `event`
Adds an event with specified name and information to your task list.

### Add deadline : `deadline`
Adds a deadline with specified name and due date to your task list.

### Delete task : `delete`
Removes task at specified indices from the task list.

### Mark as done : `done`
Marks task at specified indices in the task list as done.

### Mark as undone : `undo`
Marks task at specified indices in the task list as not done.

### Search keyword : `find`
List out all tasks that contains keyword in name. 
Index relative to task list is shown for easier deletion / mark as done.







