# <span style="color:#2975C7  ">Duke Project</span>
### <span style="color:teal">By Krithigha Panneer</span>

This project is named after the Java mascot _Duke_.
Given below are instructions on how to use it.

<a name="toc"></a>
## Table of Contents:

1. [Quick Start](#Quickstart)
    1. [Setting up .JAR file](#settingup)
1. [Running **Duke**](#runningduke)

    1. [Command: `todo <taskname>`](#todo)
    1. [Command: `deadline <taskname> /by YYYY-MM-DD HH:MM`](#deadline)
    1. [Command: `event <taskname> /at YYYY-MM-DD HH:MM`](#event)
    1. [Command: `done <task number>`](#done)
    1. [Command: `delete <task number>`](#delete)
    1. [Command: `list`](#list)
    1. [Command: `find <common keyword in tasks>`](#find)
    1. [Command: `bye`](#bye)

1. [Frequently Asked Questions (FAQ)](#faq)
1. [Command Summary](#summary)


<a name="quickstart"></a>
### Quick Start
The **Duke** app is run via a file with the extension `.jar`. The following steps will help you set up **Duke** to run on your computer.
<a name="settingup"></a>
1. Set up the `.jar` file by downloading the latest version from [here](https://github.com/nivikcivik/ip/releases)
    1. Click on `ip.jar` under the latest version (`v0.1` or beyond)
    1. Once the download prompt appears, `save` the file to your desired folder on your computer. This folder will be the _home folder_ for your tasks list.

1. Ensure you have installed `Java 11` on your computer.
    1. Look for the `terminal` or `command prompt` application on your computer.
    1. Type `java --version` in the terminal on your computer to check the version of java.

Back to [Table Of Contents](#toc)

<a name="runningduke"></a>
### Running <span style="color:#2975C7">Duke</span> Program
1. On the `terminal` or `command prompt`, locate your _home folder_ where you have saved the `.jar` file.
    1. Use commands `cd` to change directory ( or folder) and `ls` (or `dir` on command prompt on Windows) to view the files in your current directory.

1. Once you have entered the _home folder_, type the following command: `java -jar <name of .jar file>` e.g. `java -jar ip.jar`
1. You should see the **Duke** logo along with a welcome message.   
   **Duke** is now running!
    1. You would see this greeting message, for example:

```
    ____________________________________________________________
 Hello from
  ____        _        
 |  _ \ _   _| | _____ 
 | | | | | | | |/ / _ \
 | |_| | |_| |   <  __/
 |____/ \__,_|_|\_\___|

 Hello! I'm Duke.
 What can I do for you?
    ____________________________________________________________
```
1. If the _data.txt_ file does not already exist in the _home folder_ that you are in currently, **Duke** will tell you the file path where the _tasks.txt_ file is saved. It will be saved in your _home folder_ where your `.jar` file is located.

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="todo"></a>
#### i. Command `todo <task name>`
Adds a To-Do to your list  
indicated by `[T]` symbol  
**e.g.** `todo do assignment`  
Output:
```
    ____________________________________________________________
     Got it. I've added this task: 
      [T][ ] do assignment
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)


<a name="deadline"></a>
#### ii. Command `deadline <task name> /by <YYYY-MM-DD> <HH:MM>`
Adds a Deadline to your list  
indicated by `[D]` symbol
Time should be entered in 24-hour clock format  
If Time is not entered in 24-hour clock format _Duke_ will not interpret the timing appropriately

**e.g.** `deadline do assignment /by 2021-02-23 23:59`  
Output:
```
    ____________________________________________________________
     Got it. I've added this task: 
      [D][ ] do assignment(by: Feb 23 2021, 11:59 PM)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```
**e.g.** `deadline do assignment /by noon`  
Output:
```
    ____________________________________________________________
     Got it. I've added this task: 
      [D][ ] do assignment(by: noon)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```
Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="event"></a>
#### iii. Command `event <task name> /at <YYYY-MM-DD> <HH:MM>`
Adds an Event to your list  
indicated by `[E]` symbol  
Time should be entered in 24-hour clock format  
If Time is not entered in 24-hour clock format _Duke_ will not interpret the timing appropriately

**e.g.** `event birthday party /at 2020-02-28 23:59`  
Output:
```
    ____________________________________________________________
     Got it. I've added this task: 
      [E][ ] birthday party(at: Feb 28 2020, 11:59 PM)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```
**e.g.** `event birthday party /at noon`  
Output:
```
    ____________________________________________________________
     Got it. I've added this task: 
      [E][ ] birthday party(at: noon)
     Now you have 5 tasks in the list.
    ____________________________________________________________
```
Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="done"></a>
#### iv. Command `done <task number`
Marks a task at task number in list as '_done_'  
indicated by `[X]` in field beside task  
**e.g.** `done 2`  
Output:
```
    ____________________________________________________________
      Nice! I've marked this task as done: 
       [D][x] finish homework(by: Feb 23 2021, 11:59 PM)
    ____________________________________________________________
```
Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="delete"></a>
#### v. Command `delete <task number`
Removes a task at task number from list  
**_NOTE:_** This action changes the numbering of tasks in the list.  
**e.g.** `delete 2`  
Output:
``` 
 ____________________________________________________________
      Noted. I've removed this task:  
       [D][ ] do assignment(by: Feb 23 2021, 11:59 PM)
    ____________________________________________________________
```

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="list"></a>
#### vi. Command `list`
Prints all the current tasks along with dates and times
**e.g.** `list`  
Output:
``` 
    ____________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] do assignment
     2. [D][x] finish homework(by: Feb 23 2021, 11:59 PM)
     3. [D][ ] do assignment(by: noon)
     4. [E][ ] birthday party(at: noon`)
     5. [E][ ] birthday party(at: Feb 28 2020, 11:59 PM)
    ____________________________________________________________
```

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="find"></a>
#### vii. Command `find <common keyword in tasks>`
Finds and filters out the tasks with a desired keyword  
**e.g.** `find birthday`  
Output:
``` 
    ____________________________________________________________
     Here are the matching tasks in your list:
     4.[E][ ] birthday party(at: noon`)
     5.[E][ ] birthday party(at: Feb 28 2020, 11:59 PM)
    ____________________________________________________________
```

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)


<a name="bye"></a>
#### viii. Command `bye`
Exits the program.

Before ending the program, the tasks list will be saved in the _tasks.txt_ file, with an acknowledgement of the file path of tasks.txt   
**e.g.** `bye`  
Output:
```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________

```

Back to [Table Of Contents](#toc)  
Back to [Command Summary](#summary)

<a name="saving"></a>
### Saving of tasks list locally on your computer
When the command `bye` is given to **Duke**, it will automatically save the current task list from the **Duke** program in the _data.txt_ file (located in your _home folder_ ; file path specified at beginning for the first time)


<a name="editing"></a>
### Editing of tasks list locally on your computer
``` 
T | 0 | do assignment
D | 1 | finish homework| Feb 23 2021, 11:59 PM
D | 0 | do assignment| noon
E | 0 | birthday party| noon`
E | 0 | birthday party| Feb 28 2020, 11:59 PM
```
When you open the _tasks.txt_ file, you will notice that your tasks are saved and are separated by the '|' symbol. This is so that **Duke** can read your tasks data each time the program runs.

<span style="color:RED">**NOTE:**</span>. It is **highly discouraged** to edit this file directly instead of editing them on the **Duke** program, as it could cause some errors when **Duke** is loaded the next time. Simply change the individual fields (e.g. numbers on dates/times, the 1 or 0 to indicate done tasks, etc.).

Please do **not** change the line structure or the format of the file.

Back to [Table Of Contents](#toc)


<a name="faq"></a>
### Frequently Asked Questions (FAQ)



1. When I search for a keyword using the `find` command, or a particular task type using the `print` command, the list is **empty**.   
   I **only** see this output:
   ```
   ____________________________________________________________
   Here are the matching tasks in your list:
   ____________________________________________________________
   ```  
   What does this mean?
    - This simply means that  there are no results after searching through the tasks.

Back to [Table Of Contents](#toc)

<a name="summary"></a>
### Command Summary

Command  | Function
 :----  | :----
[todo \<task name\>](#todo)   | Adds a To-Do to your list
[deadline \<task name\> /by \<YYYY-MM-DD\> \<HH:MM\>](#deadline)   | Adds a Deadline to your list
[event \<task name\> /at \<YYYY-MM-DD\> \<HH:MM\>](#event)   | Adds an Event to your list
[done \<task number\>](#done)   | Marks a task at task number in list as '_done_'
[delete \<task number\>](#delete)   | Removes a task at task number from list
[list](#list)   | Prints all the current tasks along with dates and times
[find \<common keyword in tasks\>](#find)   | Finds and filters out the tasks with a desired keyword
[bye](#bye)   | Exits the program.

Back to [Table Of Contents](#toc)
