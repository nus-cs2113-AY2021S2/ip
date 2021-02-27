#Duke
Duke is a personal assistant chatbot that helps keep track of tasks, deadlines and events via a _Command Line 
Interface_ (CLI).

##User Guide
* [Getting started with Duke](#getting-started-with-duke)
  
* [Features](#features)
   * [Viewing help: `help`](#viewing-help-help)
   * [Adding a to do: `todo`](#adding-a-to-do-todo)
   * [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
   * [Adding an event: `event`](#adding-an-event-event)
   * [Viewing all tasks: `list`](#viewing-all-tasks-list)
   * [Checking a task off as done: `done`](#checking-a-task-off-as-done-done)
   * [Undoing a checked task: `undo`](#undoing-a-checked-task-undo)
   * [Removing a task: `delete`](#removing-a-task-delete)
   * [Finding tasks by description: `find`](#finding-tasks-by-description-find)
   * [Exiting Duke: `bye`](#exiting-duke-bye)
   
* [Saving the data](#saving-the-data)

* [Transferring save data](#transferring-save-data)
   
---

### Getting started with Duke
1. Ensure you have `Java 11` installed.
   * If you do not have the correct version of Java installed, `Java 11` 
     can be installed from [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest `ip.jar` from [here](https://github.com/Emkay16/ip/releases).
3. Move `ip.jar` to any folder of choice.
4. Navigate to the folder using your terminal of choice and start Duke up via this command:
   ```
   java -jar ip.jar
   ```
5. You are now ready to interact with Duke. Type a command 
   (e.g. typing `help` will list all commands and how to use them) to execute it.
   
6. Refer to the [Features](#features) below for commands that Duke recognises. 

---

###Features
> Command format:
>  * Words in angle brackets are required arguments\
>  e.g. in `todo <DESCRIPTION>`, `<DESCRIPTION>` is an argument which can be used as `todo Install Java 11`
>  * Commands can be typed in upper or lower case, or even a mix of both.\
>  Be aware that string arguments are saved as is.

####Viewing help: `help`
Prints all currently implemented commands, along with how to use them.\
Usage: `help`

####Adding a to do: `todo`
Adds a to do to the current lists of tasks.\
Usage: `todo <DESCRIPTION>`\
Example: `todo Complete CS2113T java exercises`

####Adding a deadline: `deadline`
Adds a deadline to the current lists of tasks.\
Usage: `deadline <DESCRIPTION> /by <DATE>`\
Example: `deadline Submit final version of Duke /by 2021-3-5`, `deadline submit quiz /by today 6pm`

####Adding an event: `event`
Adds an event to the current lists of tasks.\
Usage: `event <DESCRIPTION> /at <DATE>`\
Example: `event CS2113T Finals /at 2021-4-29`, `dinner with beanpod /at later tonight`

####Viewing all tasks: `list`
Prints the list containing all tasks, along with the type and status of the task.\
Usage: `list`

####Checking a task off as done: `done`
Checks off the indicated task as done.\
Usage: `done <TASK_NUMBER>`\
Example: `done 3`

####Undoing a checked task: `undo`
Unchecks the indicated task that was previously checked off as done.\
Usage: `undo <TASK_NUMBER>`\
Example: `undo 3`

####Removing a task: `delete`
Removes the indicated task.\
Usage: `delete <TASK_NUMBER>`\
Example: `delete 5`
> ! Note that deleting a task will cause the task numbers of tasks after it to change

####Finding tasks by description: `find`
Finds and lists tasks that contain the keyword(s) in the description.\
Usage: `find <KEYWORD(S)>`\
Example: `find CS2113T`, `find CS2113T ip`

####Exiting Duke: `bye`
Exits Duke\
Usage: `bye`

---

###Saving the data
Task data is saved automatically in `data.txt` after every command that modifies the task list.\
There is no need for manual saving.

---

###Transferring save data
When transferring data to another device, just place `data.txt` in the same directory as `ip.jar`.
> ! Please do not modify `data.txt` directly

---

###Command summary
Listed below are all currently implemented commands in alphabetical order.\
Click on the commands to navigate to specific feature details.

| Command                                    | Usage                               |
|--------------------------------------------|-------------------------------------|
| [bye](#exiting-duke-bye)                   | `bye`                               |
| [deadline](#adding-a-deadline-deadline)    | `deadline <DESCRIPTION> /by <DATE>` |
| [delete](#removing-a-task-delete)          | `delete <TASK_NUMBER>`              |
| [done](#checking-a-task-off-as-done-done)  | `done <TASK_NUMBER>`                |
| [event](#adding-an-event-event)            | `event <DESCRIPTION> /at <DATE>`    |
| [find](#finding-tasks-by-description-find) | `find <KEYWORD(S)>`                 |
| [help](#viewing-help-help)                 | `help`                              |
| [list](#viewing-all-tasks-list)            | `list`                              |
| [todo](#adding-a-to-do-todo)               | `todo <DESCRIPTION>`                |
| [undo](#undoing-a-checked-task-undo)       | `undo <TASK_NUMBER>`                |
