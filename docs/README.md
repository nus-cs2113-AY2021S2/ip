# Duke User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things. The name Duke was chosen in honor of Duke, the Java Mascot.
<br /><br />


## Features 
### Tracks Tasks 
Duke helps to keep track of tasks such as todos, deadlines, and events. 
### Maintains Due Dates
Due dates can be added for deadlines and event tasks.
### Easy Viewing/Searching of Tasks
Tasks can be easily listed or filtered based on search string.
### View Urgent Tasks
List all urgent tasks that are within the next week.
### No Lost Data
Saves data upon exit and loads data upon start to keep track of progress.
<br /><br />


## Usage
### `todo taskName` - add a todo task
Add a todo task
```
>> todo team project
_______________________________________________________________________________
Alrighty! I have added this new Todo:
    [TD][ ] team project
You now have 1 tasks in the list.
_______________________________________________________________________________
```

### `deadline deadlineName /by dd/mm/yyyy hhmm` - add a deadline
Add a deadline task with a due date. Date and time must be specified with the given format.
```
>> deadline individual project /by 09/03/2021 2359
_______________________________________________________________________________
Alrighty! I have added this new Deadline:
    [DL][ ] individual project (by: 09/03/2021 2359)
You now have 2 tasks in the list.
_______________________________________________________________________________
```

### `event eventName /at dd/mm/yyyy hhmm` - add an event
Add an event occuring on a date. Date and time must be specified with the given format.
```
>> event lecture /at 28/10/2021 1200
_______________________________________________________________________________
Alrighty! I have added this new Event:
    [EV][ ] lecture (at: 28/10/2021 1200)
You now have 3 tasks in the list.
_______________________________________________________________________________
```

### `list` - displays all tasks
Displays all existing tasks with its type, complete status, and due date (if applicable)
```
>> list
_______________________________________________________________________________
Here are the tasks in your list:
    1. [TD][ ] team project
    2. [DL][ ] individual project (by: 09/03/2021 2359)
    3. [EV][ ] lecture (at: 28/10/2021 1200)
_______________________________________________________________________________
```

### `done taskNumber` - mark task as complete
Marks task with specified task number as complete.
```
>> list
_______________________________________________________________________________
Here are the tasks in your list:
    1. [TD][ ] team project
    2. [DL][ ] individual project (by: 09/03/2021 2359)
    3. [EV][ ] lecture (at: 28/10/2021 1200)
_______________________________________________________________________________
>> done 2
_______________________________________________________________________________
Very nice! I've marked this task as done:
    [DL][X] individual project (by: 09/03/2022 2359)
_______________________________________________________________________________
```

### `urgent` - displays all urgent task
Displays all urgent tasks that are due within one week. Todo tasks are urgent by default. Completed tasks are not urgent.
```
>> urgent // when executed on 04/03/2021
_______________________________________________________________________________
Here are the urgent tasks due in the next week:
    1. [TD][ ] team project
_______________________________________________________________________________
```

### `find searchString` - finds tasks containing string
Displays list of tasks containing a given string
```
>> find project
_______________________________________________________________________________
Here are the matching tasks in your list:
    1. [TD][ ] team project
    2. [DL][X] individual project (by: 09/03/2022 2359)
_______________________________________________________________________________
```

### `delete taskNumber` - delete task
Deletes task with specified task number.
```
>> list
_______________________________________________________________________________
Here are the tasks in your list:
    1. [TD][ ] team project
    2. [DL][X] individual project (by: 09/03/2021 2359)
    3. [EV][ ] lecture (at: 28/10/2021 1200)
_______________________________________________________________________________
>> delete 1
_______________________________________________________________________________
Yay! I've successfuly deleted this task:
    [DL][X] individual project (by: 09/03/2022 2359)
_______________________________________________________________________________
>> list
_______________________________________________________________________________
Here are the tasks in your list:
    1. [TD][ ] team project
    2. [EV][ ] lecture (at: 28/10/2022 1200)
_______________________________________________________________________________
```

### `help` - Displays the list of commands and format
Displays all available commands and their corresponding formats.
```
>> help
_______________________________________________________________________________
Commands:
    todo taskName
    deadline deadlineName /by dd/mm/yyyy hhmm
    event eventName /at dd/mm/yyyy hhmm
    list
    urgent
    done taskNumber
    find searchString
    delete taskNumber
    help
    bye
_______________________________________________________________________________
```

### `bye` - Exits the program
Ends the program and exits
```
>> bye
_______________________________________________________________________________
Sad to see you go! ): See you soon!
_______________________________________________________________________________

```