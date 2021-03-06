# User Guide

## Commands 

- todo _TASKNAME_ : adds a todo task to the list.
- event _TASKNAME_ /at _TASKDATETIME_* : adds a event task to the list.
- deadline _TASKNAME_ /by _TASKDATETIME_* : adds a deadline task to the list.
- list : displays list of tasks.
- done _NUMBER_ : checks task as done.
- delete _NUMBER_ : removes task from list.
- find KEYWORD: search and display tasks that contains keywords.
- date DATE**: search and display tasks that contains date.
- save : save task list in a seperate text file.
- bye : exits system.

If the task list is saved, it will be re-loaded into the console and available for further edits upon running duke.main().
Do take note:
* TASKDATETIME: must be entered in the following format dd-MM-yyyy HH:mm.
* DATE: must be entered in the following format dd-MM-yyyy.

### Expected outcome:

- **todo eat dinner**
  ```
  Ayy I got you my brother. I've added this ting: 
  [T][ ] eat dinner
  Dayuum son! You have 1 mad tings in the list.
  ```
- **event party time /at 01-03-2021 22:00
  ```
  Ayy I got you my brother. I've added this ting: 
  [E][ ] party time (at: 1 Mar 2021 10:00 PM)
  I feer! You have 2 mad tings in the list.
  ```
