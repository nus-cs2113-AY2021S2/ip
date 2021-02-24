# _J.A.R.V.I.S._ User Guide

_J.A.R.V.I.S._ (_JARVIS_) is a **cross-platform desktop task manager app, optimized for use via a Command Line Interface (CLI)**.
If you can type fast, _JARVIS_ can manage your tasks faster than traditional Graphical User Interface (GUI) apps.

----
### Table of Contents
* [Quick start](link)
    - [For first time users](link)
    - [For existing users](link)
* [Features](link)
    - [Adding a todo task : `todo`](link)
    - [Adding a deadline task : `deadline`](link)
    - [Adding an event task : `event`](link)
    - [Deleting a task : `delete`](link)
    - [Marking a task as done : `done`](link)
    - [Viewing all the tasks : `list`](link)
    - [Searching tasks by a keyword : `find`](link)
    - [Exiting JARVIS : `bye`](link)
* [FAQ](link)
* [Command summary](link)

---
## Quick start
> **Prerequisites:** Ensure that you have **Java 11** or above installed in your Computer.
### For first time user
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
1. Type the command in the command window and press Enter to execute it. Refer to [Features](link) below for details of
each command.   
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
1. Type the command in the command window and press Enter to execute it. Refer to [Features](link) below for details of
each command.
   
--- 
## Features
> **Note:**
> * Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo TASK`, `TASK` is a parameter
> which can be used as `todo task 1`.
> * Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. e.g.
> if the command specifies `bye 123`, it will be interpreted as `bye`.
### Adding a todo task : `todo`
### Adding a deadline task : `deadline`
### Adding an event task : `event`
### Deleting a task : `delete`
### Marking a task as done : `done`
### Viewing all the tasks : `list`
### Searching tasks by a keyword : `find`
### Exiting JARVIS : `bye`

---
## FAQ

---
## Command summary

---


[comment]: <> (## Features )

[comment]: <> (### Feature 1 )

[comment]: <> (Description of feature.)

[comment]: <> (## Usage)

[comment]: <> (### `Keyword` - Describe action)

[comment]: <> (Describe action and its outcome.)

[comment]: <> (Example of usage: )

[comment]: <> (`keyword &#40;optional arguments&#41;`)

[comment]: <> (Expected outcome:)

[comment]: <> (`outcome`)
