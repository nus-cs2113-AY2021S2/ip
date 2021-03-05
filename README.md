# Baggie User Guide

Baggie is an interactive task-manager. He is purely Command-Line Interface (CLI).
## Getting Started
## Quick Start
- Ensure you have Java 11 or above installed in your Computer.
- Download the latest version of `Baggie.jar` file from [here](https://github.com/baggiiiie/ip/releases/tag/v0.1).
- Move the `Baggie.jar` file to the folder of your choice.
- Open terminal(For MacOS)/command prompt(For Windows) and change working directory to that folder. 
- Launch *Baggie* by entering *java -jar Baggie.jar*.

#### Following is the Welcome Page if Baggie is running successfully
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
## Features

### Help Menu 
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



### Help Menu 
This command prints a task list for the user.

Format: `list`

Example: `list`

Expected Output when there is no task in the list:
```
  List is empty :o
```

Expected Output when there are tasks in the list:
```
 1: [D] [X] math tutorial (by: Feb 27 2021)
 2: [T] [-] submit UG
 3: [E] [-] CNY dinner (at: 5pm)
 There are 3 tasks in the list
```
