# User Guide
This Duke program is for tracking tasks - *todo, event and deadline*. It is optimised for use via a Command Line Interface. 

## Quick Start
1. Download and install Java 11 or above in your device.
2. Get the latest version of the `ip.jar` from <a href="https://github.com/s-t-e-f/ip/releases/tag/v2.0">HERE</a>
3. To execute the JAR file from Windows Command Prompt
   * Input the following command : `java -jar file_Path` , where **file_path** is the location of the jar file.
4. Start using the app by entering commands to the user interface. 
   Please refer to the Features section below for detailed description of each command.


## Features 
### Notes about the command format
* Words in UPPER_CASE are parameters specified by the user.
  <br> E.g. : In `todo DESCRIPTION`, `DESCRIPTION` is a parameter 
  which can be used as `todo buy bread`.
  
* Extraneous parameters for commands that do not take in parameters
  (such as `list` and `bye`) will be ignored.
  <br> E.g.: `bye 123` will be interpreted as `bye`.
  
### List all tasks : `list` 
Shows a list of all the tasks in the tasks list.
The task type, task description, and date 'at' or 'by' 
if the task is an event or deadline will be printed.
<br>Format : `list`

### Add a ToDo task : `todo`
Adds a ToDo task to the tasks list.
<br> Format : `todo DESCRIPTION`
<br> Examples :
<br>* `todo buy bread` : adds a new Todo task with the description `buy bread`.


### Add an Event : `event`
Adds an event to the tasks list.
<br> Format : `event DESCRIPTION /at DATE`
<br>* `DATE` has to be in the format of **YYYY-MM-DD**.
<br> Examples :
<br>* `event project meeting /at 2021-01-01` : add a new task with the description `project meeting` 
on `2020-01-01`.

### Add a Deadline : `deadline`
Adds a deadline to the tasks list.
<br> Format : `deadline DESCRIPTION /by DATE`
* `DATE` has to be in the format of YYYY-MM-DD.
<br> Examples :
<br>* `deadline project submission /at 2021-03-05` : add a new task with the description `project submission`,
  with the deadline `2020-03-05`.

### Mark a task as done : `done`
Marks the specified task as done 
<br> Format : `done INDEX`
<br> Examples :
<br>* `done 3` : marks the `third` item in the task list as done.

### Delete a task : `delete`
Deletes the specified task from the tasks list.
<br> Format : `delete INDEX`
<br> Examples :
<br>* `delete 3` : deletes the `third` item in the task list.

### Find a task : `find`
Find tasks from the tasks list which description 
contains the given substring.
<br> Format : `find SEARCH_DESCRIPTION`
<br> Examples :
<br>* `find project` : searches the task list for all the task descriptions containing the word 
`project` and print those task(s) out.

### Exit the program : `bye`
Exits the program and saves the tasks list into a text file.
<br> Format :  `bye`

## Storage  
The task list is loaded and saved from the `user_dir/duke.txt` file automatically when 
starting and exiting the program.
