# User Guide

### Introduction

Welcome to Duke - A Star Wars - themed personal assistant here to help you with
your every need! From adding events to notifying you about deadlines, Duke organizes your day
so you don't have to.
## Task Categories

### Todo

This is the simplest form of tasks you can communicate to Duke - these tasks
only have a simple description! No deadline, no duration!

### Deadline
This category of task has a deadline associated with it - you can also set
when this task should be completed!

### Event
This final category of task has, similar to the deadline, a duration associated
with it. Inputting an event allows you to set a time duration during which the
event is planned to occur.

## Features


### Saved Task Lists

You can now back up all the tasks input to Duke, and bring all this information
with you, wherever you go! Duke-on-the-go!

### Viewing Tasks

Viewing tasks allows you to see an overview of all the tasks you have in your
task list - this also includes tasks that have already been completed!

### Adding Tasks

Adding tasks is a breeze - you'll only need to type in one line of text into the
command-line style user interface in order to communicate your intentions to Duke!

### Removing Tasks

We have made removing tasks easily done - simply choose a number that appears next
to the task you wish to have deleted, and voila!


## Usage

### `todo { description of todo }` - Add a todo task to Duke

This command allows you to add a Todo task to Duke's master task list!

Example of usage: 

`todo Execute Order 66`

### `deadline` - Add a deadline task to Duke

This command allows you to add a Deadline task to Duke's master task list!

Example of usage:

`deadline Submit Launch Codes || 12-02-2021 2:00 PM`

### `event` - Add an event task to Duke

This command allows you to add a Event task to Duke's master task list!

Example of usage:

`event Submit blaster for inspection || 12-02-2021 2:00 PM`

### `list` - Shows all tasks

This command tells Duke to show you all the tasks that you currently
have outstanding - this includes tasks that have been marked as done

Example of usage:

`list`

### `done { task number }` - Mark the desired task as complete

This tells Duke that you have completed a Task! The task number appears
on the screen just before the task details - see `list` for more details

Example of usage:

`done 12` - This marks the task number 12 (as shown by the `list` command) as 
complete


### `delete { task number }` - Deletes the desired task

This tells Duke to delete a Task! The task number appears
on the screen just before the task details - see `list` for more details

Example of usage:

`delete 12` - This deletes the task number 12 (as shown by the `list` command) and
it will no longer appear when `list` is called