# User Guide

Duke is a desktop app for managing user's tasks based on the Command Line Interface (CLI).

## Quick Start 
 
1. Ensure you have Java 11 or above installed in your Computer.  
1. Download the latest Duke.jar from [here](https://github.com/jalvinchan/ip/releases/download/A-Jar/ip.jar).  
1. Copy the file to the folder you want to use as the home folder for your TaskManager.
1. Double-click the file to start the app.
1. Type the command in the command box and press Enter to execute it. e.g. typing list and pressing Enter will list all the tasks.
1. Some example commands you can try:
    1. [todo](https://jalvinchan.github.io/ip/#todo---add-todo)
    1. [deadline](https://jalvinchan.github.io/ip/#deadline---add-deadline) 
    1. [event](https://jalvinchan.github.io/ip/#event---add-event)
    1. [list](https://jalvinchan.github.io/ip/#list---list-all-tasks)
    1. [done](https://jalvinchan.github.io/ip/#done---mark-task-as-done)
    1. [delete](https://jalvinchan.github.io/ip/#delete---deletes-a-task)
    1. [find](https://jalvinchan.github.io/ip/#find---finds-tasks)
    1. [bye](https://jalvinchan.github.io/ip/#bye---exits-program)
1. Refer to the Usage below for details of each command.

## Usage

### `todo` - Add todo

Add a basic task to the task list.

Format `todo TASK`  
Example of usage:  
`todo CS2113 IP`   
`Got it. I've added this task:`  
`[T][ ] CS2113 IP`  
`Now you have 1 tasks in the list.`  

### `deadline` - Add deadline

Add a task that has to be done by a certain date/time to the task list.

Format `deadline TASK /by DATETIME`  
Example of usage:  
`deadline CS2113 homework /by Mar 5th`   
`Got it. I've added this task:`  
`[D][ ] CS2113 homework (by: Mar 5th)`  
`Now you have 2 tasks in the list.`

### `event` - Add event

Add an event to the task list.

Format `event EVENT /at DATETIME`  
Example of usage:  
`event CS2113 exam /at Apr 9th 4-6pm`  
`Got it. I've added this task:`  
`[E][ ] CS2113 exam (at: Apr 9th 4-6pm)`  
`Now you have 3 tasks in the list.` 

### `list` - List all tasks

Shows a list of all the tasks currently in the task list.

Format: `list`  
Example of usage:  
`list`  
`Here are the tasks in your list:`  
`1: [T][ ] CS2113 IP'  `

### `done` - Mark task as done

Mark a specified task from the task list as done.

Format `done INDEX`  
Example of usage:  
`done 2`   
`Nice! I've marked this task as done:`  
`[T][X] CS2113 IP`


### `delete` - Deletes a task

Deletes the specified task from the list of task

Format `delete INDEX`  
Example of usage:  
`delete 3`   
`Noted. I've removed this task:`  
`[E][ ] CS2113 exam (at: Apr 9th 4-6pm)`  
`Now you have 2 tasks in the list.`

### `find` - Finds tasks

Finds tasks that contains the keywords and list them.

Format `find KEYWORDS`  
Example of usage:  
`find homework`  
`[D][X] CS2113 homework (by: Mar 5th)`  

### `bye` - Exits program

Says goodbye to the program.

Format: `bye`  
Example of usage:  
`bye`  
`Bye. Hope to see you again soon!`
