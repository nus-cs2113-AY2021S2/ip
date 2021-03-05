# User Guide
Happy is designed to be a personal assistant chatbot in command line interface (CLI).
By using Happy, you can make a list of tasks (todo, deadline and event) and manage your schedule 
more easily.

## Quick start
1. Download and install Java 11 or above in your device.
2. Get the latest version of Happy from [HERE](https://github.com/NgManSing/ip).
3. Run the .jar file in terminal (e.g. command prompt for Windows) to start the program. A command-line interface (CLI) 
   should appear quickly after execution.
4. Start using the app by entering commands to the user interface. Please refer to the Features section below for 
   detailed description of each command.

## Features

### Notes about the command format:
- Parameters in brackets () are mandatory while the ones in square brackets [] are optional.
- Valid command should be given in the following format: __command + parameter(s)__
- Date has to be provided in yyyy-mm-dd format (e.g. 2021-09-03)
- Time has to be provided in hhmm 24-hour format (e.g. 2359)


### `todo` - Adding a todo task
To add a todo task to the task list
#### Command: `todo (name of the task)`
#### Example: `todo task1`
#### Expected outcome:
```
Command entered: todo task1
Got it. I've added this task:
    [T][X] task1
Now you have 5 tasks in the list.
```

### `deadline` - Adding a deadline task
To add a deadline task to the task list
#### Command: `deadline (name of the deadline) /by (date) [time]`
#### Example: `deadline task1 /by 2020-09-03 1134`
#### Expected outcome:
```
Command entered: deadline task1 /by 2020-09-03 1134
Got it. I've added this task:
	[D][X] task1 (by: Sep 3 2020, 11:34am)
Now you have 1 tasks in the list.
```

### `event` - Adding an event task
To add an event task to the task list
#### Command: `event (name of the event) /at (date) [time]`
#### Example: `event task1 /at 2020-09-03 1134`
#### Expected outcome:
```
Command entered: event task1 /at 2020-09-03 1134
Got it. I've added this task:
	[E][X] task1 (at: Sep 3 2020, 11:34am)
Now you have 1 tasks in the list.
```

### `list` - Show the task list
To list all tasks stored in the task list
#### Command: `list`
#### Expected outcome:
```
Command entered: list
Here is your task List:
1. [T][X] task1
2. [D][X] task2 (by: Jan 1 2020, 11:59pm)
3. [E][X] task3 (at: Mar 1 2020)
```

### `done` - Mark a task as "Done"
To mark a task indicated by an index, which could be found with `list` command, as "Done".
#### Command: `done (index)`
#### Example: `done 1`
#### Expected outcome:
```
Command entered: done 1
Nice! I've marked this task as done:
	[T][✓] task1
```

### `delete` - Delete a task
To delete a task indicated by an index, which could be found with `list` command, stored in the task list
#### Command: `delete (index)`
#### Example: `delete 1`
#### Expected outcome:
```
Command entered: delete 1
Got it. I've deleted this task:
	[T][✓] task1
Now you have 0 tasks in the list.
```

### `find` - List target tasks based on a given keyword
To list all tasks in the task list whose task names contain the keyword.
#### Command: `find (keyword)`
#### Example: `find happy`
#### Expected outcome:
```
Command entered: find happy
Here is your task List with keyword happy:
1. [T][X] happyhappy
2. [E][X] happy night (at: Feb 2 2020, 6:00pm)
```

### `search` - List target tasks based on a given date
To list all tasks in the task list whose dates are the same as the given date.
#### Command: `search (date)`
#### Example: `find 2020-01-01`
#### Expected outcome:
```
Command entered: search 2020-01-01
Here is your task in 2020-01-01:
1. [D][X] task1 (by: Jan 1 2020)
```

### `bye` - Exit the program
To exit the program
#### Command: `bye`
#### Expected outcome:
```
Command entered: bye
Bye. Hope to see you again soon!
```
## FAQ
Question: Will Happy update the changes made in CLI to the local text file?

Answer: Yes, all changes (add / delete / set as Done) will be updated synchronously.

##Command Summary
Command|Format
---------|---------
todo|todo (name of the task)
deadline|deadline (name of the deadline) /by (date) [time]
event|event (name of the task) /at (date) [time]
list|list
done|done (index)
delete|delete (index)
find|find (keyword)
search|search (date)
bye|bye
