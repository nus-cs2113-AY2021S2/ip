# User Guide

## Introduction
Welcome to Duke! Duke is a personal assistant to keep track of your tasks, deadlines and more!

## Features 

### Add Todo
What do you need to do? Just let Duke know and Duke will collate a list for you!

### Add Deadline 
Have an assignment coming up? Of course, the deadline is important! Let Duke know what the assignment is and when the deadline is, and Duke will handle the rest.

### Event
Important event coming up? Is it a birthday? Ceremony? No worries, just let Duke know the details.

### View List of Tasks
Want to see what you have on your plate at the moment? Duke can show you exactly what is left on your list.

### Clearing of Tasks
Finished your task? Congratulations! Duke can remove it from your list (now go on and do the rest!)

## Usage

### `todo {task description}` - Add a task to your todo list
This commmand allows you to add task that you would like to do into your todo list

Example of usage: 

`todo buy tesla stock`

Expected outcome:

`The following task has been added:`

`Todo : [✘] buy tesla stock`

`You now have 1 tasks in your list!`

### `deadline {task description} / {task deadline}` - Add a deadline to your todo list
This command allows you to add an upcoming deadline to your todo list, together with the timing details

Example of usage: 

`deadline deposit money / monday 10am` - the deadline is separated from the task with '/'

Expected outcome:

`The following task has been added:`
`Deadline : [✘] deposit money || Due by: monday 10am`
`You now have 2 tasks in your list!`

### `event {event description} / {event date}` - Add an event to your todo list
This command allows you to add an event that is coming up, and the date details

Example of usage: 

`event cheryl's birthday / monday` - event date is separated from the event with '/'

Expected outcome:

`event cheryl's birthday / monday`

`The following task has been added:`

`Event : [✘] cheryl's birthday || Happening on: monday`

`You now have 3 tasks in your list!`

### `list` - Displays the list of tasks
This command allows you to get an overview of all the tasks in you todo list

Example of usage: 

`list`

Expected outcome:

`1: Todo : [✘] buy tesla shares`

`2: Deadline : [✘] deposit money || Due by: monday 10am`

`3: Event : [✘] cheryl's birthday || Happening on: monday`

### `done {number}` - Sets a task as done
This command allows you to set a task as completed -> sense of accomplishment!

Example of usage: 

`done 1` - 1 corresponds to the number in the list

Expected outcome:

`Nice! I've marked this task as done:`
`Todo : [✓] buy tesla shares`

### `delete {number}` - Removes a task from your list
This command allows you to remove a task that you have completed from your list -> Marie Kondo your life!

Example of usage: 

`delete 1` - 1 corresponds to the number in the list

Expected outcome:

`The deleted task is : Todo : [✓] buy tesla shares`

`Noted. I've removed this task:`

`Todo : [✓] buy tesla shares`


### `find {query}` - Finds the task matching the query 
This command allows you to find a specific task with a certain query

Example of usage: 

`find deposit` 

Expected outcome:

`We found these tasks matching the query: hw`

`1. Deadline : [✘] deposit money || Due by: monday 10am`
