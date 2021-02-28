# User Guide
Duke is a simple desktop app for managing tasks.
Instead of a Graphical User Interface (GUI), Duke is purely Command-Line Interface (CLI).

## Quick Start
1. Duke requires Java 11 or above to run. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` file from [here](https://github.com/marklowsk/ip/releases/tag/v0.2).
3. Copy the `ip.jar` file to a folder where you want to run Duke.
4. Open your terminal emulator or command prompt and change your working directory to that folder.
5. You can run Duke by entering `java -jar ip.jar`.
6. Once running, you can start entering commands to Duke.
   Enter `help` to display the list of commands.
   Some basic commands you can enter:
    * `list`: lists all tasks stored in Duke.
    * `todo HW assignment`: records a new task "HW assignment".
    * `done 1`: marks the first task in the list as done.
    * `delete 1`: removes the first task in the list.
    * `bye`: exits the application.
7. Refer to the Features below for details of each command.

## Features
Duke supports 3 types of tasks:
1. Todo `T` - a basic task.
2. Deadline `D` - a task with a "by" description (DateTime supported).
3. Event `E` - a task with a "at" description (DateTime supported).

### `help` - View help
Displays the list of commands and their description.

Format: `help`

### `list` - List all tasks
Lists all recorded tasks in Duke.

Format: `list`

Sample output:
```
list
	______________________________________________________________________
	 1.[D][X] math hw (by: Feb 27, 2021 23:59)
	 2.[D][ ] calculus qns (by: Mar 01, 2021 23:59)
	 3.[E][ ] goodwood show (at: Feb 28, 2021 09:30)
	 4.[T][ ] complete GE mod work
	______________________________________________________________________
```

### `todo` - Add a todo task
Adds a todo task to record.

Format: `todo <description>`

Sample command: `todo complete GE mod work`

### `deadline` - Add a deadline task
Adds a deadline task to record.

Format: `deadline <description> /by <datetime>`

Note: `<datetime>` not required to be a DateTime. A meaningful string is good enough.

Sample command 1: `deadline calculus qns /by 1/3/2021 23:59`

Sample command 2: `deadline calculus qns /by next Sunday`

Sample output:
```
deadline Homework questions /by 1/3/2021
	______________________________________________________________________
	 Got it. I've added this task:
	   [D][ ] Homework questions (by: Mar 01, 2021 00:00)
	 Now you have 5 tasks in the list.
	______________________________________________________________________
```

Note: If time is not specified with the date,
Duke will assume the time to be the start of the day. This is similar for the `event` command.

### `event` - Add an event task
Adds an event task to record.

Format: `event <description> /at <datetime>`

Note: `<datetime>` not required to be a DateTime. A meaningful string is good enough.

Sample command 1: `event goodwood show /at 28.02.2021 0930`

Sample command 2: `event goodwood show /at tomorrow (Sunday)`

### `done` - Mark a task as done
Marks a task as done based on its task number in the list.

Format: `done <task number>`

Sample output:
```
done 5
	______________________________________________________________________
	 Nice! I've marked this task as done:
	   [D][X] Homework questions (by: Mar 01, 2021 00:00)
	______________________________________________________________________
```

### `delete` - Delete a task
Deletes a task record based on its task number in the list.

Format: `delete <task number>`

Sample output:
```
delete 5
	______________________________________________________________________
	 Noted. I've removed this task:
	   [D][X] Homework questions (by: Mar 01, 2021 00:00)
	 Now you have 4 tasks in the list.
	______________________________________________________________________
```

### `find` - Find tasks with a matching keyword
Find tasks with a specified keyword.

Format: `find <keyword>`

```
list
	______________________________________________________________________
	 Here are the tasks in your list:
	 1.[T][X] borrow book
	 2.[D][ ] return book (by: Sunday)
	 3.[E][ ] project meeting (at: Mon 2-4pm)
	 4.[E][ ] Arts festival (at: May 03, 2021 18:00)
	______________________________________________________________________
find book
	______________________________________________________________________
	 Here are the task(s) in the list that match the keyword:
	 1.[T][X] borrow book
	 2.[D][ ] return book (by: Sunday)
	______________________________________________________________________
```

### `date` - Find deadline and event tasks by date
Search for deadline or event tasks that have a same specified date.

Format: `date <date>`

Note: Date format if `<date>` must be specified properly.

* Supported date formats are:
    * `DDMMYYYY`
    * `D.M.YYYY`
    * `D-M-YYYY`
    * `D/M/YYYY`
    * `YYYY.M.D`
    * `YYYY-M-D`
    * `YYYY/M/D`

```
list
	______________________________________________________________________
	 Here are the tasks in your list:
	 1.[E][ ] 5th project meeting (at: Mar 01, 2021 16:00)
	 2.[E][X] HW briefing (at: Feb 28, 2021 00:00)
	 3.[E][ ] Test briefing (at: Mar 01, 2021 18:00)
	 4.[D][ ] iP submission (by: Mar 05, 2021 23:59)
	 5.[D][ ] HW submission (by: Mar 05, 2021 23:59)
	______________________________________________________________________
date 5/3/2021
	______________________________________________________________________
	 Found 2 Deadline(s) and 0 Event(s) on the Mar 05, 2021.
	 Deadline(s):
	 1.[D][ ] iP submission (by: Mar 05, 2021 23:59)
	 2.[D][ ] HW submission (by: Mar 05, 2021 23:59)
	______________________________________________________________________
```

### `bye` - Exit the program
Exits the program.

Format: `bye`

## File storage
Duke stores the task list data to file.
Each time a task is added or modified, Duke writes to file.
The data file is located at `./data/duke.txt` i.e. relative to your current working directory.
If directory/file does not exist, duke attempts to create it.
An error will be printed if file operations fail.
On Duke's start-up, Duke will attempt to retrieve task data from the file.

## Command summary

| Command | Format |
| --------- | ---------------------------------------------- |
| help | `help` |
| list | `list` |
| todo | `todo <description>` |
| deadline | `deadline <description> /by <datetime>` |
| event | `event <description> /at <datetime>` |
| done | `done <task number>` |
| delete | `delete <task number>` |
| find | `find <keyword>` |
| date | `date <date>` |
| bye | `bye` |

> User guide inspiration is based on [AB-3 UG](https://se-education.org/addressbook-level3/UserGuide.html).
