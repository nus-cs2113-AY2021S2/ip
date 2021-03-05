# User Guide for Duke <a ></a>
##### Duke is a memo application that can help you record the tasks to be done
<hr>

### Running Environment 
As a cross-platform application, Duke can run on any PCs or Desktops as long as it supports Java 11.

### Features 

* Record different types of tasks.

<p>Duke can record tasks in the form of ToDo, Deadline and Event. As the name suggests, ToDos are normal tasks, Deadlines are tasks that must be finished within certain time limit, Events are tasks that will happen at certain time.</p>

* Track the progress of task completion

<p>Duke can help users to mark the tasks they have done and delete tasks that they no longer need to keep track of the progress.</p>

* Search tasks using keyword

<p>Duke can search the task list with keyword given by users.</p>

* Load and Store tasks

<p>Duke will load task list from a specified file when launched and update any changes made by users to the file at real time. Hence, there will be no information loss when users terminate Duke and the state will resume when Duke is launched next time.</p>

## Usage

<p>Duke uses command line to interact with users</p>

### The command supported by Duke are

##### 1. List all tasks in current task list: `list`
<p>  Display all tasks in current task list with the type, name and completion status.</p>

* sample input 
  
        list

* sample output
  
        ____________________________________________________________
        Here are the tasks in your list:
        1.[D][ ]return the book (by: Friday 10pm)
        2.[E][X]meeting (at: Sunday)
        3.[T][ ]do homework
        4.[E][ ]hang out (at: 8pm)
        ____________________________________________________________


#### 2. Add a new ToDo task: `todo <task name>`
<p> Add a new ToDo task to task list. The task name should be specified after the command word "todo".</p>


* sample input

        todo read a book

* sample output

        ____________________________________________________________
        ToDo added:
            [T][ ]read a book
        Now you have 5 tasks in the list
        ____________________________________________________________


#### 3. Add a new Deadline task: `deadline <task name> /by <deadline of the task>`
<p> Add a new Deadline task to task list. The task name should be specified after the command word "deadline". The name and deadline should be separated by "/by".</p>


* sample input

        deadline return the book /by Friday 10am

* sample output

        ____________________________________________________________
        Deadline added:
            [D][ ]return the book (by: Friday 10am)
        Now you have 6 tasks in the list
        ____________________________________________________________


#### 4. Add a new Event task: `event <task name> /at <deadline of the task>`
<p> Add a new Event task to task list. The task name should be specified after the command word "evnt". The name and time of the event should be separated by "/at".</p>


* sample input

        event interview /at 8 Mar 1400

* sample output

        ____________________________________________________________
        Event added:
            [E][ ]interview (at: 8 Mar 1400)
        Now you have 7 tasks in the list
        ____________________________________________________________


#### 5. Mark a task as done: `done <task index in task list>`
<p> Mark a task as done by specifying the index of task after command word "done".</p>


* sample input

        done 1

* sample output

        ____________________________________________________________
        Nice! I've marked this task as done:
            [D][X]return the book (by: Friday 10pm)
        ____________________________________________________________


#### 6. Delete a task: ``delete <task index in task list>`
<p> Delete a task from task list by specifying the index of task after command word "delete".</p>


* sample input

        delete 1

* sample output

        ____________________________________________________________
        Noted! I've removed this task:
            [D][X]return the book (by: Friday 10pm)
        Now you have 6 tasks in the list.
        ____________________________________________________________


#### 7. Search tasks: `find <keyword>`
<p> Search task list for tasks whose names contain the keyword. The keyword should be specified after command word "find".</p>

* sample input

        find book

* sample output

        ____________________________________________________________
        Here are the matching tasks in your list:
        4.[T][ ]read a book
        5.[D][ ]return the book (by: Friday 10am)
        ____________________________________________________________


#### 8. Clear current task list: `clear`
<p> Clear current task list.</p>

* sample input

        clear

* sample output

        ____________________________________________________________
        Noted! I've removed all the tasks in current task list.
        ____________________________________________________________


#### 9. View help message: `help`
<p> Display help message.</p>

* sample input

        help

* sample output

        ____________________________________________________________
        Duke accepts the following commands:
        1.list
          List current task list
        2.todo <task name>
          Add a new todo task to task list
          Eg. todo do homework
        3.deadline <task name> /by <deadline>
          Add a new deadline to task list
          Eg. deadline return a book /by tonight
        4.event <task name> /at <time>
          Add a new event to task list
          Eg. event meeting /at 8pm
        5.done <task index>
          Mark a task as done
          Eg. done 1
        6.delete <task index>
          Delete a task from task list
          Eg. delete 2
        7.find <keyword>
          Search task list for tasks whose names contain the keyword
          Eg. find book
        8.clear
          Clear current task list
        9.help
          Show help message
        10.bye
          Terminate Duke
        ____________________________________________________________


#### 10. Terminate Duke: `bye`
<p> Terminate Duke.</p>

* sample input

        bye

* sample output

        ____________________________________________________________
        Bye. Hope to see you again soon!
        ____________________________________________________________