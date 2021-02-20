# 8K's Task Planner

8K's Task Planner is a Command Line Interface (CLI) app for planning of tasks. 

* Types of tasks
* Quick start
* Features
  * View all commands : `help`
  * List all tasks : `list`
  * Add todo : `todo`
  * Add event : `event`
  * Add deadline : `deadline`
  * Delete task : `delete`
  * Mark as done : `done`
  * Mark as undone : `undo`
  * Search keyword : `find`
  * Exit : `bye`
* Storage
* Command summary
* Credits






## Quick start
Download from here: https://github.com/8kdesign/ip/releases

**Requirement:** 
1. Java 11 and above

**Usage:**
1. Transfer the program to a folder of your liking. (Note: Data from the app will be stored in the same folder)
2. Double-click to start.
3. Type in command and press enter to execute.
For more information about commands, please refer to the list below.






## Types of tasks
8K's Task Planner comes with 3 type of tasks are available:
1. ToDo - Basic task with checkbox.
1. Event - Task with checkbox and information field.
1. Deadline - Task with checkbox and due date field.






## Features

### View all commands : `help`
List out list of commands and how to use them.<br>
Format: `help`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | help |
| Output | 8K: bye - Exit programme.<br>    help - Show list of commands.<br>    list - Show list of saved values.<br>    todo <name> - Creates new todo task.<br>    event <name> /at <info> - Creates new event.<br>    deadline <name> /by <DD-MM-YYYY> - Creates new deadline.<br>    delete <indices> - Deletes selected tasks.<br>    done <indices> - Mark selected tasks as done.<br>    undo <indices> - Mark selected tasks as not done.|

### List all tasks : `list`
List out all the tasks currently in your task list.<br>
Format: `list`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | list |
| Output | 1.[T][ ] apple<br>2.[E][ ] apple (at: pear)<br>3.[D][ ] apple (by: 24 Jun 1999) |

### Add todo : `todo`
Adds a todo with specified name into task list.<br>
Format: `todo <name>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | todo CS2113T Homework |
| Output | 8K: Added "CS2113T Homework" to list. |
| Action | Add toDo "CS2113T Homework" to task list |

### Add event : `event`
Adds an event with specified name and information to your task list.<br>
Format: `event <name> /at <additional info>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | event OP2 Meeting /at COM2 |
| Output | 8K: Added "OP2 Meeting" to list. |
| Action | Add event "OP2 Meeting" to task list |

### Add deadline : `deadline`
Adds a deadline with specified name and due date to your task list.<br>
Format: `deadline <name> /by <DD-MM-YYYY>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | deadline Wk6 Quiz /by 19-2-2020 |
| Output | 8K: Added "Wk6 Quiz" to list. |
| Action | Add deadline "Wk6 Quiz" to task list |

### Delete task : `delete`
Removes task at specified indices from the task list.<br>
Seperate multiple indices with a space.<br>
Invalid indices will be skipped.<br>
Format: `delete <index 1> <index 2> ... <index n>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | delete 1 2 |
| Output | Deleted:<br>[T][ ] task3<br>[T][ ] task1 |
| Action | Delete first and second task in task list |

### Mark as done : `done`
Marks task at specified indices in the task list as done.<br>
Seperate multiple indices with a space.<br>
Invalid indices will be skipped.<br>
Format: `done <index 1> <index 2> ... <index n>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | done 1 2 |
| Output | Marked as done:<br>[D][✓] task1 (by: 12 Dec 2020)<br>[D][✓] task2 (by: 20 Feb 2021) |
| Action | Mark first and second task in task list as done |

### Mark as undone : `undo`
Marks task at specified indices in the task list as not done.<br>
Seperate multiple indices with a space.<br>
Invalid indices will be skipped.<br>
Format: `undo <index 1> <index 2> ... <index n>`<br>
**Example:**
| | Result |
| --------- | ------------------------------------------------------- |
| Input | undo 1 2 |
| Output | Marked as undone:<br>[D][ ] task1 (by: 12 Dec 2020)<br>[D][ ] task2 (by: 20 Feb 2021) |
| Action | Mark first and second task in task list as not done |

### Search keyword : `find`
List out all tasks that contains keyword in name. <br>
Index relative to task list is shown for easier deletion / mark as done.<br>
Format: `find <keyword>`<br>
| | Result |
| --------- | ------------------------------------------------------- |
| Input | Find quiz |
| Output | 3.[T][ ] CS2113T Quiz |





