## Quick Start
1. Make sure you have installed `Java 11` or above in your device.
2. Download the latest `Projects.jar` from [here](https://github.com/L-Irvin/ip/releases).
3. Copy the jar file to a folder, designated as the root folder of your task management app.
4. Run the jar file in your terminal to start the app.
5. Type the command according to the features explained below.

## Features 

### Add Task
Add new tasks to the task list.

Types of task that can be added are:

1. ### `todo` - Create New Todo Task
    
    Command format:
    
    `todo <string: description>`
    
    Example:
    
    `todo Finish Duke Project`

2. ### `deadline` - Create New Deadline Task
    
    Command format:
    
    `deadline <string: description> /by <string: description>`
    
    Example:
    
    `deadline CS2113 Weekly Quiz /by Wednesday`

3. ### `event` - Create New Event Task
    
    Command format:
    
    `event <string: description> /at <string: description>`
    
    Example:
    
    `event CS2113 Tutorial /at Thursday 10am`
    
### List Tasks
List all the tasks inside the task list.

### `list`  - List All Tasks

   Command format:
    
    `list`
    
   Example: 
    
    `list`
    
### Delete Task
Delete one task from the task list.

### `delete/remove` - Delete A Task From List

   Command format:
    
    `delete <integer: task number>`
    or
    `remove <integer: task number>`
    
   Example:
    
    `delete 1`

### Find Tasks
Find tasks that include a specific keyword.

### `find` - Find Tasks From List

   Command format:
    
    `find <string: keyword>`
    
   Example:
    
    `find Homework`
    
### Mark Task
Mark a task as completed.

### `mark/done` - Mark Task as Completed

   Command format:
    
    `done <integer: task number>`
    or
    `mark <integer: task number>`
    
   Example:
    
    `done 2`
    
### Exiting The Program
Exits the application.

### `bye` - Exit Application

   Command format:
    
    `bye`
    
   Example:
    
    `bye`
    
## Command Summary

Action | Format, Examples
-------|-----------------
**Add Todo** | `todo <string: description>` <br> e.g. `todo Finish CS2103T Project`
**Add Deadline** | `deadline <string: description> /by <string: yyyy-mm-dd>` <br> e.g. `deadline CCA Registration /by 2020-09-14`
**Add Event** | `event <string: description> /at <string: yyyy-mm-dd>` <br> e.g. `event Application Release /at 2020-09-18`
**List** | `list`
**Delete** | `delete/remove <integer: task number>` <br> e.g. `delete 1`
**Find** | `find <string: keyword>` <br> e.g. `find Release`
**Mark** | `mark/done <integer: task number>` <br> e.g. `done 2`
**Bye** | `bye`