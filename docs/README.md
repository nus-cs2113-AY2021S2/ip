# Duke User Guide

Duke is your personal task manager to help you track different types of tasks. Duke is also a command line interface 
application which makes it easy for typing.

- [Quick start](#quick-start)
- [Features](#features)
    * [Add a Todo task: `todo`](#add-a-todo-task-todo)
    * [Add a Deadline task: `deadline`](#add-a-deadline-task-deadline)
    * [Add an Event task: `event`](#add-an-event-task-event)
    * [List all the tasks: `list`](#list-all-the-tasks-list)
    * [Mark a task as done: `done`](#mark-a-task-as-done-done)
    * [Delete a task: `delete`](#delete-a-task-delete)
    * [Search task by keyword: `find`](#search-task-by-keyword-find)
    * [Exit the program: `exit`](#exit-the-program-bye)
    * [Save task list](#save-task-list)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `Duke.jar` from [here](https://github.com/hiongkaihan/ip/releases/tag/A-Release).
3. Copy the jar file to the folder you want to use as the home folder for `Duke`.
4. Open up a terminal/command window at the location where `Duke.jar` is located.
5. Run the command `java -jar Duke.jar`
6. The app should load as shown below with the list of commands.

```
____________________________________________________________
Hello! I'm Duke!!
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

What can I do for you?
List of commands: todo, deadline, event, done, bye
____________________________________________________________
```

7. Type a command and press `Enter` to execute it.
8. Refer to the [Features](#features) below for details of each command.

## Features

### Add a Todo task: `todo`

Add a Todo task to the task list.

Format: `todo <description>`

Example: `todo borrow book`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
```
### Add a Deadline task: `deadline`

Add a Deadline task to the task list.

Format: `deadline <description> /by <deadline date and time>`

Example: `deadline return book /by Monday 2pm`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Monday 2pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Add an Event task: `Event`

Add an Event task to the task list.

Format: `event <Task description> /at <event date and time>`

Example: `event project meeting /at April 1st 3pm`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (at: April 1st 3pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### List all the tasks: `list`

List all the tasks in the task list.

Format: `list`

Expected Output:
```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Monday 2pm)
3.[E][ ] project meeting (at: April 1st 3pm)
____________________________________________________________
```

### Mark a task as done: `done`

Mark task as done.

Format: `done <index>`

Example: `done 2`

Expected Output:
```
Nice! I've marked this task as done:
  [D][✓] return book (by: Monday 2pm)
____________________________________________________________
```

### Delete a task: `delete`

Delete task from the task list.

Format: `delete <index>`

Example: `delete 2`

Expected Output:
```
Noted. I've removed this task:
  [D][✓] return book (by: Monday 2pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Search task by keyword: `find`

Search all the tasks with the keyword found in the task description.

Format: `find <keyword>`

Example: `find project`

Expected Output:
```
The following tasks have been found:
1. [E][ ] project meeting (at: April 1st 3pm)
____________________________________________________________
```

### Exit the program: `exit`

Exit the app.

Format: `exit`

Expected Output:
```
File has been saved!
Bye. Hope to see you again soon!
____________________________________________________________
```

### Save task list

Duke save files are automatically created when there is no existing save file. When `exit` command is entered, 
Duke will automatically `save`, hence there is no need to manually save.

## FAQ

**Q1**: Where is the save file stored or created after I exit the program?

> Save file `duke.txt` is stored in the directory you ran the app in.

## Command Summary

Command | Format | Example |
------- | ------- | ------- | 
todo | `todo <description>` | `todo borrow book` |
deadline | `deadline <description> /by <deadline date>` | `deadline return book /by Monday 2pm` |
event | `event <description> /at <event time>`      | `event project meeting /at April 1st 3pm` |
list | `list` | `list` |
done | `done <index>` | `done 2` |
delete | `delete <index>` | `delete 2` |
find | `find <keyword>` | `find project` |
exit | `exit` | `exit` |