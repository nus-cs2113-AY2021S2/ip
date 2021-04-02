# User Guide

Duke is a **task scheduler to help you manage your tasks, to be used via a Command Line Interface** (CLI). If you can
type fast, Duke allows you to manage your tasks faster than traditional GUI apps.

## Content Page

* [Quick start](#1-quick-start)
* [Features](#2-features)
    * [Adding a task](#21-add-task)
        * todo: `todo`
        * event: `event`
        * deadline: `deadline`
    * [Listing](#22-list): `list`
    * [Marking a task as done](#23-marking-task-as-done): `done`
    * [Deleting a task](#24-deleting-a-task): `delete`
    * [Searching for tasks](#25-searching-for-tasks): `find`
    * [Saving tasks](#26-saving-tasks): `save`
    * [Exit programme](#27-exiting-the-programme): `bye`
* [Command Summary](#3-command-summary)

## 1 Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `ip.jar` file.
3. Copy the file to your desired home folder.
4. In command prompt, use `cd` to navigate to the home folder.
5. Run the `.jar` file in command prompt by entering `java -jar ip.jar`
6. Enter `help` to view available user commands.
7. Refer to the guide below for details of each command.

## 2 Features

### 2.1 Add Task

Add 3 different kinds of tasks to the list.

### Usage:

### `todo` - Add todo task

Add a todo task to the task list.

Example of usage:

`todo borrow book`

Expected outcome:

```
    ____________________________________________________________   
    Got it. I've added this task:  
    [T][X] borrow book  
    Now you have 1 task in the list.  
    ____________________________________________________________
```

### `event` - Add event task

Add an event task in the task list.

Example of usage:

`event read book /at home`

Expected outcome:

```
    ____________________________________________________________   
    Got it. I've added this task:  
    [E][X] read book (at: home)  
    Now you have 2 tasks in the list.  
    ____________________________________________________________
```

### `deadline` - Add deadline task

Add a deadline task in the task list.

Example of usage:

`deadline return book /by next Tuesday`

Expected outcome:

```
    ____________________________________________________________   
    Got it. I've added this task:  
    [D][X] return book (by: next Tuesday)  
    Now you have 3 tasks in the list.  
    ____________________________________________________________
```

### 2.2 List

Show all existing tasks from the list.

### Usage:

### `list` - View list of tasks

Example of usage:

`list`

Expected outcome:

```
    ____________________________________________________________ 
    Here are the tasks in your list:
    1.[T][X] borrow book   
    2.[D][X] return book (by: Aug 11 2000)   
    3.[E][X] read book (at: Aug 3 2000)   
    ____________________________________________________________    
```

### 2.3 Marking Task as Done

Mark a task in the task list as done based on the index of the task.

### Usage:

### `done` - Mark the task as done

Example of usage:

`done 1`

Expected outcome:

```
    ____________________________________________________________   
    Nice! I've marked this task as done:  
    [T][X] borrow book  
    ____________________________________________________________   
```

### 2.4 Deleting a Task

Deletes a task in the task list based on the index of the task.

### Usage:

Example of usage:

`delete 1`

Expected outcome:

```
    ____________________________________________________________   
    Noted. I've removed this task:
    [D][X] borrow book
    Now you have 2 tasks in the list.
    ____________________________________________________________   
```

### 2.5 Searching for tasks

Find a list of tasks in the task list that containing the keyword given.

### Usage:

### `find` - Find tasks by keyword

Example of usage:

`find borrow`

Expected outcome:

```
    ____________________________________________________________    
    Here are the matching tasks in your list:  
    1.[T][V] borrow book  
    ____________________________________________________________    
```

### 2.6 Saving Tasks

Saves tasks added, deleted or completed.

### Usage:

### `save` - Saves tasks.

Example of usage:

`save`

Expected outcome:

```
    ____________________________________________________________    

    ░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗
    ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝
    ██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░
    ██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░
    ╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗
    ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝
    Hope to see you again soon!
    ____________________________________________________________    
```

### 2.7 Exiting the programme

Print goodbye message and end the program.

### Usage:

### `bye` - Exit the program

Example of usage:

`bye`

Expected outcome:

```
    ____________________________________________________________    

    ░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗
    ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝
    ██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░
    ██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░
    ╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗
    ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝
    Hope to see you again soon!
    ____________________________________________________________    
```

## 3 Command Summary

Command | Function | Format
------------ | ------------- | -------------
todo | Add todo task | `todo` **Task Name**
deadline | Add deadline task | `deadline` **Task Name /by XX**
event | Add event task | `event` **Task Name /at XX**
list | View list of tasks | `list`
delete | Delete task | `delete` **Task Index**
done | Mark task as done | `done` **Task Index**
find | Find tasks by keyword |  `find` **Keyword**
bye | Exit program | `bye`