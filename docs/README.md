# User Guide
A brief User Guide for CS2113T IP Project. This user guide explains briefly how to use the commands for the purpose of interaction.
As a task manager, the functionality of the JAR file includes adding, deleting and searching(to name a few features) a task(either a todo, deadline or event) to the list.

## Features 

### Feature 1: Todo
Adds a todo to the list.

### Feature 2: Deadline
Adds a deadline to the list.

### Feature 3: Event
Adds an event to the list.

### Feature 4: Mark Task as Done
Marks a task as completed on the list.

### Feature 5: Delete
Deletes a task from the list.

### Feature 6: Find
Finds an item on the list.

### Feature 7: Show current list
Views the current list.


## Usage

### `todo task` - adds a todo task to the list

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





