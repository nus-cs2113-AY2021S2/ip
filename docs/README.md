# User Guide

Duke is an application that uses Command Line Interface (CLI) for managing tasks.
## Features 

This section will describe the *8* available features and its usages
## Feature 1 
### Keyword - `todo`

Adds a todo task into your task list

Example of usage: 

`todo prepare for cs2113t exam`

Expected outcome:
```
Gotcha! I've added this task:
[T][ ] prepare for cs2113t exam

1 task left in the list
```

---
## Feature 2

### Keyword - `event`

Adds an event task into your task list

Format: `event <description> /at <venue>`

Example of usage:

`event cs2113t exam /at nus`

Expected outcome:
```
Gotcha! I've added this task:
[E][ ] cs2113t exam (at: nus)
1 task left in the list
```
---
## Feature 3

### Keyword - `deadline`

Adds a deadline task into your task list

Format: `deadline <description> /by <YYYY-MM-DD>`

Example of usage:

`deadline cs2113t homework /by 2021-01-01`

Expected outcome:
```
Gotcha! I've added this task:
[D][ ] cs2113t homework (by:Jan 1 2021)
1 task left in the list
```
---
## Feature 4

### Keyword - `list`

Prints all the task in the task list

Format: `list`

Example of usage:

`list`

Expected outcome:
```
1. [T][ ] read a book
2. [E][ ] cs2113t exam (at: nus)
3. [D][ ] math homework (by:Feb 2 2021)
```
---
## Feature 5

### Keyword - `delete`

Removes the selected task from the task list

Format: `deadline <index number>`

Example of usage:

`deadline 2`

Expected outcome:
```
The task has been deleted.
```
---
## Feature 6

### Keyword - `done`

Marks the task as done in the task list

Format: `done <index number>`

Example of usage:

`done 1`

Expected outcome:
```
This task has been done. Good job!
[X] read a book
```
---
## Feature 7

### Keyword - `find`

Searches for the input word amongst all entries in the task list

Format: `find <word>`

Example of usage:

`find book`

Expected outcome:
```
1.[T][ ] read a book tonight
2.[T][ ] read harry potter and the deathly hallows book 
3.[E][ ] attend a book fest tomorrow (at: nus)
```
---
## Feature 8

### Keyword - `bye`

Terminates the program

Format: `bye`

Example of usage:

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
---
## Summary

Keyword | Format
-------------| ------------
todo | `todo <description>`
event | `event <description> /at <venue>`
deadline | `deadline <description> /by <YYYY-MM-DD>`
list | `list`
delete | `delete <index number>`
done | `done <index number>`
find | `find <word>`
bye | `bye`
