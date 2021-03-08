# Da Ali G task helper
By: `Xia Fuxi` Latest update: `24 Feb 2021`

![logo](./ali-g.png)

- [Da Ali G task helper](#da-ali-g-task-helper)
  * [1. Introduction](#1-introduction)
  * [2. Quick Start](#2-quick-start)
  * [3. Features](#3-features)
    + [3.1 Add a Todo task: `todo`](#31-add-a-todo-task-todo)
    + [3.2 Add a Deadline task: `deadline`](#32-add-a-deadline-task-deadline)
    + [3.3 Add an Event task: `event`](#33-add-an-event-task-event)
    + [3.4 Mark a task as done: `done`](#34-mark-a-task-as-done-done)
    + [3.5 List all the tasks: `list`](#35-list-all-the-tasks-list)
    + [3.6 Delete a task: `delete`](#36-delete-a-task-delete)
    + [3.7 Search tasks by keyword: `find`](#37-search-tasks-by-keyword-find)
    + [3.8 Exit the program: `bye`](#38-exit-the-program-bye)
    + [3.9 Task Storage](#39-task-storage)
  * [4. Frequently Asked Question (FAQ)](#4-frequently-asked-questions-faq)
  * [5. Command Summary](#5-command-summary)

## 1. Introduction
Diggity check it, booyakasha! Wagwan, my name be Ali G and I is here to help you manage your everyday life.
Whether it be your weekly ganja shipment or your next murder trial, I is able to help
you remember bare important tings that your not very cleverer little head always be forgettin'. I use only
the most states of the art tekmology, the **command line interface (CLI)**, to help you out.
## 2. Quick Start
Prerequisites: JDK 11, update IntelliJ to the most recent version.
1. Ensure you have `Java 11` installed on your computer.
2. Download the latest `Duke.jar` from [here](https://github.com/fupernova/ip/releases).
3. Move the `Duke.jar` into the folder you wish to run it from.
4. Open up **command prompt** and navigate to the directory where `Duke.jar` resides.
5. Run the command `java -jar Duke.jar` and you should see the welcome message:
```
Wagwan! I is Ali G. West side.
What is we chattin' bout today?
___________________________________________________________
```

## 3. Features

### 3.1 Add a Todo task: `todo`
This command adds a todo task to the task list.

Usage: `todo <Task name>`

Sample input: `todo restring guitar`

Expected output:

```
Wicked. This ting is now on da list.
[T][ ] restring guitar 
You is having 1 task(s) on your list.
___________________________________________________________
```
### 3.2 Add a Deadline task: `deadline`
This command adds a deadline task to the task list.

Usage: `deadline <Task name> /by <YYYY-MM-DD>`

Sample input: `deadline remaster guitar track /by 2020-02-27`

Expected output:
```
Wicked. This ting is now on da list.
[D][ ] remaster guitar track (by: 2020-02-27)
You is having 2 task(s) on your list.
___________________________________________________________
```
### 3.3 Add an Event task: `event`
This command adds an event task to the task list.

Usage: `event <task name> /at <YYYY-MM-DD>`

Sample input: `event live gig /at 2020-03-05`

Expected output:
```
Wicked. This ting is now on da list.
[E][ ] live gig (at: 2020-03-05)
You is having 3 task(s) on your list.
___________________________________________________________
```

### 3.4 Mark a task as done: `done`
This command marks one task on the list as done.

Usage: `done <task number>`

Sample input: `done 2`

Expected output:
```
remaster guitar track set to done. You is well smart innit?
___________________________________________________________
```
### 3.5 List all the tasks: `list`
This command lists all the tasks on the list, including its type and whether it is done.

Usage: `list`

Sample input: `list`

Expected output:
```
Eez are the tings you added to the list
1. [T][ ] restring guitar 
2. [D][✘] remaster guitar track (by: 2020-02-27)
3. [E][ ] live gig (at: 2020-03-05)
___________________________________________________________
```
### 3.6 Delete a task: `delete`
This command removes a task from the list.

Usage: `delete <task number>`

Sample input: `delete 1`

Expected output:
```
This ting has been deleted. I could've done that task, you stupid.
[T][ ] restring guitar 
You is having 2 task(s) on your list.
___________________________________________________________
```
### 3.7 Search tasks by keyword: `find`
This command lists down all tasks containing a specific keyword.

Usage: `find <keyword>`

Sample input: `find gig`

Expected output:
```
Here are all the tings I cound find.
[E][ ] live gig (at: 2020-03-05)
___________________________________________________________
```
### 3.8 Exit the program: `bye`
This command shuts down the chatbot. Ali G be snoozing.

Usage: `bye`

Sample input: `bye`

Expected output:
```
Goodbye, big up yourself, keep it real, respekt.
___________________________________________________________
```
### 3.9 Task storage
#### 3.9.1 Loading data
Ali G is smart enough to keep a text file
storing all tasks added to the list. Information in this file will be loaded every
time you call Ali G to help you.

#### 3.9.2 Saving data
Every time the user sends Ali G to snooze with
`bye`, the text file is updated. 


**If you abruptly exit the program without `bye`, Ali G will 
be very upset at your rude behavior and will not save anything for you.**

#### 3.9.3 Location of saved tasks
The text file is saved under ***tasklogs/tasks.txt***.

## 4. Frequently Asked Questions (FAQ)
**Q: How do I know that my data was loaded and saved?**

**A:** As someone who has shanked many people and gotten away with it, Ali G
is a very sneaky man and you won't see any traces of him loading or saving data.

**Q: I currently do not have a file called tasks.txt. Do I need to create it myself?**

**A:** Of course not! Upon calling up Ali G to help you with your life the first time,
he will create the file for you. He's got everything covered!

## 5. Command Summary

Command     | Format                                   | Example
 ----------- | ---------------------------------------- | --------------------------------------
todo        | `todo <task description>`                  | `todo restring guitar`
deadline    | `deadline <task description> /by <YYYY-MM-DD>`   | `deadline buy new snare /by 2020-04-25`
event       | `event <task description> /at <YYYY-MM-DD>`      | `event song recording /at 2020-03-01`
list        | `list`                                     | `list`
done        | `done <task number>`                       | `done 1`
delete      | `delete <task number>`                     | `delete 2`
find        | `find <task number>`                       | `find 3`
bye         | `bye`                                      | `bye`


###### *All wacky typos in this README are intentional.*