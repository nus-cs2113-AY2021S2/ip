# Duke User Guide

## Introduction
Duke is a personal assistant chatbox which helps you keep track of various to-do's, deadlines and events. The name Duke
chosen as a placeholder name, in honor of Duke, the Java Mascot.

## Features
#### Adding a To-do's: `todo`
Adds a to-do's to the task list.  

**Format**: `todo DESCRIPTION`    

Example:
```$xslt
todo attend CS2113T Lecture
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Got it. I've added this task:
       [T][✘] attend CS2113T Lecture
     Now you have 1 task in the list.
    ____________________________________________________________
```
#### Adding an Event: `event`
Adds an event to the task list. Note that the format for `TIME` should take the form of `YYYY-MM-DD`

**Format**: `event DESCRIPTION /at TIME`      

Example:
```$xslt
event NUS blood donation event /at 2021-05-01
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Got it. I've added this task:
       [E][✘] NUS blood donation event (at: 2021-05-01)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

#### Adding a Deadline: `deadline`
Adds a deadline to the task list. Note that the format for `TIME` should take the form of `YYYY-MM-DD`

**Format**: `deadline DESCRIPTION /by TIME`

Example:
```$xslt
deadline CG1112 design report /by 2021-03-07
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Got it. I've added this task:
       [D][✘] CG1112 design report (by: 2021-03-07)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

#### Listing all Task(s): `list`
Prints all task(s) in the list.  

**Format**: `list`

Example:
```$xslt
list
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][✘] attend CS2113T Lecture
     2.[E][✘] NUS blood donation event (at: 2021-05-01)
     3.[D][✘] CG1112 design report (by: 2021-03-07)
    ____________________________________________________________
```

#### Finding Task(s): `find`
Prints all tasks containing the keyword given by the user. 

**Format**:`find KEYWORD`  

Example:
```$xslt
find CS2113T
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Here are the matching task(s) in your list:
     1.[T][✘] attend CS2113T Lecture
    ____________________________________________________________
```

#### Deleting a Task: `delete`
Deletes the task with the given index. Note that the index given should be valid. 

**Format**:`delete INDEX`

Example:
```$xslt
delete 1
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Noted. I've removed this task:
       [T][✘] attend CS2113T Lecture
     Now you have 2 tasks in the list.
    ____________________________________________________________
```
#### Mark a Task as Done: `done`
Marks a task with the given index as done. Note that the index given should be valid.

**Format**:`done INDEX`  

Example:
```$xslt
done 1
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Nice! I've marked this task as done:
       [E][✓] NUS blood donation event (at: 2021-05-01)
    ____________________________________________________________
```
#### Find Task(s) with a Date: `date`
Prints all tasks occurring on a specified date.

**Format**:`date DATE`  

Example:
```$xslt
date 2021-05-01
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Here are the task(s) occurring on the same date in your list:
     1.[E][✘] NUS blood donation event (at: 2021-05-01)
    ____________________________________________________________
```
#### Exit the Application: `bye`
Exits the application.  

**Format**: `bye`

Example:
```$xslt
bye
```
Expected outcome:
```$xslt
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Command Summary

Action | Format & Example
--------------------------------|--------------------------------------------------------------
Add Todo | `todo DESCRIPTION`  e.g.`todo CG1112 final project`
Add Event | `event DESCRIPTION /at TIME`   e.g.`event CS2113T team meeting /at 2021-03-07 14:00`
Add Deadline | `deadline DESCRIPTION /by TIME`   e.g.`deadline submit CS2113T iP /by 2021-03-05 23:59`
List Task(s) | `list`
Find Task(s) | `find KEYWORD`   e.g.`find EE`
Delete Task | `delete INDEX`   e.g.`delete 2`
Done Task | `done INDEX`   e.g.`done 3`
Find Task on Date | `date DATE`   e.g.`date 2021-03-10`
Exit | `bye`
