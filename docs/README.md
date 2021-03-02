# User Guide to Duke

## 1. Introduction

This is a CS2113T project for a greenfield Java project. It's named after the Java mascot Duke. Given below are
instructions on how to use it.

## 2. Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### On IntelliJ version 2020.3.1 (latest) and beyond

Open IntelliJ (if you are not in the welcome screen, click File > Close Project to close the existing project first)

Import the project into IntelliJ as follows:

Click Open Select the project directory, and click OK If there are any further prompts, accept the defaults. Configure
the project to use JDK 11 (not other versions) as explained in here.

After that, locate the src/main/java/Duke.java file, right-click it, and choose Run Duke.main(). If the setup is
correct, you should see something like the output below.

![Duke Greeting Image](/docs/img.png)

## 3. Task Management Features

Using Duke, you can easily and effectively manage your tasks, deadlines and personal events. This section aims to
introduce the various important features of Duke. However, before we do that there are some command formats that will be
used from here on out. They are:

### Command Formats

|`/add` | A grey highlight refers to a command keyword that will be taken from the command line, at textbox at the bottom of the GUI. |
:---:|---


|\<task description>| Texts enclosed by angular brackets are fields that have to be replaced with the user input. For example, <task description> can be “read a book”, while <time> can be “12:00”.|
:---:|---
### 3.1 Adding tasks to Duke

Since Duke is task list checker, the first thing you would want to do is to add your tasks for the next few days. Duke
is able to set up the following 3 types of Tasks, To-do, Event and Deadline.

### 3.1.1 Adding a To-Do task: `todo`

Duke can add in specific commands to add in a todo task. In order to do so, you must issue commands in the command line
at the bottom.

The syntax for adding a todo task to the list is as follows:

    todo <task description>

On issuing this command in the command-lie text box at the bottom, a task will be added to the list.

### 3.1.2 Adding a Event task: `event`

Duke can add in specific commands to add in an event. In order to do so, you must issue commands in the command line at
the bottom.

The syntax for adding a todo task to the list is as follows:

    event <task description> /at <date> <time>

On issuing this command in the command-lie text box at the bottom, a task will be added to the list.

Elaboration: Date format is YYYY-MM-DD and time format is HH:mm. Where D stands for day, M stands for month, Y stands
for year, H stands for hour in 24-hour format and m stands for minutes. Uses the /at keyword.

### 3.1.3 Adding a Deadline task: `deadline`

Duke can add in specific commands to add in a task with a deadline. In order to do so, you must issue commands in the
command line at the bottom.

The syntax for adding a todo task to the list is as follows:

    deadline <task description> /by <date> <time>

On issuing this command in the command-lie text box at the bottom, a task will be added to the list.

Elaboration: Date format is YYYY-MM-DD and time format is HH:mm. Where D stands for day, M stands for month, Y stands
for year, H stands for hour in 24-hour format and m stands for minutes. Uses the /by keyword.

Some trivial examples can be seen below:

* todo read book (ToDo)

* event Team meeting /at 2021-1-3 15:00 (Event)

* deadline Homework /by 2021-12-12 23:59 (Deadline)

### 3.2 Delete a task: `delete`

Sometimes you might want to delete a task because you no longer have to perform it. In order to do so, a `delete`
command can be issued in the command line.

The syntax for adding a task to the list is as follows:

    delete <index of task>

Executing a `delete` command:

    1. Look at the index of the task you want to delete.
    2. Type in the above command.
    3. Press Enter

The command will disappear from the list and the index gets readjusted.

### 3.3 Marking tasks as done: `done`

If you ended up completing the task, you might want to mark the task as done. Issue the `done` command to do so.

The syntax of the command is as follows:

    done <index of task>

Executing a `done` command:

    1.	Look at the index of the task you want to mark.
    2.	Type in the above command.
    3.	Press Enter.

### 3.4 Finding tasks: `find`

When dealing with multiple tasks, it is hard to find older ones even if you sort it in a particular way. To find tasks,
you can use the `find` command:

The syntax of the command is as follows:

    find <keyword>

The command takes in your input <keyword> which is present in the task you want to find. Currently, this feature is able
to find tagged words, dates, time and words in the task description.

Executing the `find` command:

    1.	Type find <keyword>. For example, we type find Dec and press Enter.
    2.	The list of tasks that contains 'Dec' will be shown on the screen.

### 3.4 Listing all the tasks: `list`

When dealing many tasks, you would want to see all of your tasks in a list. To see your task list, you can use
the `list` command:

Executing the `list` command:

    1.	Type list.
    2.	All tasks in list should be printed with respective task headers.

### 3.5 Exiting Duke: `bye`

This command would cause Duke programme to exit and end.

## 4. FAQ

Q: Where can I install Java 11 from?

A: Java 11 can be downloaded from the oracle
website. https://www.oracle.com/technetwork/java/javase/downloads/index.html

## 5. Command Summary

Adding tasks:

* todo <task description> (ToDo)
    * Example: todo add task 1

* event <task description> /at <date> <time> (Event)
    * Example: event planning for task 2 /at 2021-12-12 18:00

* deadline <task description> /by <date> <time> (Deadline)
    * Example: deadline complete task 3 /by 2021-12-12 18:00

Deleting tasks:

* delete <index of task>
    * Example: delete 3

Finding tasks:

* find <index of task> (queries the task list for any task containing the given word; can be in the task description,
  tags, time or date)
    * Example: find 3

Completing tasks:

* done <index of task>  (marks task as done in the given index)

    * Example: done 3

List:

* list  (shows a list of all the tasks in the task list)