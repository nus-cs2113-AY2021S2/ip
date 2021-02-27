# User Guide

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ip.jar` from [here](https://github.com/brandonfoong/ip/releases/), under `Assets > ip.jar`.

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Open an instance of your Terminal/Command Prompt/Powershell. In Windows 10, this can be done by right clicking an empty space in the File Manager, then selecting "Open Powershell window here".

5. Copy the following command into your terminal and press Enter to start Duke: `java -jar ip.jar`
    - Duke will automatically try to import a previous session's task information from the file `./data/duke.csv`.
    - If Duke is unable to open the file for reading, it will immediately terminate. To prevent this from happening, please ensure that you do not have the `./data/duke.csv` open in other programs (e.g. Excel).

6. You will see a welcome message, and Duke will prompt you to enter a command. A list of commands can be found by typing **`help`** and then pressing Enter. Some sample commands you can try:
    - `list`: Lists all tasks that you've added
    - `todo try out this app`: Adds a new task to the list with the description "try out this app"
    - `delete 1`: Deletes the 1st task in the list
    - `clear`: Clears all previously added tasks
    - `bye`: Exits the app

7. Refer to the Features below for details of each command.

## Features 

### Feature 1 
Description of feature.

## Usage

### `help` - Show a help message

Shows a message listing all valid commands and their formats.

Format: 

`help`

Sample outcome:

```	
    ____________________________________________________________
	 List of valid commands:
	 bye - Exits program
	 help - Prints this help message
	 list - Lists all tasks
	 done <TASK_NUMBER> - Mark the specified task as done
	 todo <TASK_DESCRIPTION> - Create a new task with the specified description
	 deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE> - Create a new task with the specified description and deadline
		Format for DEADLINE_DATE: YYYY-MM-DD
	 event <TASK_DESCRIPTION> /at <EVENT_DATE> - Create a new task with the specified description and event date
		Format for EVENT_DATE: YYYY-MM-DD
	 delete <TASK_NUMBER> - delete the specified task
	 clear - deletes all tasks
	 find <SEARCH_EXPRESSION> - finds all tasks containing the required expression
	____________________________________________________________
```

### `list` - List all tasks

Shows an enumerated list of tasks

Format: 

`list`

Sample outcome:

```	
	____________________________________________________________
	 Here is a list of all your tasks:
	 1. [T][ ] eat
	 2. [T][ ] sleep
	 3. [T][ ] repeat
	____________________________________________________________
```

### `done` - Mark a task as done

Marks the specified task as done. Tasks are selected based on their numbering after running the `list` command.

Format: 

`done TASK_NUMBER`

1. `TASK_NUMBER` must be a positive integer 1, 2, 3,... and it has to be less than or equal to the total number of tasks.

2. If `TASK_NUMBER` does not follow the specification above, Duke will show an error message and not execute the command.

Examples:

If we run `done 2` in the sample instance in the `list` command above, it will mark the 2nd task as done and produce the following output:

```	
	____________________________________________________________
	 Nice! I've marked this task as done:
	 [T][X] sleep
	____________________________________________________________
```

### `todo` - Add a new task

Adds a new task to the list with the specfied description.

Format: 

`todo <TASK_DESCRIPTION>`

Example:
- `todo try out this app`

### `event` - Add a new task with a event date

Adds a new task to the list with the specfied description and event date.

Format: 

`event TASK_DESCRIPTION /at EVENT_DATE`

1. `EVENT_DATE` must follow the format YYYY-MM-DD. If it doesn't, Duke will show an error message and not execute the command.

Example:
- `event CS2113T tutorial /at 2021-03-03`

### `delete` - Delete a selected task

Deletes the specified task. Tasks are selected based on their numbering after running the `list` command.

Format: 

`delete TASK_NUMBER`

1. `TASK_NUMBER` must be a positive integer 1, 2, 3,... and it has to be less than or equal to the total number of tasks.

2. If `TASK_NUMBER` does not follow the specification above, Duke will show an error message and not execute the command.

Examples:

If we run `delete 3` in the sample instance in the `list` command above, it will delete the 3rd task and produce the following output:

```
    ____________________________________________________________
	 Noted. I've removed this task:
	 [T][ ] repeat
	 You have 2 tasks in the list.
	____________________________________________________________
```
### `clear` - Deletes all tasks

Deletes all tasks from the list.

Format: 

`clear`

### `find` - List all matching tasks

Searches for all tasks matching the search expression, and shows them in an enumerated list. If no tasks match the search criteria, Duke will inform the user.

Format: 

`find SEARCH_EXPRESSION`

Example:

If we run `find ea` in the sample instance in the `list` command above, it will produce the following output:

```	
	____________________________________________________________
	 Here are the matching tasks in your list:
	 1. [T][ ] eat
	 2. [T][ ] repeat
	____________________________________________________________
```

### `bye` - Exits the program

Exits the program, saving all tasks in the list to `./data/duke.csv`. If Duke encounters any issues while saving the file, it will give you the choice to try re-exporting the file, or to exit without saving.

Format:

`bye`