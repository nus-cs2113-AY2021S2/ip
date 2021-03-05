# Baggie User Guide

Baggie is an interactive task-manager. He is purely based on Command-Line Interface (CLI). With his help, you could be event more productive!
## Getting Started

## Table of Contents
  * [Table of Contents](#table-of-contents)
  * [1. Before Start](#1-before-start)
  * [2. Features](#2-features)
    + [2.1 Help Menu](#21-help-menu)
    + [2.2 Add Task](#22-add-task)
      - [Deadline Task](#deadline-task)
      - [Event Task](#event-task)
      - [Dodo Task](#todo-task)
    + [2.3 Print Task List](#23-print-task-list)
    + [2.4 Mark As Done](#24-mark-as-done)
    + [2.5 Delete Tasks](#25-delete-tasks)
    + [2.6 Find Task On A date](#26-find-task-on-a-date)
    + [2.7 Find Task With Keyword](#27-find-task-with-keyword)
    + [2.8 Exit Command](#28-exit-command)
    + [2.9 If the command entered is invalid](#29-if-the-command-entered-is-invalid)
  * [3. Command Overview](#3-command-overview)
  * [4. Storage](#4-storage)
  * [5. Contributors](#5-contributors)

## 1. Before Start
- Ensure you have Java 11 or above installed in your Computer.
- Download the latest version of `Baggie.jar` file from [here](https://github.com/baggiiiie/ip/releases/tag/v0.1).
- Move the `Baggie.jar` file to the folder of your choice.
- Open terminal(For MacOS)/command prompt(For Windows) and change working directory to that folder. 
- Launch *Baggie* by entering *java -jar ip.jar*.

**Following is the Welcome Page if Baggie is running successfull
   ```
  
██████   █████   ██████   ██████  ██ ███████ 
██   ██ ██   ██ ██       ██       ██ ██      
██████  ███████ ██   ███ ██   ███ ██ ██████ 
██   ██ ██   ██ ██    ██ ██    ██ ██ ██      
██████  ██   ██  ██████   ██████  ██ ███████ 
____________________________________________________________
 Hello! I'm Baggie
 Type 'help' to see what i could do for you
____________________________________________________________

   ```
## 2. Features

### 2.1 Help Menu 
This command prints a help menu for the user.

Format: `help`

Example: `help`

Expected Output:
```
 Here's what I could do for you ^_^
 help: print help menu
 list: see saved tasks in the list
 done: mark a task in the list as done
 delete: delete a task from the list
 date: search saved tasks on a specific day
 find: search saved tasks by keywords
 bye: exit Baggie
 how to add tasks into the list:
 	todo-> format: todo [task]
 	event-> format: event [task] / [time]
 	deadline-> format: deadline [task] / [time]
 	[tip: enter date as YYYY-MM-DD to help Baggie understand better!]
```



### 2.2 Add Task 
This command add a task into task list for the user.

#### Deadline Task

Format: `deadline [task description] / [YYYY-MM-DD]`

Example: `deadlilne submit UG / 2021-03-05`

Expected Output:
```
 Task added! ^_^
 5: [D] [-] submit UG (by: Mar 5 2021)
```

Expected output when date is not in the right format:
```
 Task added! ^_^
 5: [D] [-] submit UG (by: 2021 03 05)
```

#### Event Task

Format: `event [task description] / [YYYY-MM-DD]`

Example: `event CNY dinner / 2021-02-11`

Expected Output:
```
 Task added! ^_^
 5: [E] [-] event CNY dinner (at: Feb 11 2021)
```

Expected output when date is not in the right format:
```
 Task added! ^_^
 5: [E] [-] event CNY dinner (at: 2021 02 11)
```

#### Todo Task

Format: `todo [task description]`

Example: `todo submit UG`

Expected Output:
```
 Task added! ^_^
 5: [T] [-] submit UG
```





### 2.3 Print Task List 
This command prints a task list for the user.

Format: `list`

Example: `list`

Expected output when there is no task in the list:
```
  List is empty :o
```

Expected output when there are tasks in the list:
```
 1: [D] [X] math tutorial (by: Feb 27 2021)
 2: [T] [-] submit UG
 3: [E] [-] CNY dinner (at: 5pm)
```





### 2.4 Mark As Done 
This command marks a task in the list as done.

Format: `done [index]`

Example: `done 1`

Expected output when the task is in the list:
```
 Yay! This task is done!
 5: [D] [X] submit UG (by: Mar 5 2021)
```

Expected output when the task is not in the list:
```
 You don't have Task 5 in your list ^_^ 
```
Expected output when the task is already done:
```
The task is already done ^_^
```




### 2.5 Delete Tasks 
This command deletes a task from list for the user.

Format: `delete [index]`

Example: `delete 5`

Expected output when the task is in the list:
```
 5: [D] [X] submit UG (by: Mar 5 2021)
 Yay! This task is deleted!
```

Expected output when the task is not in the list:
```
 You don't have Task 5 in your list ^_^ 
```




### 2.6 Find Task On A date 
This command finds tasks on a specific date for the user.

Format: `date [YYYY-MM-DD]`

Example: `date 2021-03-05`

Expected Output:
```
 4. [D] [-] submit UG (by: Mar 5 2021)
 6. [E] [-] math tutorial (at: Mar 5 2021)
 There are 2 task(s)
```

Expected output when the date is not in the right format:
```
 Baggie could find any tasks related to 2021 03 05
```



### 2.7 Find Task With Keyword 
This command finds tasks containing the keyword for the user.

Format: `keyword [keyword]`

Example: `keyword math`

Expected Output:
```
5. [E] [-] math tutorial (at: Mar 5 2021)
6. [T] [-] math HW2
There are 2 task(s)
```

Expected output when there is no task containing keyword in the list:
```
 Baggie could find any tasks related to math
```

### 2.8 Exit Command
This command exits the programm for the user.

Format: `bye`

Example: `bye`

Expected Output:
```
 File saved!
 Bye! Hope to see you again soon!
```



### 2.9 If the command entered is invalid
Baggie shows error message.

Example: `quit`

Expected Output:
```
 Baggie doesn't know what to do with the command D:
```

## 3. Command Overview
 Command | Format | Example 
---------|--------|---------
 Help Menu|`help`|`help`
 Print List|`list`|`list`
 Mark As Done|`done [index]`|`done 1`
 Delete Task|`delete [index]`|`delete 1`
 Find With Date|`date [YYYY-MM-DD]`|`date 2021-03-05`
 Find With Keyword|`keyword [keyword]`|`keyword math`
 Add Deadline Task|`deadline [task]/[time]`|`deadline submit UG/2021-03-05`
 Add Event Task|`event [task]/[time]`|`event CNY dinner/2021-02-11`
 Add Todo Task|`todo [task]`|`todo submit UG`
 Exit Program|`bye`|`bye`


 


## 4. Storage
If File  `Baggie.txt` exists in the working directory, Baggie will read from the file and load the saved tasks into task list;
If File  `Baggie.txt` does not exist in the working directory, Baggie will create a new file `Baggie.txt`;
Baggie updates `Baggie.txt` every time user exits the program by entering [Exit Command](#exit-command).



## 5. Contributors
   [baggiiiie](https://github.com/baggiiiie)
