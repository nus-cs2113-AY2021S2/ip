# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). Duke is a versatile task manager and it is able to manage various types of tasks that you may want to keep track of.

1. [Quick start](#quick-start)
2. [Features](#features)
   * [Viewing help](#viewing-help)
   * [Adding a todo task](#adding-a-todo-task)
   * [Adding a deadline](#adding-a-deadline)
   * [Adding an event](#adding-an-event)
   * [Marking a task as done](#marking-a-task-as-done)
   * [Listing all tasks](#listing-all-tasks)
   * [Deleting a task](#deleting-a-task)
   * [Searching for tasks by a keyword](#searching-for-tasks-by-a-keyword)
   * [Exit](#exit)
3. [Command summary](#command-summary)

---

## Quick start
1. Ensure that you have Java 11 or above installed in your computer.  
2. Download the latest version of [ip.jar] from here.  
3. Copy the file to the folder you want to use as the home folder for your ip.  
4. Open a terminal console and navigate to the folder where the downloaded jar file was placed.  
5. In your terminal, type `java -jar ip.jar` and press enter. The terminal should look similar to the image shown below.    
   ![Screenshot 2021-03-02 at 12 34 35 AM](https://user-images.githubusercontent.com/60348727/109528031-4185eb00-7aef-11eb-9f0b-270c2e2cae8e.png)  
6. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
   Some example commands you can try: 
   * `list`: Lists all tasks.  
   * `deadline submit coding assignment /by Monday night`: Add a deadline to submit coding assignment by Monday night.  
   * `delete 1`: Deletes the first task shown in the current list. 
   * `bye`: Exits the app.  
7. Refer to the [Features](#features) below for details of each command.  

---

## Features

### Viewing help
### `help`  
Shows a message with the format of possible commands that ip can handle.  
Outcome:  
![Screenshot 2021-03-02 at 12 59 43 AM](https://user-images.githubusercontent.com/60348727/109531210-9414d680-7af2-11eb-848b-f5f04c8bbd62.png)  
Format: `help`

### Adding a todo task  
### `todo`  
Adds a todo task to the tasks list.   
Example: `todo make coffee`  
Outcome:  
![Screenshot 2021-03-03 at 10 18 37 PM](https://user-images.githubusercontent.com/60348727/109818842-681e6000-7c6e-11eb-8f91-8e99e9d5d213.png)  
Format: `todo TASK_DESCRIPTION`  
* Note that a todo task is marked with a [T]

### Adding a deadline  
### `deadline`  
Adds a deadline to the tasks list.  
Example: `deadline finish project /by monday`  
Outcome:  
![Screenshot 2021-03-03 at 10 19 36 PM](https://user-images.githubusercontent.com/60348727/109818971-8ab07900-7c6e-11eb-8cef-77d919be61c7.png)  
Format: `deadline TASK_DESCRIPTION /by WHEN`  
* Note that a deadline is marked with a [D]

### Adding an event  
### `event`  
Adds an event to the tasks list.  
Example: `event important meeting /at tuesday morning`  
Outcome:  
![Screenshot 2021-03-03 at 10 21 11 PM](https://user-images.githubusercontent.com/60348727/109819203-c3e8e900-7c6e-11eb-8f54-be21bdf9c9d7.png)  
Format: `event TASK_DESCRIPTION /at WHEN`  
* Note that an event is marked with a [E]

### Marking a task as done  
### `done`  
Marks the specified task as done.  
Example: `done 2`  
Outcome:  
![Screenshot 2021-03-03 at 10 21 46 PM](https://user-images.githubusercontent.com/60348727/109819271-d82ce600-7c6e-11eb-8959-bb2e1ac913d5.png)    
Format: `done TASK_NUMBER` 
* Note that the [ ] has changed to [X] for the specified task

### Listing all tasks
### `list`  
Lists all tasks.  
Outcome:  
![Screenshot 2021-03-03 at 10 22 26 PM](https://user-images.githubusercontent.com/60348727/109819361-f0046a00-7c6e-11eb-815e-44458accb809.png)  
Format: `list`

### Deleting a task  
### `delete`  
Deletes the specified task from the list.  
Example: `delete 1`  
Outcome:  
![Screenshot 2021-03-03 at 10 23 40 PM](https://user-images.githubusercontent.com/60348727/109819520-1c1feb00-7c6f-11eb-921d-b390f0f914ef.png)  
Format: `delete TASK_NUMBER`

### Searching for tasks by a keyword  
### `find`  
Finds all tasks that contain the specified keyword from the list.  
Example: `find important`  
Outcome:  
![Screenshot 2021-03-03 at 10 24 19 PM](https://user-images.githubusercontent.com/60348727/109819585-335ed880-7c6f-11eb-8eeb-e9d1a4d8702d.png)  
Format: `find KEYWORD`  


### Exit  
### `bye`
Exits the app.
Outcome:  
![Screenshot 2021-03-02 at 10 47 13 AM](https://user-images.githubusercontent.com/60348727/109589064-b8999e80-7b44-11eb-88a6-13f8bceb85d7.png)  
Format: `bye`

---

## Command summary  

|  Commands  |               Format               |                      Example                    |
| ---------- | ---------------------------------- | ----------------------------------------------- |
| `help`     | help                               | -                                               |
| `todo`     | todo TASK_DESCRIPTION              | todo *make coffee*                              |
| `deadline` | deadline TASK_DESCRIPTION /by WHEN | deadline *finish project* /by *monday*          |
| `event`    | event TASK_DESCRIPTION /at WHEN    | event *important meeting* /at *tuesday morning* |
| `done`     | done TASK_NUMBER                   | done *2*                                        |
| `list`     | list                               | -                                               |
| `delete`   | delete TASK_NUMBER                 | delete *1*                                      |
| `find`     | find KEYWORD                       | find *important*                                |
| `bye`      | bye                                | -                                               |

[ip.jar]: https://github.com/Rizavur/ip/releases/tag/A-Jar
