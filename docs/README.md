# User Guide
A brief User Guide for CS2113T IP Project. This user guide explains briefly how to use the commands for the purpose of interaction.
As a task manager, the functionality of the program includes adding, deleting and searching(to name a few features) 
a task(either a todo, deadline or event) to the list.

## How to use this User Guide
The User Guide has two main sections. The first section "features" lists the features available while the second section 
"Usage" explains how the command can be used. The sample usage and expected output are included as well in the usage section
for the purpose of clarity. The last section "Misc" shows the "welcome screen" generated when the program is opened and the
"end screen" generated when the program is terminated.

## Features 

### `Todo`
Adds a todo to the list.

### `Deadline`
Adds a deadline to the list.

### `Event`
Adds an event to the list.

### `Mark Task as Done`
Marks a task as completed on the list.

### `Delete`
Deletes a task from the list.

### `Find`
Finds an item on the list.

### `Show current list`
Views the current list.


## Usage

### `todo task` - adds a todo task to the list ### Feature 1: Todo

Adds a task that is categorized as todo to the list.

Example of usage: 

`todo read book`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Got it. I've added this task:
      [T] [ ] read book
    Now you have 1 tasks in the list.
 
    -------------------------------------------------------------------------------------------------------------------------------

### `deadline task /by date` - adds a deadline task to the list

Adds a task that is categorized as a deadline to the list.

Example of usage:

`deadline return book /by June 6th`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Got it. I've added this task:
      [D] [ ] return book (by: June 6th)
    Now you have 2 tasks in the list.

    -------------------------------------------------------------------------------------------------------------------------------

### `event task /at location` - adds an event task to the list

Adds a task that is categorized as an event to the list.

Example of usage:

`event go to the library /at town`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Got it. I've added this task:
      [E] [ ] go to the library (at: town)
    Now you have 3 tasks in the list.

    -------------------------------------------------------------------------------------------------------------------------------

### `done task_number` - marks a task on the list as done

Marks a task that has been added to the list as done.

Example of usage:

`done 1`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Nice! I've marked this task as done:
      [T] [X] read book

    -------------------------------------------------------------------------------------------------------------------------------

### `delete task_number` - deletes a task from the list

Deletes a task on the list.

Example of usage:

`delete 1`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Noted. I've removed this task:
      [T] [X] read book

    -------------------------------------------------------------------------------------------------------------------------------

### `find keyword` - searches for a task on the list

Searches for a task on the list.

Example of usage:

`find library`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Here are the matching tasks in your list:
    1. [E] [ ] go to the library (at: town)

    -------------------------------------------------------------------------------------------------------------------------------

### `list` - Displays the current list

Displays the current list.

Example of usage:

`list`

Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Here are the tasks in your list:
    1. [D] [ ] return book (by: June 6th)
    2. [E] [ ] go to the library (at: town)

    -------------------------------------------------------------------------------------------------------------------------------


## Misc

### Welcome screen

The text that is shown when the JAR file runs for the first time. If the contents of the list is empty, "The list is empty" will be shown.

Expected outcome:

    Hello from
    ____        _
    |  _ \ _   _| | _____
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\___|

    -------------------------------------------------------------------------------------------------------------------------------
    Hello! I'm Duke
    What can I do for you?

    -------------------------------------------------------------------------------------------------------------------------------
        -------------------------------------------------------------------------------------------------------------------------------
        Here are the tasks in your list:
        1. [D] [ ] return book (by: June 6th)
        2. [E] [ ] go to the library (at: town)

        -------------------------------------------------------------------------------------------------------------------------------

### End screen

The text that is shown when the JAR file is terminated. If the contents of the list is empty, "The list is empty" will be shown.

Example of usage:

`bye`


Expected outcome:

    -------------------------------------------------------------------------------------------------------------------------------
    Here are the tasks in your list:
    1. [D] [ ] return book (by: June 6th)
    2. [E] [ ] go to the library (at: town)

    -------------------------------------------------------------------------------------------------------------------------------








