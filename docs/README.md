# User Guide

## Features 

### Adding todo task: todo
add a task of type "todo" to the task list

### Adding todo task: deadline
add a task of type "deadline" to the task list

### Adding todo task: deadline
add a task of type "deadline" to the task list

### Listing all the task: list

###Marking a task as done: done
mark a task of a specific index as done

### Finding a task by keywords: find
find all the tasks whose names contain the keyword

###Deleting a task: delete
delete a task of a specific index

### End the project: bye

## Usage

### `todo [name_of_task]` - Adding todo task

add a task of type "todo" to the task list

Example of usage: 

`todo read books `

Expected outcome:

`Got it. I've added this task:
                              [T][✘]read books`
                              
                              
### `event [name_of_task] /at [time_of_event]` - Adding event task

add a task of type "event" to the task list

Example of usage: 

`event attend lecture /at Tonight `

Expected outcome:

`Got it. I've added this task:
                              [E][✘]attend lecture (at: Tonight)`
                              
                              
### `deadline [name_of_task] /by [time_to_complete]` - Adding deadline task

add a task of type "deadline" to the task list

Example of usage: 

`deadline return books /by Thursday `

Expected outcome:

`Got it. I've added this task:[D][✘]return books (by: Thursday) `
 
 
 
 ### `list` - listing all the tasks
 
 Example of usage: 
 
 `list`
 
 Expected outcome:
 
 ` 1. [T][✓]123    2. [D][✓]ddl1  (by: Thursday)`
 
 
 ### `find [keyword]` - Finding a task by keywords
 find all the tasks whose names contain the keyword
  
  Example of usage: 
  
  `find 12`
  
  Expected outcome:
  
  ` Here are the matching tasks in your list:
                               1. [T][✓]123`
                               
                               
 ### `done INDEX` - Marking a task as done
 mark a task of a specific index as done
  
  Example of usage: 
  
  `done 3`
  
  Expected outcome:
  
  ` Nice! I've marked this task as done: 
    ✓ abc`   
    
    
### `delete INDEX` - deleting a task
delete a task of a specific index
  
  Example of usage: 
  
  `ddelete 1`
  
  Expected outcome:
  
  ` Noted. I've removed this task: 
                   [T][✓]123`    

### `bye` - end of project                                                    