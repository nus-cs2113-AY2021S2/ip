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



### Add Task 
This command add a task into task list for the user.

**deadline task**

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

**event task**

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

**todo task**

Format: `todo [task description]`

Example: `todo submit UG`

Expected Output:
```
 Task added! ^_^
 5: [T] [-] submit UG

```





### Print Task List 
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





### Mark As Done 
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




### Delete Tasks 
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




### Find Task On A date 
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



### Find Task With Keyword 
This command finds tasks containing the keyword for the user.

Format: `keyword [keyword]`

Example: `keyword math`

Expected output when there is no task in the list:
```
  List is empty :o
```

Expected output when there are tasks in the list:
```
5. [E] [-] math tutorial (at: Mar 5 2021)
6. [T] [-] math HW2
 There are 2 task(s)
```
### Exit Command
This command exits the programm for the user.

Format: `bye`

Example: `bye`

Expected Output:
```
 File saved!
 Bye! Hope to see you again soon!
```



### If the command entered is invalid
Baggie shows error message.

Example: `quit`

Expected Output:
```
 Baggie doesn't know what to do with the command D:
```

## Storage
If File  `Baggie.txt` exists in the working directory, Baggie will read from the file and load the saved tasks into task list;
If File  `Baggie.txt` does not exist in the working directory, Baggie will create a new file `Baggie.txt`;
Baggie updates `Baggie.txt` every time user exits the program by entering [Exit Command](#exit-command).



## Contributors
   [baggiiiie](https://github.com/baggiiiie)
