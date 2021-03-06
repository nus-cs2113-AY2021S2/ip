# Duke User Guide

Duke is an application project that is designed to help users to keep track different types of tasks. For example: todos, deadlines and events.

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
1. Download Duke file
1. Go to `/ip/src/main/java`
1. In command line key in:
   ```
   javac ip/duke/Duke.java
   java ip/duke/Duke   
   ```

## Setting Up in IntelliJ version 2020.3.1 (latest) and beyond

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Set up the correct JDK version, as follows:
   1.Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1.If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1.Click 'OK'
1. Import the project into IntelliJ as follows:
   1. Click `Open or Import`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/ip.duke.Duke.java` file, right-click it, and choose `Run ip.duke.Duke.main()`. If the setup is correct, you should see something like the output below.

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Using Duke Project

### Help

* Display all acceptable commands
* Format: `help`
* Example of expected output:

```
___________________________________________
Here are some available commands and their corresponding input format: 

list: Display all tasks in the list.
Format: list

todo: Add a todo task.
Format: todo read a book

deadline: Add a deadline task.
Format: deadline submit a report /by 05 Mar

event: Add an event task.
Format: event project meeting /at Mon 2-4pm

done: Mark a task as done.
Format: done 1

find: find all tasks contain the input keyword
Format: find book

date: find all tasks occurring the specific date
Format: date 2021-02-28

bye: Exit the program.
Format: bye

___________________________________________

```

### Todo

* Adds a todo task which has no time limit to finish
* Format: `todo DESCRIPTION`
* Example: `todo read a book`
* Example of expected output:

```
___________________________________________
Got it. I've added this task: 
[T][✘] read a book
Now you have 1 tasks in the list. 
___________________________________________
```

Exception:
* No Todo DESCRIPTION
* Example: `todo`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a todo cannot be empty.
Please input again!:)
___________________________________________
```

### Deadline

* Adds a deadline task which has to be completed before a given time
* Format：`deadline DESCRIPTION /by BYTIME`
* Example 1: `deadline submit a report /by 2020-12-21 12:45`
* Example of expected output 1:

```
___________________________________________
Got it. I've added this task: 
[D][✘] submit a report (by: Dec 21 2020  12:45)
Now you have 2 tasks in the list. 
___________________________________________
```

* Example 2: `deadline submit a report /by Sunday`
* Example of expected output 2:

```
___________________________________________
🙁 OOPS!!! The date is not valid
Please follow the format YYYY-MM-DD and input again.:)
___________________________________________
```

Exception 1:
* No Deadline DESCRIPTION
* Example: `deadline`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a deadline cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* No Deadline TIME
* Example: `deadline submit a report`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The time of the Deadline task is missing.
Please complete the time information. :)
___________________________________________
```

### Event

* Adds an event task which will start at a given time
* Format：`event DESCRIPTION /by ATTIME`
* Example 1: `event project group meeting /at 2021-12-21 23:59`
* Example of expected output 1:

```
___________________________________________
Got it. I've added this task: 
[E][✘] project group meeting (at: Dec 21 2021  23:59)
Now you have 3 tasks in the list. 
___________________________________________
```

* Example 2: `event project group meeting /at Sunday`
* Example of expected output 2:

```
___________________________________________
🙁 OOPS!!! The date is not valid
Please follow the format YYYY-MM-DD and input again.:)
___________________________________________
```


Exception 1:
* No Event DESCRIPTION
* Example: `event`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of an event cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* No Event TIME
* Example: `event group meeting`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The time of the Event task is missing.
Please complete the time information. :)
___________________________________________
```

### List

* Display all recorded tasks in the list
* Format: `list`
* Example of expected output:

```
___________________________________________
Here are the tasks in your list: 
1. [T][✘] read a book
2. [D][✘] submit a report (by: 05 Mar)
3. [E][✘] project group meeting (at: 2-4 pm Sunday)
___________________________________________
```
Exception:
* The list is empty
* Example: `list`
* Example of expected output:

```
___________________________________________
The list is empty.
You can add your task now! :)
___________________________________________
```


### Done

* Marks a certain task to be done
* Format: `done INDEX`
* Example: `done 1`
* Example of expected output:

```
___________________________________________
Nice! I've marked this task as done: 
[T][✓] read a book
___________________________________________
```

Exception 1:
* No done INDEX
* Example: `done`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a done cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* The given done INDEX is out of bound
* Example: `done 5`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The done index is not valid.
Please input again.:)
___________________________________________
```

Exception 3:
* The list is empty
* Example: `done 5`
* Example of expected output:

```
___________________________________________
The list is empty.
You can add your task now! :)
___________________________________________
```

### Delete

* Deletes a certain task
* Format: 'delete INDEX'
* Example: `delete 2`
* Example of expected output:

```
___________________________________________
Noted. I've removed this task: 
[D][✘] submit a report (by: 05 Mar)
Now you have 2 tasks in the list.
___________________________________________
```

Exception 1:
* No delete INDEX
* Example: `delete`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a delete cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* The given delete INDEX is out of bound
* Example: `delete 5`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The delete index is not valid.
Please input again.:)
___________________________________________
```

Exception 3:
* The list is empty
* Example: `delete 5`
* Example of expected output:

```
___________________________________________
The list is empty.
You can add your task now! :)
___________________________________________
```

### Find

* Finds the tasks that contains the certain keyword given by user
* Format: `find KEYWORD`
* Example: `find book`
* Example of expected output 1:

```
___________________________________________
Here are the matching tasks in your list: 
1. [T][✓] read a book
___________________________________________
```

* Example of expected output 2:

```
___________________________________________
There is no matching task in your list.
Please input another keyword! :)
___________________________________________
```

Exception 1:
* No find DESCRIPTION
* Example: `find`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a find cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* The list is empty
* Example: `find book`
* Example of expected output:

```
___________________________________________
The list is empty.
You can add your task now! :)
___________________________________________
```

### Date

* Finds all the tasks that are relevant to the specific date
* Format: `date [yyy-mm-dd]`
* Example: `date 2020-02-28 12:23`
* Example of expected output 1:

```
___________________________________________
Here are the tasks occurring on this specific date: 
1. [D][✘] return a book(by: Feb 28 2021 12:23)
___________________________________________
```

* Example of expected output 2:

```
___________________________________________
There is no task occurring on this specific date.
Please input another date! :)
___________________________________________
```

Exception 1:
* No date DESCRIPTION
* Example: `date`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The description of a date cannot be empty.
Please input again!:)
___________________________________________
```

Exception 2:
* The date format is incorrect
* Example: `date 123`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! The date is not valid
Please follow the format YYYY-MM-DD and input again.:)
___________________________________________
```

Exception 3:
* The list is empty
* Example: `date 2020-12-21`
* Example of expected output:

```
___________________________________________
The list is empty.
You can add your task now! :)
___________________________________________
```

### Bye

* Exits this running program
* Format: `bye`
* Example of expected output:

```
___________________________________________
Bye. Hope to see you again soon!
___________________________________________
```

#### Exception

* The command is not acceptable by Duke
* Example: `balh` or `hi`
* Example of expected output:

```
___________________________________________
🙁 OOPS!!! I'm sorry, but I don't know what that means :-(
Please input again!:)
___________________________________________
```
