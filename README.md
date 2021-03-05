# Duke Project
----------------------------------------------------------------------------------------
#### By Noor Sarrah

This program is named after Java mascot Duke.
Given below are the instructions on how to use it

<a name = "table-of-contents"></a>
## Table of Contents
1. [Quick Start](#Quickstart)

   1. [Setting up in Intellij](#SetUpIntellij)
   1. [Setting up Duke](#SetUpDuke)
  
1. [Running Duke](#RunDuke)

   1. [Command: **`todo <task name>`**](#todo)
   2. [Command: **`deadline <task name> /by <...>`**](#deadline)
   3. [Command: **`event <task name> /at <...>`**](#event)
   4. [Command: **`done <task number>`**](#done)
   5. [Command: **`list`**](list)
   6. [Command: **`delete <task number>`**](#delete)
   7. [Command: **`find <keywork in tasks>`**](#find)
   8. [Command: **`bye`**](#bye)

1. [Command Summary](#Commandsummary)

<a name = "Quickstart"></a>
## Quick Start

<a name = "SetUpIntellij"></a>
### Setting up in Intellij
#### On IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).

<a name = "SetUpDuke"></a>
### Setting up Duke
1. On the terminal, locate your home folder where you have saved the .jar file.

   1. cd to change directory
   1. ls to view files in the current directory
   
1. Once you have entered the home folder, type the following command: java -jar          <name of .jar file> 

   1. You should see the Duke logo along with a welcome message. Duke is now ready to be used!
   1. You would see this greeting message, for example:
 
  ```
        Unable to access file at C:\Users\User\Desktop\ip final/duke.txt
        To save your task locally,
        A new file has been created at:
        C:\Users\User\Desktop\ip final\duke.txt

Hello from
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

--------------------------------------------------------------------------------
Hello! I'm Duke
What can I do for you?
--------------------------------------------------------------------------------
   ```
Back to [Table of Contents](#table-of-contents)

<a name = "RunDuke"></a>
## Running Duke
   
<a name = "todo"></a>
i. Command: `todo <task name>`<br/>Adds a To-Do to your list<br/>[T] symbol represents To-Do task<br/>e.g. `todo read book`

Output:
```
--------------------------------------------------------------------------------
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 task in the list.
--------------------------------------------------------------------------------

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "deadline"></a>
ii. Command `deadline <task name> /by <…>`<br/>Adds a Deadline to your list<br/>[D] symbol represents Deadline task<br/>Time should be stated inside <…><br/>e.g. `deadline finish homework /by 2pm Monday`

Output:
```
--------------------------------------------------------------------------------
 Got it. I've added this task:
   [D][ ] finish homework (by: 2pm Monday)
 Now you have 2 tasks in the list.
--------------------------------------------------------------------------------

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "event"></a>
iii. Command `event <task name> /at <…>`<br/>Adds an Event to your list<br/>[E] symbol represents Event task<br/>Time should be stated inside <…><br/>e.g. `event family dinner/at 5-6pm Saturday`

Output:
```
--------------------------------------------------------------------------------
 Got it. I've added this task:
   [E][ ] family dinner(at: 7-10pm Saturday)
 Now you have 3 tasks in the list.
--------------------------------------------------------------------------------

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "done"></a>
iv. Command `done <task number>`<br/>Marks task with <task number> in list as 'done'<br/>indicated by [X] in field beside task<br/>e.g. `done 2`
   
Output:
```
--------------------------------------------------------------------------------
 Nice! I've marked this task as done:
 [D][X] finish homework (by: 2pm Monday)
--------------------------------------------------------------------------------
   
```

Your *tasks.txt* file will look like this:
```
T -->   --> be happy
D --> X --> finish homework --> 2pm Monday
E -->   --> family dinner --> 7-10pm Saturday

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "list"></a>
v. Command `list`<br/>Prints all the tasks along with dates and times<br/>e.g. `list`

Output:
```
--------------------------------------------------------------------------------
 Here are the tasks in your list:
 1. [T][ ] read book
 2. [D][ ] finish homework(by: 2pm Monday)
 3. [E][ ] book club(at: 11-12pm Tuesday)
 4. [E][ ] family dinner(at: 7-10pm Saturday)
--------------------------------------------------------------------------------

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "delete"></a>
vi. Command `delete <task number>`<br/>Removes task with <task number> in list<br/>e.g. `delete 2`
   
Output:
```
--------------------------------------------------------------------------------
 Noted. I've removed this task:
 [D][X] finish homework (by: 2pm Moday)
 Now you have 2 tasks in the list.
---------------------------------------------------------------------------------
```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "find"></a>
vii. Command `find <keyword in tasks>`<br/>Finds and filters out the tasks with <keyword><br/>e.g. `find book`
   
Output:
```
---------------------------------------------------------------------------------
 Here are the matching tasks in your list:
 1. [T][X] read book
 2. [E][X] book club (at: 11-12pm Tuesday)
---------------------------------------------------------------------------------

```
Back to [Table of Contents](#table-of-contents)<br/>Back to [Command Summary](#Commandsummary)

<a name = "bye"></a>
viii. Command `bye`<br/>Exits the program.<br/>e.g. `bye`

Output:
```
--------------------------------------------------------------------------------
Bye. Hope to see you again soon!
--------------------------------------------------------------------------------
```
NOTE: Before ending the program, all the tasks will be saved in *tasks.txt* file.

<a name = "Commandsummary"></a>
## Command Summary
Command   |Function
------------ | -------------
`todo <task name>`| Adds a To-Do to your list
`deadline <task name> /by <...>` | Adds a Deadline to your list
`event <task name> /at <...>` | Adds an Event to your list
`done <task number>` |Marks a task at task number in list as done
`delete <task number>` | Removes a task at task number from list
`list` | Prints all the tasks along with dates and times
`find <common keyword in tasks>` | Finds and filters out the tasks with a desired keyword
`bye` | Exits the program.

