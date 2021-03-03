# Duke User Guide

Duke is a command line interface (CLI) application to track different types of tasks that the user adds.
If you can type fast, Duke can get do task management faster than traditional Graphical User Interface (GUI) apps.

- [Quick start](#quick-start)
- [Features](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Add a Todo task: `todo`](#add-a-todo-task-todo)
  * [Add a Deadline task: `deadline`](#add-a-deadline-task-deadline)
  * [Add an Event task: `event`](#add-an-event-task-event)
  * [List all the tasks: `list`](#list-all-the-tasks-list)
  * [Complete a task: `done`](#complete-a-task-done)
  * [Delete a task: `delete`](#delete-a-task-delete)
  * [Search task by keyword: `find`](#search-task-by-keyword-find)
  * [Exit the program: `exit`](#exit-the-program-bye)
  * [Save task list](#save-task-list)
- [FAQ](#faq)    
- [Command Summary](#command-summary)
    
## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar from [here](https://github.com/ongweisheng/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open up a terminal/command window at the location where Duke.jar
5. Run the command `java -jar Duke.jar`
6. The app should load as shown below

```
Hello! I'm Duke
What can I do for you? Enter help to view commands.
```
7. Type a command and press enter to continue using the app.
8. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help: `help`

Shows available commands of the app.

Format: `help`

Expected Output:
```
  todo <description> - Add new todo task
  deadline <description> /by <dd/mm/yy and time> - Add new deadline task 
  event <description> /at <dd/mm/yy and time> - Add new event task
  list - List all tasks
  done <index> - Mark task number _ as done
  delete <index> - Delete task number _ 
  find <keyword> - Search for task with keyword in description
  exit - Save tasks list and exit program
```
### Add a Todo task: `todo`

Add a Todo task to the task list.

Format: `todo <description>`

Example: `todo edit ip`

Expected Output:
```
Got it. I've added this task:
  [T][ ] edit ip
Now you have 1 tasks in the list.
```
### Add a Deadline task: `deadline`

Add a Deadline task to the task list.

Format: `deadline <Task Description> /by <dd/mm/yyyy and time>`

Example: `deadline complete ip /by 23/02/2021 2359`

Expected Output:
```
Got it. I've added this task:
  [D][ ] complete ip (by: 23/02/2021 2359)
Now you have 2 tasks in the list.
```

### Add an Event task: `Event`

Add an Event task to the task list.

Format: `event <Task Description> /at <dd/mm/yy and time>`

Example: `event Liverpool v Sheffield Utd /at 01/03/2021 0315`

Expected Output:
```
Got it. I've added this task:
  [E][ ] Liverpool v Sheffield Utd (at: 01/03/2021 0315)
Now you have 3 tasks in the list.
```

### List all the tasks: `list`

List all the tasks in the task list.

Format: `list`

Expected Output:
```
Here are the tasks in your list:
1.[T][ ] edit ip
2.[D][ ] complete ip (by: 23/02/2021 2359)
3.[E][ ] Liverpool v Sheffield Utd (at: 01/03/2021 0315)
```

### Complete a task: `done`

Mark task number _ as done.

Format: `done <index>`

Example: `done 2`

Expected Output:
```
Nice! I've marked this task as done:
  [D][X] complete ip (by: 23/02/2021 2359)
```

### Delete a task: `delete`

Delete task number _ from task list.

Format: `delete <index>`

Example: `delete 2`

Expected Output:
```
Noted. I've removed this task:
  [D][X] complete ip (by: 23/02/2021 2359)
Now you have 2 tasks in the list.
```

### Search task by keyword: `find`

Search all the tasks with the keyword found in the task description.
The keyword is case-sensitive.

Format: `find <keyword>`

Example: `find Liverpool`

Expected Output:
```
Here are the matching tasks in your list:
1.[E][ ] Liverpool v Sheffield Utd (at: 01/03/2021 0315)
```

### Exit the program: `exit`

Exit the app.

Format: `exit`

Expected Output:
```
File saved.
Bye. Hope to see you again soon!
```

### Save task list

Duke save files are automatically updated or created when there is no existing save file
when `exit` command is entered. There is no need to save manually. Save files are also loaded on start up 
of the application if it exists.

## FAQ

**Q1**: Where is the save file stored or created after I exit the program?

> Save file `duke.txt` is stored in the directory you ran the app in.

## Command Summary

Command | Format | Example |
------- | ------- | ------- | 
todo | `todo <description>` | `todo edit ip` |
deadline | `deadline <description> /by <dd/mm/yyyy and time>` | `deadline complete ip /by 23/02/2021 2359` |
event | `event <description> /at <dd/mm/yyyy and time>`      | `event Liverpool v Sheffield Utd /at 01/03/2021 0315` |
list | `list` | `list` |
done | `done <index>` | `done 2` |
delete | `delete <index>` | `delete 2` |
find | `find <keyword>` | `find mushroom` |
exit | `exit` | `exit` |
