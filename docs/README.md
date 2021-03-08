# Butler Duke User Guide
By: `Seh Xin Ru` 
Last Updated: `February 2021`

- [Personal Butler Guide](#track-the-mushroom-user-guide)
    * [1. Introduction](#1-introduction)
    * [2. Quick Start in Intellij](#2-quick-start-in-intellij)
    * [3. Features](#3-features)
      + [3.1 Add a Todo task: `todo`](#31-add-a-todo-task-todo)
      + [3.2 Add a Deadline task: `deadline`](#32-add-a-deadline-task-deadline)
      + [3.3 Add an Event task: `event`](#33-add-an-event-task-event)
      + [3.4 List all tasks: `list`](#34-list-all-tasks-list)
      + [3.5 Mark a task as complete: `done`](#35-mark-a-task-as-complete-done)
      + [3.6 Delete a task: `delete`](#36-delete-a-task-delete)
      + [3.7 Search task by keyword: `find`](#37-search-task-by-keyword-find)
      + [3.8 Exit the program: `bye`](#38-exit-the-program-bye)
      + [3.9 Task Storage](#39-task-storage)
    * [4. Frequently Asked Question (FAQ)](#4-faq)
    * [5. Command Summary](#5-command-summary)


## 1. Introduction
Butler Duke at your service! I help track your busy schedule of todos, deadlines and events so that you don't have to! 
Let me know when you have completed one and I will mark them as done. Let me know of the upcoming tasks and I'll keep them in mind. 

## 2. Quick Start in IntelliJ):
Prerequisites: JDK 11, update IntelliJ to the most recent version.

### 2.1. On IntelliJ version 2020.3.1 (latest) and beyond

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Ensure that you have `Java 11` or higher version installed on your computer.
1. Download the latest `Duke.jar` from [here](https://github.com/xseh/ip/releases/tag/A-Release).
1. Move the `Duke.jar` into the folder of your choice. This would be your **base folder**.
1. Run `command prompt` and navigate into the base folder containing the `Duke.jar`.
1. Run the command `java -jar Duke.jar` and you should see the welcome message:

   ```
   -=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Hello! I'm Duke, what can I do for you?
   ```

## 3. Features
### 3.1. Add a todo task: `todo`
This operation adds a todo task to the task list. 

Format: `todo <task description>`

Example: `todo read a book`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
        Got it. I've added this task:
          [T][ ] read a book
        You have 1 task(s) in the list.
```

### 3.2. Add a deadline task: `deadline`
This operation adds a deadline task to the task list. 

Format: `deadline <task description> /by <yyyy-MM-dd>`

Example: `deadline submit homework /by 2021-02-10`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Got it. I've added this task: 
           [D][ ] submit homework (by: 10 Feb 2021)
         You have 1 task(s) in the list.
```

### 3.3. Add an event task: `event`
This operation adds an evvent to the task list. 

Format: `event <task description> /at <yyyy-MM-dd>`

Example: `event book club meeting /at 2021-02-10`

Expected Output:
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Got it. I've added this task: 
           [E][ ] book club meeting (at: 10 Feb 2021)
         You have 1 task(s) in the list.
```

### 3.4. List all tasks: `list`
This operation lists all tasks recorded in the task list. 

Format: `list`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Here are the tasks in your list: 

         1. [T][ ] read a book
         2. [D][ ] submit homework (by: 10 Feb 2021)
         3. [E][ ] book club meeting (at: 10 Feb 2021)
```

### 3.5. Mark a task as complete: `done`
This operation marks a task as completed. 

Format: `done <task number>`

Example: `done 1`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Nice! I've marked this task as done:
         [T][X] read a book
```

### 3.6. Delete a task: `delete`
This operation deletes a task from the task list. 

Format: `delete <task number>`

Example: `delete 2`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Noted. I have removed this task:
         [D][ ] submit homework (by: 10 Feb 2021)
```

### 3.7. Search task by keyword: `find`
This operation searches the task list and shows all the tasks that contains the keyword in the task description. 

Format: `find <keyword>`

Example: `find book`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Here are some of the tasks in your list:

         1. [T][ ] read a book
         2. [E][ ] book club meeting (at: 10 Feb 2021)
```

### 3.8. Exit the program: `bye`
This operation exits the program. 

Format: `bye`

Expected Output: 
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         Bye. Hope to see you again soon!
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
```

### 3.9. Task storage
#### 3.9.1 Load data form existing file
Upon launch, Duke automatically loads stored tasks from existing file. Unless loading is unsuccessful, loading data is **transparent**.  

Expected Output (if unsuccessful):
```
-=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=--=-=-=-=-=-
         File not found, loading new tasklist.
```

#### 3.9.2. Save data to file
Duke automatically saves the task list to a file after every modification (i.e. `add`, `delete`, `done`). 

Name of save file: _**tasks.txt**_

## 4. FAQ
**Q1**: What if I do not have a save file? 
> Duke will generate a new empty task list. When adding new tasks, a new save file will be created. 

**Q2**: Why did Duke not load my safe file?
> The file may have been moved to another location. Check if save file is found in the directory of the application. 

> An error may have been encountered, try relaunching the program. 

## 5. Command Summary

 Command     | Format                                   | Example   
 ----------- | ---------------------------------------- | --------------------------------------
 todo        | `todo <task description>`                  | `todo read a book`
 deadline    | `deadline <task description> /by <yyyy-MM-dd>`   | `deadline submit homework /by 2021-02-10`
 event       | `event <task description> /at <yyyy-MM-dd>`      | `event book club meeting /at 2021-02-10`
 list        | `list`                                     | `list`
 done        | `done <task number>`                       | `done 1`
 delete      | `delete <task number>`                     | `delete 2`
 find        | `find <task number>`                       | `find book`
 bye         | `bye`                                      | `bye`
