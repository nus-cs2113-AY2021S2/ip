# User Guide

Duke is a personal assistant chatbot that helps a person to keep track of various tasks via a Command Line Interface (CLI).

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ip.jar from **lihaoyangML/ip**'s Github Page.
3. Copy the file to the folder you want to use as the home folder.
4. Launch Command Prompt and goto the folder.
5. Type *java -jar <filename>.jar* to launch the program.
6. Refer to the instruction that is displayed automatically when the program is launched to start using the program. 

## Features

### Listing all tasks: list
Shows a list of all tasks in Duke.
Format: list

### Marking a task as completed: done
Marks an exisitng task as completed.
Format: done x
Example: 
* done 1

### Deleting a task: delete
Deletes an existing task.
Format: delete x
Example: 
* delete 2

### locate tasks by keyword: find
Finds tasks which the task descriptions contain a particular keyword.
Format: find keyword:
Example:
* find task 3

### Adding a ToDo task: todo
Adds a task without a specific deadline or duration.
Format: todo TASK_DESCRIPTION
Example:
* todo book out on sunday

### Adding a Deadline task: deadline
Adds a task with a specific deadline.
Format: deadline TASK_DESCRIPTION /KEYWORD DATE_TIME_DESCRIPTION
Example:
* deadline Finish ip /by tmr at 23:59 
* deadline I must finish my IP /by tmr

### Adding an Event task: event
Adds a task with a specific duration.
Format: event TASK_DESCRIPTION /KEYWORD DATE_TIME_DESCRIPTION
Example:
* event CS2113 meeting /from 2/22/2021 2pm to 3pm
* event meeting /from 2-3

### Exiting the program : bye
Exits the program.
Format: bye


## Demonstration
Here is a short demonstration on how to use the prorgam.

**todo sample task 1**
Got it. I've added this task:
  [T][0] sample task 1
Now you have 1 tasks in the list.

**event sample task 2 /from xxx-xxx**
Got it. I've added this task:
  [E][0] sample task 2  (from: xxx-xxx)
Now you have 2 tasks in the list.

**deadline sample task 3 /by xxx**
Got it. I've added this task:
  [D][0] sample task 3  (by: xxx)
Now you have 3 tasks in the list.

**list**
1.[T][0] sample task 1
2.[E][0] sample task 2  (from: xxx-xxx)
3.[D][0] sample task 3  (by: xxx)

**done 1**
Nice! I've marked this task as done: 
[1] todo sample task 1

**list**
1.[T][1] sample task 1
2.[E][0] sample task 2  (from: xxx-xxx)
3.[D][0] sample task 3  (by: xxx)

**delete 2**
Noted. I've removed this task:
  [0] event sample task 2
Now you have 2 tasks in the list.

**list**
1.[T][1] sample task 1
2.[D][0] sample task 3  (by: xxx)

**find task 3**
Here are the matching tasks in your list:
2.[D][0] sample task 3  (by: xxx)

**bye**
Bye. Hope to see you again soon!


