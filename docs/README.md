# User Guide

## Features 

### Listing all tasks: `list`
<div>Shows a list of tasks.</div>
<div>Format: list</div>
<li>Any parameters after list will be ignored</li>

### Add a todo task: `todo`
<div>Adds a todo task into todo list</div>
<div>Format: todo NAME</div>
<li>Reads the name given as the name of the task</li>

### Add a event task: `event`
<div>Adds an event task into todo list</div>
<div>Format: event NAME /at TIME</div>
<li>Reads the name and uses it as the name of the task</li>
<li>Reads the time and uses it as a time period for this task</li>

### Add a deadline task: `deadline`
<div>Adds a deadline task into todo list</div>
<div>Format: deadline NAME /by TIME</div>
<li>Reads the name and uses it as the name of the task</li>
<li>Reads the time and uses it as a time period for this task</li>

### Delete a task: `delete`
<div>Deletes a task in the todo list</div>
<div>Format: delete INDEX</div>
<li>Takes the index as input and deletes tasks stored in that particular index</li>

### Mark a task as completed: `done`
<div>Marks a task as completed</div>
<div>Format: done INDEX</div>
<li>Takes the index as input and mark tasks stored in that particular index as completed</li>

### Find a task name: `find`
<div>Finds tasks which contains a certain name</div>
<div>Format: find NAME</div>
<li>Searches for task names that contain NAME</li>
