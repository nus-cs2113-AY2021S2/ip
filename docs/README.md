# User Guide
## Intro
TaskManager (Duke) is a desktop app for managing tasks, along with dates if applicable, according to the type of the tasks. <p>
It is optimized for use via a Command Line Interface (CLI).

## Features 

### Adding a 'todo' task: `todo` 
Adds a task of type 'todo' to the list of tasks.
### Adding a 'deadline' task: `deadline`
Adds a task of type 'deadline' to the list of tasks.
### Adding an 'event' task: `event`
Adds a task of type 'event' to the list of tasks.
### Deleting a task : `delete`
Deletes the task at the specified `INDEX` in the list.
### Listing all tasks : `list`
Shows the list of all tasks, 
which includes their type, status, name and date (if applicable).
### Marking tasks as 'done': `done`
Marks a task in the list at the specified `INDEX` as 'done'.
### Searching for tasks using keywords: `find`
Shows all the tasks in the list containing the keyword. <br>
If the name or the date of a task contains the keyword, the task will be shown.
### Exiting the program : `bye`
Exits the program.
### Saving the data
The data in the TaskManager are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.


## Usage

### 1. `todo TASKNAME` - Add a 'todo' task.

Description: Add a 'todo' type task with the TASKNAME to the list of tasks. <p>

Outcome: In updated list of tasks, there is a new task with the TASKNAME.
    Task will be displayed in the format: `[T][Status] TASKNAME`
- Type can be 'T', 'E', or 'D'. They represent Todo, Event and Deadline tasks respectively.
- Status: 'X' if task has been marked as 'done'. Otherwise, Empty.

Example of usage: <p>
`todo read book`

Expected outcome:

````
____________________________________________________________
Got it. I've added this task:
       [T][ ] read book
 Now you have 1 task in the list.
____________________________________________________________
````
### 2. `deadline TASKNAME /by DATE` - Add a 'deadline' task.

Description: Add a 'deadline' type task with its TASKNAME and its deadline which is DATE to the list of tasks. <p>
- DATE can be in any format. ie. DATE is a simply a word or phrase. 
- There are no limits on the length of the DATE.

Outcome: In updated list of tasks, there is a new task with the TASKNAME and the deadline DATE. <p>
Task will be displayed in the format: `[E][Status] TASKNAME (by: DATE)`
- Type can be 'T', 'E', or 'D'. They represent Todo, Event and Deadline tasks respectively.
- Status: 'X' if task has been marked as 'done'. Otherwise, Empty.

Example of usage: <p>
`deadline return book /by June 6th`

Expected outcome:

````
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.
____________________________________________________________
````

### 3. `event TASKNAME /at DATE` - Add a 'event' task.

Description: Add a 'event' type task with its TASKNAME and the DATE the event is held on to the list of tasks. 
- DATE can be in any format. ie. DATE is a simply a word or phrase.
- There are no limits on the length of the DATE.

Outcome: In updated list of tasks, there is a new task with the TASKNAME and the DATE of the event. <p>
Task will be displayed in the format: `[E]][Status] TASKNAME (by: DATE)`
- Type can be 'T', 'E', or 'D'. They represent Todo, Event and Deadline tasks respectively.
- Status: 'X' if task has been marked as 'done'. Otherwise, Empty.

Example of usage: <p>
`event project meeting /at Aug 6th 2-4pm`

Expected outcome:

````
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (at: Aug 6th 2-4pm)
Now you have 3 tasks in the list.
____________________________________________________________
````

### 4. `delete INDEX` - Delete a task.

Description: Delete a task at INDEX position in the list.
- The INDEX refers to the index number shown in the displayed tasks list.
- The INDEX must be a positive integer 1, 2, 3, …
- The INDEX must be within the total number of tasks.

Outcome: In updated list of tasks, the task is removed if it exists. <p> 
- The index of all the other tasks in the list are updated automatically.

Example of usage: <p>
`delete 2`

Expected outcome:

````
____________________________________________________________
Noted. I've removed this task: 
 [D][ ] return book (by: June 6th)
____________________________________________________________
````

### 5. `list` - List all tasks.

Description: List all tasks in the updated list.

Outcome: Shows the updated list of tasks.

Example of usage: <p>
`list`

Expected outcome:

````
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[E][ ] project meeting (at: Aug 6th 2-4pm)
____________________________________________________________
````

### 6. `done INDEX` - Mark task as done.

Description: Mark a task at INDEX position in the list as 'done'.
- The INDEX refers to the index number shown in the displayed tasks list.
- The INDEX must be a positive integer 1, 2, 3, …
- The INDEX must be within the total number of tasks.

Outcome: In updated list of tasks, the status of the task is marked as done with a 'X'.

Example of usage: <p>
`done 1`

Expected outcome:

````
____________________________________________________________
Nice! I've marked this task as done:
 [T][X] read book
____________________________________________________________
````

### 7. `find KEYWORD` - Find tasks by a keyword.

Description: Filter out all tasks with a certain keyword.
- The KEYWORD can be a word, phrase or just one character. ie. no definite format for KEYWORD

Outcome: Shows all tasks where their description (name or date) contains the keyword.

Example of usage: <p>
`find book`

Expected outcome:

````
Here are the matching tasks in the list:
1.[T][ ] read book
____________________________________________________________
````

### 8. `bye` - Exit the program.

Description: Says 'bye' to program to end the current session.

Outcome: Shows greeting to indicate that program has exited.

Example of usage: <p>
`bye`

Expected outcome:

````
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
````

