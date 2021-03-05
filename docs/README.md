# Bob User Guide
Bob is a Personal Assistant Chat bot that helps a person to keep track of various things.
<br /><br />


## Features 
### Track Tasks 
Bob helps to track tasks of types deadline, event and todo.
### Keeps track of Due Dates
Bob maintains due dates for types deadline and event
### Search Tasks
Bob allows for searching of tasks by matching letters.
### Auto saving
Saves the task every time it is modified. Reads from the save automatically when launched.
<br /><br />


## Usage
### `todo taskLabel` - add a todo task
Add a todo task
```
>> todo Take out the trash
____________________________________________________________
Say no more fam. The task is added:
  [T][❌] Take out the trash
1 task(s) in the list.
____________________________________________________________
```

### `deadline deadlineLabel /by time` - add a deadline
Add a deadline task with a due date.
```
>> deadline Buy a new printer /by 5/3/2021
____________________________________________________________
Say no more fam. The task is added:
  [D][❌] Buy a new printer (by: 2021-03-05)
2 task(s) in the list.
____________________________________________________________
```

### `event eventLabel /at time` - add an event
Add an event with a date. 
```
>> event Attend a TED talk /at 1700
____________________________________________________________
Say no more fam. The task is added:
  [E][❌] Attend a TED talk (at: 1700)
3 task(s) in the list.
____________________________________________________________
```

### `list` - displays all tasks
Displays all tasks with its type, completeness status, and date

T - Todo |
D - Deadline |
E - Event
```
>> list
____________________________________________________________
1.[T][❌] Take out the trash
2.[D][❌] Buy a new printer (by: 2021-03-05)
3.[E][❌] Attend a TED talk (at: 1700)
____________________________________________________________
```

### `done taskNumber` - mark task as done
Marks task with that taskNumber as done.
```
>> done 3
____________________________________________________________
Task.Task marked as done, gg ez
  [E][✔] Attend a TED talk (at: 1700)
____________________________________________________________
```


### `find query` - finds tasks containing query
Displays a list of tasks containing a given query
```
>> find printer
____________________________________________________________
Here are the tasks in your list that match「printer」:
1.[D][❌] Buy a new printer (by: 2021-03-05)
____________________________________________________________
```

### `delete taskNumber` - delete task
Deletes a task with specified task number.
```
>> delete 3
____________________________________________________________
You are a quitter 👎 Anyways, I removed this:
  [E][✔] Attend a TED talk (at: 1700)
2 task(s) left in the list.
____________________________________________________________
```

### `help` - Displays the list of commands
Displays all available commands and their format.
```
>> help
____________________________________________________________
 Available commands:
   todo taskLabel
   deadline deadlineLabel /by time
   event eventLabel /at time
   list
   done taskNumber
   find query
   delete taskNumber
   help
   bye
____________________________________________________________
```

### `bye` - Exits the program
```
>> bye
____________________________________________________________
 Chao 👌
____________________________________________________________
```