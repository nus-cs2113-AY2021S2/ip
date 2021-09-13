# User Guide

Duke is a todo-list for managing all of your tasks using the Command Line Interface (CLI).

* [Quick Start](#quick-start)
* [Features](#features)
    + [1. help](#1-help)
    + [2. todo](#2-todo)
    + [3. deadline](#3-deadline)
    + [4. event](#4-event)
    + [5. list](#5-list)
    + [6. delete](#6-delete)
    + [7. done](#7-done)
    + [8. find](#8-find)
    + [9. bye](#9-bye)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have java *11* installed in your computer
2. Download duke.jar and place it in a convenient folder
3. Navigate to the folder in command prompt and run it with 'java -Dfile.encoding=UTF-8 -jar duke.jar'
4. Type 'help' to see the list of commands for Duke

## Features 

Duke can help you with the following:
* `help`: Provides a list of commands and how to use them
* `todo`: Adds a basic todo to your list
* `deadline`: Adds a deadline to your list
* `event`: Adds an event to your list
* `list`: Shows the list of all your current tasks
* `delete`: Deletes a task in your list
* `done`: Marks a task in your list as done
* `find`: Finds a task in your list according to keywords
* `bye`: Ends the application

### 1. `help`
Prints out a list of all available commands and their formats

#### Usage
Format: `help`

### 2. `todo`
Add a todo to the list of tasks 

#### Usage
Format: `todo [task description]`

### 3. `deadline`
Add a deadline to the list of tasks

#### Usage
Format: `deadline [deadline description] /by [time]`

### 4. `event`
Add an event to the list of tasks

#### Usage
Format: `event [event description] /at [time]`

### 5. `list`
Prints out the list of all the tasks

#### Usage
Format: `list`

### 6. `delete`
Deletes an existing task, removing it from the list of tasks

#### Usage
Format: `delete [task number]`

### 7. `done`
Marks an existing task in the list as done

#### Usage
Format: `done [task number]`

### 8. `find`
Prints out existing tasks in the list that contain keywords and phrases inputted by the user

#### Usage
Format: `find [keyword or phrase]`

### 9. `bye`
Exits the application

#### Usage
Format: `bye`

## Command Summary

 Command | Format | Example
------- | ---------- | ------------
 help | `help` | -
 todo | `todo [task description]` | `todo do chores`
 deadline | `deadline [deadline description] /by [time]` | `deadline finish IP /by Thursday 2359`
 event | `event [event description] /at [time]` | `event Hackathon /at March 4th`
 list | `list` | -
 delete | `delete [task number]` | `delete 1`
 done | `done [task number]` | `done 1`
 find | `find [keyword or phrase]` | `find do chores`
 bye | `bye` | -