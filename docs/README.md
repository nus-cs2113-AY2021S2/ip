# Duke User Guide
 _____     
By: Tan Le Jun Latest Update: 4/3/2012


## Content
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Features](#features)
    1. [Todo](#todo---add-todo-task-to-task-list)
    2. [Deadline](#deadline---add-deadline-task-to-task-list)
    3. [Event](#event---add-event-task-to-task-list)
    4. [List](#list---list-out-the-task-list)
    5. [Delete](#delete---delete-task-in-task-list) 
    6. [Find](#find---find-tasks-in-task-list)
    7. [Bye](#bye---exit-the-application)

## Introduction 

Duke is a Command Line Interface (CLI) task manager application. 
It can help you to keep track of your todos, deadlines and events. 
You can easily add and delete tasks with just a few simple commands. 
Duke can also help you to mark your tasks as done once you have successfully completed them.
Need to bring your tasks list around?
Dukes got you covered by saving your task list as a text file in your local storage when you exit and loads it with every start up.

## Getting Started

### Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

On IntelliJ version 2020.3.1 (latest) and beyond
Open IntelliJ (if you are not in the welcome screen, click File > Close Project to close the existing project first)

Import the project into IntelliJ as follows:

Click Open
Select the project directory, and click OK
If there are any further prompts, accept the defaults.
Configure the project to use JDK 11 (not other versions) as explained in here.

After that, locate the src/main/java/Duke.java file, right-click it, and choose Run Duke.main(). If the setup is correct, you should see something like the output below.

````
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke
What can I do for you?
____________________________________________________________

````

## Features 

### `todo` - Add todo task to task list


### Usage

`todo task` - Add a todo task named "task" to task list

Example of usage: 

`todo buy apples`

### Outcome

Success outcome:

```
todo buy apples
Got it. I've added this task:
[T][ ] buy apples
Now you have 5 task in the list.
____________________________________________________________
```

Failed outcome (no task name):
```
todo
OOPS!!! The description of a todo cannot be empty.
____________________________________________________________
```

### `deadline` - Add deadline task to task list


### Usage

`deadline task /by date` - Add a deadline task named "task" to be done by date "date" to task list

Example of usage:

`deadline CS2113T assignment /by Friday 2359`

### Outcome

Success outcome:

```
deadline CS2113T assignment /by Friday 2359
Got it. I've added this task:
[D][ ] CS2113T assignment (by: Friday 2359)
Now you have 1 task in the list.
____________________________________________________________
```

Failed outcome (no task name):
```
deadline
OOPS!!! The description of a deadline cannot be empty.
____________________________________________________________
```

Failed outcome (no date):

### `event` - Add event task to task list

### Usage

`event task /on date` - Add an event task named "task" to be done on date "date" to task list

Example of usage:

`event CS2113T group meeting /on Monday 2.00pm`

### Outcome

Success outcome:

```
event CS2113T group meeting /on Monday 2.00pm
Got it. I've added this task:
[E][ ] CS2113T group meeting (at: Monday 2.00pm)
Now you have 1 task in the list.
____________________________________________________________
```

Failed outcome (no task name):
```
event
OOPS!!! The description of a event cannot be empty.
____________________________________________________________
```

Failed outcome (no date):

### `list` - List out the task list

### Usage

`list` - List the task list in order of entry

Example of usage:

`list`

### Outcome

Success outcome (with tasks in list):

```
event CS2113T group meeting /on Monday 2.00pm
Got it. I've added this task:
[E][ ] CS2113T group meeting (at: Monday 2.00pm)
Now you have 1 task in the list.
____________________________________________________________
```

Success outcome (no tasks in list):

```
list
You have no task! :)
____________________________________________________________
```
### `delete` - Delete task in task list

### Usage

`delete index` - Delete task in task list with index "index"

Example of usage:

`delete 1`

### Outcome

Success outcome:

```
delete 1
Noted. I've removed this task: 
[T][ ] buy apples
Now you have 2 tasks in the list.
____________________________________________________________
```

Failed outcome (no task with requested index):

```
delete 3
Error: no such task index
____________________________________________________________
```
Failed outcome (no index given):
```
delete
OOPS!!! You missed out the index of the task you want to delete.
____________________________________________________________
```

### `find` - Find tasks in task list

### Usage

`find keyword` - Find tasks in task list containing word "keyword"

Example of usage:

`find apple`

### Outcome

Success outcome:

```
find apples
1: [T][ ] buy apples
2: [E][ ] apples expiring (at: Sunday)
____________________________________________________________
```

Failed outcome (no task with requested keyword):

```
find oranges
There is no such task!
____________________________________________________________
```
Failed outcome (no keyword given):
```
find
OOPS!!! You forgot to give the keyword!!!
____________________________________________________________
```

### `bye` - Exit the application

### Usage

`bye` - Exit the application

Example of usage:

`bye`

### Outcome

Success outcome:

```
bye
Bye. Hope to see you again soon!
____________________________________________________________
```

## Thank you for reading!