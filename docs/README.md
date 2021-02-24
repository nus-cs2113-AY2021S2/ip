# _J.A.R.V.I.S._ User Guide

_J.A.R.V.I.S._ (_JARVIS_) is a **cross-platform desktop task manager app, optimized for use via a Command Line Interface (CLI)**.
If you can type fast, _JARVIS_ can manage your tasks faster than traditional Graphical User Interface (GUI) apps.

----
### Table of Contents
* [Quick start](README.md#quick-start)
    - [For new users](README.md#for-new-user)
    - [For existing users](README.md#for-existing-user)
* [Features](README.md#features)
    - [Adding a todo task : `todo`](README.md#adding-a-todo-task--todo)
    - [Adding a deadline task : `deadline`](README.md#adding-a-deadline-task--deadline)
    - [Adding an event task : `event`](README.md#adding-an-event-task--event)
    - [Deleting a task : `delete`](README.md#deleting-a-task--delete)
    - [Marking a task as done : `done`](README.md#marking-a-task-as-done--done)
    - [Viewing all the tasks : `list`](README.md#viewing-all-the-tasks--list)
    - [Searching tasks by a keyword : `find`](README.md#searching-tasks-by-a-keyword--find)
    - [Exiting _JARVIS_ : `bye`](README.md#exiting-jarvis--bye)
* [FAQ](README.md#faq)
* [Command summary](README.md#command-summary)

---
## Quick start
> **Prerequisites:** Ensure that you have **Java 11** or above installed in your Computer.


### For new user
Set up _JARVIS_ as follows:
1. Create an empty folder you want to use as the _home folder_ for _JARVIS_.
1. Download the latest `JARVIS.jar` from [here](https://github.com/iamakilahamed/ip/releases).
1. Copy the jar file to the folder you created earlier.
1. Open a command window to that folder.
1. Run the command `java -jar JARVIS.jar` (i.e., run the command in the same folder as the jar file).
1. If the setup is correct, you should see something like the output below after approximately 5 seconds.

   ```
   --------------------------------------------------------------------------------------------
   Importing all preferences from home interface.
   Synchronising from the cloud.
   Systems are now fully operational.
   Initialising...
   --------------------------------------------------------------------------------------------
       Hello, sir. J.A.R.V.I.S. at your service.
   --------------------------------------------------------------------------------------------
       Unfortunately, I could not detect any files in the database!
       But don't worry sir.
       I will create the files you might be needing later.
   --------------------------------------------------------------------------------------------
   ```
1. Type the command in the command window and press Enter to execute it. Refer to 
   [Features](README.md#features) below for details of each command.   

### For existing user
Open _JARVIS_ as follows:
1. Open a command window to the _home folder_ where you have copied `JARVIS.jar`.
1. Run the command `java -jar JARVIS.jar` (i.e, run the command in the same folder as the jar file).
1. If you already have a `jarvis.txt` in the folder containing the jar file, you should see something like the output 
  below after approximately 5 seconds.
   
   ```
   --------------------------------------------------------------------------------------------
   Importing all preferences from home interface.
   Synchronising from the cloud.
   Systems are now fully operational.
   Initialising...
   --------------------------------------------------------------------------------------------
       Hello, sir. J.A.R.V.I.S. at your service.
   --------------------------------------------------------------------------------------------
       Here's the tasks in your list, sir: 
           [T][ ] task 1
           [D][ ] task 2 (by: date and time)
           [E][ ] task 3 (at: location, date and time)
   --------------------------------------------------------------------------------------------
   ```
   However, if you do not have a `jarvis.txt` in the folder containing the jar file, you should see something like the
   output below after approximately 5 seconds.
   
   ```
   --------------------------------------------------------------------------------------------
   Importing all preferences from home interface.
   Synchronising from the cloud.
   Systems are now fully operational.
   Initialising...
   --------------------------------------------------------------------------------------------
       Hello, sir. J.A.R.V.I.S. at your service.
   --------------------------------------------------------------------------------------------
       You do not have any pending task, sir.
   --------------------------------------------------------------------------------------------
   ```
1. Type the command in the command window and press Enter to execute it. Refer to 
   [Features](README.md#features) below for details of each command.
   
--- 
## Features
> **Note:**
> * Command should always be in `lower_case`. It is always the first word supplied by the user.
> 
>   e.g. `list`, `todo TASK` and `delete 3`.
> 
> 
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
>   e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo task 1`.
> 
> 
> * Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. 
> 
>    e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.
> 
> 
> * Whenever the task list is updated, `jarvis.txt` will be automatically updated if it already exists in the same
>   directory as the `JARVIS.jar` file. If `jarvis.txt` is missing, it will be automatically created within the same
>   directory as the jar file.

### Adding a todo task : `todo`
Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo read book`

Expected outcome:
```
    Got it. I've added this task:
        [T][ ] read book
    Now you have 1 tasks in the list.
--------------------------------------------------------------------------------------------
```

### Adding a deadline task : `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DETAILS`
* `DETAILS` can be in any format and order.

Example: `deadline complete assignment /by 6 March 2021, 2359`

Expected outcome:
```
    Got it. I've added this task:
        [D][ ] complete assignment (by: 6 March 2021, 2359)
    Now you have 2 tasks in the list.
--------------------------------------------------------------------------------------------
```

### Adding an event task : `event`
Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /at DETAILS`
* Similar to `deadline`, `DETAILS` can be in any format and order.

Example: `event project meeting /at NUS, 25 Feb 2021, 2-4pm`

Expected outcome:
```
    Got it. I've added this task:
        [E][ ] project meeting (at: NUS, 25 Feb 2021, 2-4pm)
    Now you have 3 tasks in the list.
--------------------------------------------------------------------------------------------
```

### Deleting a task : `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed task list.

Example: `delete 2`
* Deletes the 2nd task in the task list.

Expected outcome:
```
    Noted, sir! I've removed this task:
        [D][ ] complete assignment (by: 6 March 2021, 2359)
    Now you have 2 tasks in the list.
--------------------------------------------------------------------------------------------
```

### Marking a task as done : `done`
Marks the task at the specified `INDEX` as done with `X`.

Format: `done INDEX`
* Marks the task at the specified `INDEX` with `X`.
* The `INDEX` refers to the index number shown in the displayed task list.

Example: `done 2`
* Marks the 2nd task in the task list as done with `X`.

Expected outcome:
```
    Well done, sir! I've marked this task as done:
        [E][X] project meeting (at: NUS, 25 Feb 2021, 2-4pm)
--------------------------------------------------------------------------------------------
```

### Viewing all the tasks : `list`
Shows a list of all the tasks in the task list.

Format: `list`

Example: `list`

Expected outcome:
```
    Here are the tasks in your list, sir:
        1. [T][ ] read book
        2. [E][X] project meeting (at: NUS, 25 Feb 2021, 2-4pm)
--------------------------------------------------------------------------------------------
```

### Searching tasks by a keyword : `find`
Finds tasks which descriptions contain the given keyword.

Format: `find KEYWORD`
* The search is case-sensitive. e.g. `BOOKS` will not match `book`
* Only the description is searched.
* Partial words will be matched. e.g. `meet` will match `meeting`
* All tasks matching with the given keyword will be returned.

Example: `find meet`

Expected outcome:
```
        1. [E][X] project meeting (at: NUS, 25 Feb 2021, 2-4pm)
--------------------------------------------------------------------------------------------
```

### Exiting JARVIS : `bye`
Exits the program.

Format: `bye`

Example: `bye`

Expected outcome:
```
    Goodbye, sir.
--------------------------------------------------------------------------------------------
```

---
## FAQ
**Q:** How do I transfer my data to another computer?

**A:** The `jarvis.txt` is a text file and can be easily transferred from one computer to another through cloud-based 
       storage platforms like Google Drive for example. To install JARVIS, follow steps 1 to 3 of 
       [For new users](README.md#for-new-user) or you can directly download the latest `JARVIS.jar` 
       [here](https://github.com/iamakilahamed/ip/releases) if you are an experienced JARVIS user.

---
## Command summary
Action | Format, Examples
--- | ---
Add Todo | `todo TASK_DESCRIPTION` e.g. `todo read book`
Add Deadline | `deadline TASK_DESCRIPTION /by DETAILS` e.g. `deadline complete assignment /by 6 March 2021, 2359` 
Add Event | `event TASK_DESCRIPTION /at DETAILS` e.g. `event project meeting /at NUS, 25 Feb 2021, 2-4pm`
Delete | `delete INDEX` e.g. `delete 2`
Mark as done | `done INDEX` e.g. `done 2`
List | `list`
Find | `find KEYWORD` e.g. `find meet`
Exit | `bye`

---