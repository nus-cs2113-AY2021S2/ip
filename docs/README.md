# Friday - User Guide
**Hi, I am Friday, your personal assistant.**  \
\
Friday is a desktop task management applictaion based on the Duke project, which is a greenfield Java project named after the Java mascot Duke. It is designed for use via **Command Line Interface (CLI)**.\


---
## Table of Content
* [Quick start](README.md#quick-start)
* [Features](README.md#features)
    - [Adding a to-do type of task : `todo`](README.md#adding-a-todo-task--todo)
    - [Adding a deadline type of task : `deadline`](README.md#adding-a-deadline-task--deadline)
    - [Adding an event type of task : `event`](README.md#adding-an-event-task--event)
    - [Deleting a task : `delete`](README.md#deleting-a-task--delete)
    - [Setting a task as done : `done`](README.md#marking-a-task-as-done--done)
    - [Viewing the list of tasks : `list`](README.md#viewing-all-the-tasks--list)
    - [Searching tasks with a keyword : `find`](README.md#searching-tasks-by-a-keyword--find)
    - [Exiting Friday : `bye` & `exit`](README.md#exiting-friday--bye)
* [FAQ](README.md#faq)
* [Command summary](README.md#command-summary)

---
## Quick start
This is a cross-platform application, you can run it on any operating system as long as **Java 11** is installed. 
> To verify if you have **Java 11** installed, simply type command `java --version` in your terminal.
1. Download the `ip.jar` file from the [latest release](https://github.com/song0180/ip/releases).
1. Open a new terminal at the folder where the `ip.jar` file is located.
1. Run the applictaion use command `java -jar ip.jar`
1. Refer to the [Features](README.md#features) section below for more info on commands.
## Features 
> **Note:**
> * All commands should be input in `lower_case`. It is always the first word supplied by the user.
> 
>   e.g. `help`, `list`, `todo haha`, etc.
> 
> 
> * Parameters of a command are denoted in `UPPER_CASE`. They must be supplied by the user, otherwise the command will not be recognized.
>   The actual input of the parameters can be in any case. 
>   
>   e.g. in `todo TASK_CONTENT`, `TASK_CONTENT` is a parameter which can be used as `todo Submit My Assignment`.
> 
> 
> * Redundant parameters for single-word commands (such as `help` and `list`) will be ignored. 
> 
>    e.g. if the command `help hahahaha` is input, it is equivalent to `help`.
> 
> * After the appliction starts running, it checks if a `data` folder exists in the same directory where `ip.jar` is located.
>   If the folder does not exist, the application will create a new folder `data`, 
>   which is used to store the `task.txt` file which contains details of the user's tasks.
>
> * The text file `tasks.txt` will be automatically updated in correspondance to the user's task list. 
> 
>   i.e. the content of the text file will be updated after operations such as adding/removing tasks in the task list.

### Feature 1 
Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
