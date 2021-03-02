# User Guide

## Features

### View tasks in list: `list`
Show the list of tasks

#### Format: `list`

Output:
```text
Here are the tasks in your list:
1.[T][✘] dance
2.[D][✘] Finish CS3245 (by: Thurs 2pm)
3.[E][✘] sleep (at: 2pm-4pm)
4.[T][✘] danceeeee
```
### Mark task as completed: `done`
Marks a task as completed using task number

#### Format: `done INDEX`

Output:
```text
Nice! I've marked this task as done:
[T][✓]  dance
```
### Add a todo task: `todo`
Add a todo task to the task list

#### Format: `todo DESCRIPTION`

Output:
```text
Got it. I've added this task: 
    [T][✘]  run
Now you have 5 task(s) in the list.
```
### Add a deadline task: `deadline`
Add a deadline task to the task list

#### Format: `deadline DESCRIPTION /by TIME`

Output:
```text
Got it. I've added this task: 
    [D][✘]  submit CS3223 Project (by: Sunday 2359)
Now you have 6 task(s) in the list.
```
### Add an event task: `event`
Add an event task to the task list

#### Format: `event DESCRIPTION /at TIME`

Output:
```text
Got it. I've added this task: 
    [E][✘]  attend John's Birthday Party(at: Monday 1430 to Monday 1530)
Now you have 7 task(s) in the list.
```
### Find a task: `find`
Find a task from the task list

#### Format: `find DESCRIPTION`

Output:
```text
Here are the matching tasks in your list: 
1.[T][✘] danceeeee
```

