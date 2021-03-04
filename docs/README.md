# User Guide for Duke
### By Li Pingrui  

## Features 

* Record different types of tasks
Duke can record tasks in the form of ToDo, Deadline and Event. As the name suggests, ToDos are normal tasks, Deadlines are tasks that must be finished within certain time limits,  Events are tasks that will happen at certain time.

* Mark tasks as done and delete tasks
Duke can help users to mark the tasks they have done and delete tasks that users no longer need.

* Search tasks using keyword
Duke can search the task list with keyword given by users.

* Load and Store tasks
Duke will load task list from a specified file when launched and store any changes user made to the file for storage. Hence, there will be no information loss when users terminate Duke and the state will resume when Duke is launched next time.

## Usage

### Duke uses command line to interact with users

### The command supported by Duke are

#### 1.`todo <task name>`
Add a todo task to the list.
*Eg.* `todo read a book`

#### 2.`deadline <task name> /by <deadline of the task>`
Add a deadline task to the list. The task name and deadline are separated by "/by".
*Eg.* `deadline return a book /by Friday 10am`

#### 3.`event <task name> /at <time of the task>`
Add an event task to the list. The task name and time are separated by "/at".
*Eg.* `event meeting /at Friday 10am`

#### 4.`done <task index in task list>`
Mark a task as done. The index is the index of the task in the current complete task list.
*Eg.* `done 1`

#### 5.`delete <task index in task list>`
Delete a task. The index is the index of the task in the current complete task list.
*Eg.* `delete 1`

#### 6.`find <keyword>`
Search task list with the keyword. Tasks whose name contains the keyword will be displayed.
*Eg.* `search book`

#### 7.`list`
list current task list.

#### 8.`bye`
Terminate Duke.
