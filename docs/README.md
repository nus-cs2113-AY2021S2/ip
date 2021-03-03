# User Guide
- [Introduction](#2)
- [Setup](#3)
- [Notes](#1)
-  [Features](#a) 
   - [Adding tasks](#a)
     - [todo](#a)
     - [deadline](#b)
     - [event](#c)
   - [Listing all tasks](#d)
   - [Completing a task](#e)
   - [Find](#f)
	 - [Description](#f)
	  - [Date](#g)
   - [Save and exit](#i)

***
# <a name="2"></a> Introduction

Your very own application to manage your to-do list!

# <a name="3"></a> Setup

&nbsp;&nbsp;&nbsp;&nbsp; 1. Copy the jar file 

&nbsp;&nbsp;&nbsp;&nbsp; 2. Launch the jar file using the following command in `CMD`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `java -jar [path to your runnable jar file]`

<br /><br>
&nbsp;&nbsp;&nbsp;&nbsp; In the event that some characters are not properly printed in the terminal, use the following commands:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `chcp 65001`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `java -jar -Dfile.encoding=UTF-8 [path to your runnable jar file]`


***

# <a name="1"></a> Notes

**General Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; Description - Describes the functionality of the feature

&nbsp;&nbsp;&nbsp;&nbsp; Formatting - Describes the code formatting to use a feature

&nbsp;&nbsp;&nbsp;&nbsp; Examples - Examples of how to use a feature

&nbsp;&nbsp;&nbsp;&nbsp; Example output - Snapshot of how the usage of a feature will appear in the application
<br /> <br>

**Additional notes:**

&nbsp;&nbsp;&nbsp;&nbsp; The square brackets specifies that user input is required

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `command [variable input]`

***

# Features
***
## <a name="a"></a> Adding tasks: Todo
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp; Adding a simple task using the `todo` command

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; `todo` [task description]

**Examples:**

&nbsp;&nbsp;&nbsp;&nbsp; Adding the task "read shakespeare"

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `todo read shakespeare`

&nbsp;&nbsp;&nbsp;&nbsp; Adding the task "finish java quiz"

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `todo finish java quiz`

**Example output:**
```
todo read shakespeare
____________________________________________________________
Got it. I've added this task: 
  [T][✘] read shakespeare
Now you have 1 tasks in the list.
____________________________________________________________
```

***
## <a name="b"></a> Adding tasks: Deadline

**Description:**

&nbsp;&nbsp;&nbsp;&nbsp; Adding a deadline task using the `deadline` command.

&nbsp;&nbsp;&nbsp;&nbsp; A deadline consist of a **description** and a **date**.

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; `deadline [task description] /by [date in format YYYY-MM-DD]`

**Examples**

&nbsp;&nbsp;&nbsp;&nbsp; Adding the deadline "read shakespeare by 8 Mar 2021"

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `deadline read shakespeare /by 2021-03-08`

&nbsp;&nbsp;&nbsp;&nbsp; Adding the deadline "finish java quiz by 16 Nov 2021"

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `deadline finish java quiz /by 2021-11-16`

**Example output**
```
deadline read shakespeare /by 2021-03-08
____________________________________________________________
Got it. I've added this task: 
  [D][✘] read shakespeare (by: Mar-08-2021 )
Now you have 1 tasks in the list.
____________________________________________________________
```
***

## <a name="c"></a> Adding tasks: Event
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp; Adding an event task using the `event` command.

&nbsp;&nbsp;&nbsp;&nbsp; An event consist of a **description** and a **date**.

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; `event [task description] /at [date in format YYYY-MM-DD]`

**Examples**

&nbsp;&nbsp;&nbsp;&nbsp; Adding the event "read shakespeare by 8 Mar 2021"

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `event shakespeare's play /at 2021-03-08`

&nbsp;&nbsp;&nbsp;&nbsp; Adding the event "java quiz by 16 Nov 2021"

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; `event java quiz /at 2021-11-16`

**Example output**
```
event shakespeare's play /at 2021-03-08
____________________________________________________________
Got it. I've added this task: 
  [E][✘] shakespeare's play (by: Mar-08-2021 )
Now you have 1 tasks in the list.
____________________________________________________________
```


## <a name="d"></a> Listing all tasks
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp;List all tasks (todo, deadline, event)

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp;`list`

**Example output**
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][✘] read shakespeare
2.[D][✘] read shakespeare (by: Mar-08-2021 )
3.[E][✘] shakespeare's play (at: Mar-08-2021 )
____________________________________________________________
```
***

## <a name="e"></a> Completing a task
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp;Set the task to be done.

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp;`done [index]`

**Examples**

&nbsp;&nbsp;&nbsp;&nbsp;`done 1`

**Example output**
```
Here are the tasks in your list:
1.[T][✘] read shakespeare
2.[D][✘] read shakespeare (by: Mar-08-2021 )
3.[E][✘] shakespeare's play (at: Mar-08-2021 )
____________________________________________________________
done 1
____________________________________________________________
Nice! I've marked this task as done: 
[✓] read shakespeare
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][✓] read shakespeare
2.[D][✘] read shakespeare (by: Mar-08-2021 )
3.[E][✘] shakespeare's play (at: Mar-08-2021 )
____________________________________________________________
```

## <a name="f"></a> Find tasks: Description
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp;Find specific tasks based on similar descriptions.

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; `find [task description]`

**Examples**

&nbsp;&nbsp;&nbsp;&nbsp; Find any tasks with the phrase "read shakespeare" in description

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `find read shakespeare`

&nbsp;&nbsp;&nbsp;&nbsp; Find any tasks with the word book description

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`find book`

**Example output**
```
find read shakespeare
____________________________________________________________
Here are the matching tasks in your list:
1.[T][✓] read shakespeare
2.[D][✘] read shakespeare (by: Mar-08-2021 )
____________________________________________________________
```

## <a name="g"></a> Find tasks: Date
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp;Find specific tasks based on date input.

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp; Find all events that occur on a specific date

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `findAt [Date with format YYYY-MM-DD]`

&nbsp;&nbsp;&nbsp;&nbsp; Find all deadlines that occurs before a specific date

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `findBy [Date with format YYYY-MM-DD]`

**Examples**

&nbsp;&nbsp;&nbsp;&nbsp; Find events happening on 10 March 2021

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `findAt 2021-03-10`

&nbsp;&nbsp;&nbsp;&nbsp; Find all deadlines that occurs before a 10 March 2021

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `findBy 2021-03-10`

**Example output**
```
Here are the tasks in your list:
1.[T][✓] read shakespeare
2.[D][✘] read shakespeare (by: Mar-08-2021 )
3.[E][✘] shakespeare's play (at: Mar-08-2021 )
4.[D][✘] watch wandaVision (by: Mar-10-2021 )
5.[E][✘] homework submission (at: Mar-08-2021 )
____________________________________________________________
findAt 2021-03-08
____________________________________________________________
Here are the matching tasks in your list:
1.[E][✘] shakespeare's play (at: Mar-08-2021 )
2.[E][✘] homework submission (at: Mar-08-2021 )
____________________________________________________
findBy 2021-03-10
____________________________________________________________
Here are the matching tasks in your list:
1.[D][✘] read shakespeare (by: Mar-08-2021 )
2.[D][✘] watch wandaVision (by: Mar-10-2021 )
```

## <a name="i"></a> Save and exit
**Description:**

&nbsp;&nbsp;&nbsp;&nbsp;Save all tasks in file and exits

**Formatting:**

&nbsp;&nbsp;&nbsp;&nbsp;`bye`

**Example output**
```
bye
____________________________________________________________
File is overwritten.
Bye. Hope to see you again soon!
____________________________________________________________
```

