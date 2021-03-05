# User Guide
_**by Wong Li Ping**_

## Introduction
This project is named after the Java mascot Duke. Duke, is a command line 
program to keep track of your tasks in a list.

## Features
Duke allows you to add three different type of tasks in your list:<p>
Todo - tasks you plan to do. This is denoted as `[T]` in your task list!<br>
Deadline - tasks that contains a deadline. This is denoted as `[D]` in your task list!<br>
Event - event you wish to attend. This is denoted as `[E]` in your task list!<br>

Duke saves your list of tasks every time you input your command `[Refer to Commands Section to learn more!]` and
saves your progress even after you exit the program!

Duke can handle simple error handling too! 
## Commands
#### Below is a table that illustrates the list of commands available.
Command | Functionality
--------|---------------
todo \<task\> | Adds a ToDo task object to the list
deadline \<task\> <br>/by <date/time> | Adds a Deadline task object to the list
event \<task\> <br>/at <venue/date> | Adds a Deadline task object to the list
list | Prints the tasks in a list with indexing
delete \<task index\> | Removes the task at specified task index 
done \<task index\> | Mark task at specified task index with a `X`
find \<keyword\> | Find and print tasks that contains the keyword
bye | Exits and terminates Duke. Progress is saved.


## Usage

### `todo` - Adds a ToDo task object to the list

Example of usage: 

`todo cs2113t homework`

Expected outcome:

`Got it. I've added this task:`<br>
`[T][ ] cs2113t homework`<br>
`Now you have 1 tasks in the list.`

### `deadline` - Adds a Deadline task object to the list

Example of usage:

`deadline cs2113t iP /by 5/3/2021 2359`

Expected outcome:

`[D][ ] cs2113t iP (by: 5/3/2021 2359)`<br>
`Now you have 2 tasks in the list.`<br>
`Got it. I've added this task: `

###### Note: event command usage is similar.


### `list` - Prints the tasks in a list with indexing

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`<br>
`1.[T][ ] cs2113t homework`<br>
`2.[D][ ] cs2113t iP (by: 5/3/2021 2359)`<br>
`3.[T][ ] read book`


### `find` - Find and print tasks that contains the keyword

Example of usage:

`find cs2113t`

Expected outcome:

`Here are the matching tasks in your list:`<br>
`1.[T][ ] cs2113t homework`<br>
`2.[D][ ] cs2113t iP (by: 5/3/2021 2359)`<br>


###### Duke is still not capable of doing many functions sadly...
###### pst... He is trying his best to learn! :)

### `random` - User inputs random words

Example of usage:

`I love software programming!`

Expected outcome:

`OOPS!!! I'm sorry, but I don't know what that means :-(`


### Hope you have fun using Duke! More functionalities to come...!

