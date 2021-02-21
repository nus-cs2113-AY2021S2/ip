# Duke User Guide

## Features 
1. Add tasks to the list
   1. todo task
   1. deadline task
   1. event task
2. View all tasks in the list
3. Mark task as done 
4. Store updated task list in duke.txt
5. Delete task
6. Search keyword in all tasks

### Feature 1
Enter "todo **task description**" to add a todo task in the task list\
Enter "deadline **task description** /by **date time**" to add deadline task in the task list\
Enter "event **task description** /at **date time**" to add a todo task\
User can input anything to replace the **bold** command part
### Feature 2
Enter "list" to view all tasks in the current task list
### Feature 3
Enter "done **task number**" to mark that task as done\
User can input any task number to replace the **bold** command part
### Feature 4
This feature is achieved automatically without user command
### Feature 5
Enter "delete **task number**"\
User can input any task number to replace the **bold** command part
### Feature 6
Enter "find **task description**"\
User can input anything they want to search to replace the **bold** command part

## Usage

### `Keyword` - todo / deadline / event
Add Todo, Deadline and Event task in the task list

Example of usage: \
`todo study`\
`deadline homework /by Monday`\
`event meeting /at tmr 2pm`

Expected outcome:\
`Got it. I've added this task:`\
`[T][✘] study`\
`Now you have 1 tasks in the list.`

`Got it. I've added this task:`\
`[D][✘] homework (by: Monday)`\
`Now you have 2 tasks in the list. `

`Got it. I've added this task:`\
`[E][✘] meeting (at: tmr 2pm)`\
`Now you have 3 tasks in the list. `

### `Keyword` - done 
Mark certain task as done

Example of usage: \
`done 1`

Expected outcome:\
`Nice! I've marked this task as done:`\
`[T][✓] study`

### `Keyword` - delete
delete certain task in the list

Example of usage: \
`delete 2`

Expected outcome:\
`Noted. I've removed this task:`\
`[D][✘] homework (by: Monday)`\
`Now you have 2 tasks in the list.`

### `Keyword` - list
view the updated task list

Example of usage: \
`list`

Expected outcome:\
`1.[T][✓] study`\
`2.[E][✘] meeting (at: tmr 2pm)`


### `Keyword` - find
find tasks in the list that matches the keyword

Example of usage: \
`find tmr`

Expected outcome:\
`Here are the matching tasks in your list:`\
`2.[E][✘] meeting (at: tmr 2pm))`
