# User Guide

ip is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). Duke is a versatile task manager and it is able to manage various types of tasks that you may want to keep track of.

1. [Quick start](#quick-start)
2. [Features](#features)
   * [Viewing help](#viewing-help)
   * [Adding a todo task](#adding-a-todo-task)
   * [Adding a deadline](#addding-a-deadline)
   * [Adding an event](#addding-an-event)
   * [Marking a task as done](#marking-a-task-as-done)
   * [Listing all tasks](#listing-all-tasks)
   * [Deleting a task](#deleting-a-task)
   * [Searching for tasks by a keyword](#searching-for-tasks-by-a-keyword)
3. [Command summary](#command-summary)
---
## Quick start
1. Ensure that you have Java 11 or above installed in your computer.
2. Download the latest version of [ip.jar] from here.
3. Copy the file to the folder you want to use as the home folder for your ip.
4. Double-click the file to start the app. A terminal console similar to the below should appear in a few seconds.    
   ![Screenshot 2021-03-02 at 12 34 35 AM](https://user-images.githubusercontent.com/60348727/109528031-4185eb00-7aef-11eb-9f0b-270c2e2cae8e.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
   Some example commands you can try:
   * `list`: Lists all tasks.
   * `deadline submit coding assignment /by Monday night`: Add a deadline to submit coding assignment by Monday night.
   * `delete 1`: Deletes the first task shown in the current list.
   * `bye`: Exits the app.
6. Refer to the Features below for details of each command.
---
## Features

### Viewing help : `help`
Shows a message with the format of possible commands that ip can handle.  
Outcome:  
![Screenshot 2021-03-02 at 12 59 43 AM](https://user-images.githubusercontent.com/60348727/109531210-9414d680-7af2-11eb-848b-f5f04c8bbd62.png)  
Format: `help`

### Adding a todo task : `todo`
Adds a todo task to the tasks list.   
Example: `todo make coffee`  
Outcome:  
![Screenshot 2021-03-02 at 1 35 34 AM](https://user-images.githubusercontent.com/60348727/109535483-96c5fa80-7af7-11eb-8623-2b3393f1c3c7.png)  
Format: `todo TASK_DESCRIPTION`  
* Note that the todo task is marked with a [T]

### Adding a deadline : `deadline`
Adds a deadline to the tasks list.  
Example: `deadline finish project /by monday`  
Outcome:  
![Screenshot 2021-03-02 at 1 36 45 AM](https://user-images.githubusercontent.com/60348727/109535636-c07f2180-7af7-11eb-839f-114d3efa6048.png)  
Format: `deadline TASK_DESCRIPTION /by WHEN`  
* Note that the deadline is marked with a [D]

### Adding an event : `event`
Adds an event to the tasks list.  
Example: `event important meeting /at tuesday morning`  
Outcome:  
![Screenshot 2021-03-02 at 1 37 22 AM](https://user-images.githubusercontent.com/60348727/109535714-d68ce200-7af7-11eb-9c11-a8c011dc8fa0.png)  
Format: `event TASK_DESCRIPTION /at WHEN`  
* Note that the event is marked with a [E]

### Marking a task as done : `done`
Marks the specified task as done.  
Example: `done 2`  
Outcome:  
![Screenshot 2021-03-02 at 1 38 08 AM](https://user-images.githubusercontent.com/60348727/109535818-f2908380-7af7-11eb-82db-d6b7c3f578bd.png)  
Format: `done TASK_NUMBER` 
* Note that the [✘] has changed to [✓] for the specified task

### List all tasks : `list`
Lists all tasks.  
Outcome:  
![Screenshot 2021-03-02 at 1 38 39 AM](https://user-images.githubusercontent.com/60348727/109535874-050abd00-7af8-11eb-99e6-2ee01e6afd58.png)  
Format: `list`

### Deleting a task : `delete`
Deletes the specified task from the list.  
Example: `delete 1`
Outcome:  
![Screenshot 2021-03-02 at 1 39 07 AM](https://user-images.githubusercontent.com/60348727/109535925-15bb3300-7af8-11eb-9e0b-0c0f101e7df3.png)  
Format: `delete TASK_NUMBER`

### Searching for tasks by a keyword : `find`
Finds all tasks that contain the specified keyword from the list.  
Example: `find important`
Outcome:  
![Screenshot 2021-03-02 at 1 39 29 AM](https://user-images.githubusercontent.com/60348727/109535980-22d82200-7af8-11eb-86f5-c1ce6485a7e5.png)  
Format: `find KEYWORD`
---
## Command summary

|  Commands  |               Format               |                    Example                  |
| ---------- |------------------------------------| --------------------------------------------|
| `help`     | help                               | -                                           |
| `todo`     | todo TASK_DESCRIPTION              | todo make coffee                            |
| `deadline` | deadline TASK_DESCRIPTION /by WHEN | deadline finish project /by monday          |
| `event`    | event TASK_DESCRIPTION /at WHEN    | event important meeting /at tuesday morning |
| `done`     | done TASK_NUMBER                   | done 2                                      |
| `list`     | list                               | -                                           |
| `delete`   | delete TASK_NUMBER                 | delete 1                                    |
| `find`     | find KEYWORD                       | find important                              |

[ip.jar]: https://github.com/Rizavur/ip/releases/tag/A-Jar
