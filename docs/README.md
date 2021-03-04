# Duke's User Guide

## Quick start to Jovan's Duke program!
1) Ensure that you have Java 11 or above installed.
2) Download the latest version of `Duke` from [here](https://github.com/jovanhuang/ip/releases/download/A-Release/IP.jar).

## Features in Duke Program

### View tasks in list: `list`
Show the list of tasks

#### Format: `list`
##### Example of Usage:
Input:
```text
list
```

Output:
```text
Here are the tasks in your list:
1.[T][ ] dance
2.[D][ ] Finish CS3245 (by: Thurs 2pm)
3.[E][ ] sleep (at: 2pm-4pm)
4.[T][ ] danceeeee
```
### Delete tasks : `delete`
Delete task using task number

#### Format: `delete TASKNUMBER`
##### Example of Usage:
Input:
```text
delete 3
```

Output:
```text
Noted. I've removed this task:
[E][ ] sleep (at: 2pm-4pm)
Now you have 3 tasks in the list.
```
### Mark task as completed: `done`
Marks a task as completed using task number

#### Format: `done TASKNUMBER`
##### Example of Usage:
Input:
```text
done 1
```


Output:
```text
Nice! I've marked this task as done:
[T][X]  dance
```
### Add a todo task: `todo`
Add a todo task to the task list

#### Format: `todo TODO_DESCRIPTION`
##### Example of Usage:
Input:
```text
todo run
```
Output:
```text
Got it. I've added this task: 
    [T][ ]  run
Now you have 5 task(s) in the list.
```
### Add a deadline task: `deadline`
Add a deadline task to the task list

#### Format: `deadline DEADLINE_DESCRIPTION /by TIME`
##### Example of Usage:
Input:
```text
deadline submit CS3223 Project /by Sunday 2359
```
Output:
```text
Got it. I've added this task: 
    [D][ ]  submit CS3223 Project (by: Sunday 2359)
Now you have 6 task(s) in the list.
```
### Add an event task: `event`
Add an event task to the task list

#### Format: `event EVENT_DESCRIPTION /at TIME`
##### Example of Usage:
Input:
```text
event attend John's Birthday Party /at Monday 1430 to 1530
```

Output:
```text
Got it. I've added this task: 
    [E][ ]  attend John's Birthday Party (at: Monday 1430 to Monday 1530)
Now you have 7 task(s) in the list.
```
### Find a task: `find`
Find a task from the task list

#### Format: `find KEYWORD`
##### Example of Usage:
Input:
```text
find danceee
```

Output:
```text
Here are the matching tasks in your list: 
1.[T][ ] danceeeee
```
### Terminate Program: `bye`
Quit program

#### Format: `bye`
##### Example of Usage:
Input:
```text
bye
```

Output:
```text
Bye. Hope to see you again soon!
________________________________
```
## FAQ

Q: How do I start this program?

A: After downloading the jar file, copy the absolute path of jar file and run the following on your command line:

```java -jar ABSOLUTE_PATH_OF_JAR_FILE```

## Command Summary
* ```list```
* ```delete TASKNUMBER```
* ```done TASKNUMBER```
* ```todo TODO_DESCRIPTION```
* ```deadline DEADLINE_DESCRIPTION /by TIME```
* ```event EVENT_DESCRIPTION /at TIME```
* ```find KEYWORD```
* ```bye```

## The end