# <span style="color:#2975C7  ">Duke Project</span>
### <span style="color:teal">By Nikhila Ravikumar</span>

This project is named after the Java mascot _Duke_.

Given below are instructions on how to use it.

<a name="toc"></a>
## Table of Contents: 

1. [Quick Start](#Quickstart)
   1. [Setting up .JAR file](#settingup)
1. [Running **Duke**](#runningduke)
   1. [Command: `hello`, `hey`, `hi`](#hello)
   1. [Command: `help`](#help)
   1. [Command: `todo <taskname>`](#todo)
   1. [Command: `deadline <taskname> /by YYYY-MM-DD HH:MM`](#deadline)
   1. [Command: `event <taskname> /at YYYY-MM-DD HH:MM`](#event)
   1. [Command: `done <task number>`](#done)
   1. [Command: `delete <task number>`](#delete)
   1. [Command: `save`](#save)
   1. [Command: `list`](#list)
   1. [Command: `find <common keyword in tasks>`](#find)
   1. [Command: `print type <task type>`](#printtype)
   1. [Command: `print date <task date YYYY-MM-DD>`](#printdate)
   1. [Command: `print filepath`](#printfilepath)
   1. [Command: `bye`](#bye)

1. [Saving of tasks list locally on your computer](#saving)
1. [Editing of tasks list locally on your computer](#editing)
1. [Frequently Asked Questions (FAQ)](#faq)
1. [Command Summary](#summary)


<a name="settingup"></a> <a name="quickstart"></a>
### Quick Start
The **Duke** app is run via a file with the extension `.jar`. The following steps will help you set up **Duke** to run on your computer.
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
I have created a file at this location:
C:\Users\user\Desktop\tasks.txt
to store all your tasks!
____________________________________________________________
Hello from
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
Hello! I'm Duke :D Be nice to me:)

Your tasks list is saved here:
C:\Users\user\Desktop\tasks.txt
Type 'help' if you need help.
____________________________________________________________
   
```
1. If the _tasks.txt_ file does not already exist in the _home folder_ that you are in currently, **Duke** will tell you the file path where the _tasks.txt_ file is saved. It will be saved in your _home folder_ where your `.jar` file is located.

Back to [Table Of Contents](#toc)

<a name="hello"></a>
#### i. Command: `hello`, `hey`, `hi` 
Prints a friendly greeting  
**e.g.** `hey`  
Output:
``` 
____________________________________________________________
 Hello to you too. I'm here to help you:)
 Give me something to do!
 Type 'help' if you need help.
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="help"></a>
#### ii. Command: `help` 
Prints the command formats that **Duke** will respond to.  
**e.g.** `help`  
Output:
``` 
____________________________________________________________
 Try entering commands like :
 help,
 list,
 save,
 add new todo <taskName>
 add deadline <taskName> /by <date YYYY-MM-DD> <time hh:mm>
 add event    <taskName> /at <date YYYY-MM-DD> <time hh:mm>
 done <task number>,
 delete <task number>
 find <common keyword in tasks>
 print type <task type> (to filter based on type)
 print date <task date YYYY-MM-DD> (to filter based on date)
 print filepath
 bye
 Remember: be nice!
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="todo"></a>
#### iii. Command `todo <task name>` 
Adds a To-Do to your list
indicated by `[T]` symbol  
**e.g.** `todo be happy`  
Output:
```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] be happy
 Now you have 1 task in the list.
____________________________________________________________
```

Back to [Table Of Contents](#toc)


<a name="deadline"></a>
#### iv. Command `deadline <task name> /by <YYYY-MM-DD> <HH:MM>` 
Adds a Deadline to your list
indicated by `[D]` symbol
Time should be entered in 24-hour clock format  
**e.g.** `deadline finish homework /by 2021-02-23 23:59`  
Output:
```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] finish homework (by: 23 Feb 2021 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="event"></a>
#### v. Command `event <task name> /at <YYYY-MM-DD> <HH:MM>` 
Adds an Event to your list
indicated by `[E]` symbol
Time should be entered in 24-hour clock format  
**e.g.** `event birthday party /at 2020-02-28 23:59`  
Output:
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] birthday party (at: 28 Feb 2020 23:59)
 Now you have 3 tasks in the list.
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="done"></a>
#### vi. Command `done <task number` 
Marks a task at task number in list as '_done_'
indicated by `[X]` in field beside task  
**e.g.** `done 2`  
Output:
```
____________________________________________________________
 Nice! I've marked this task as done:
 [D][X] finish homework (by: 23 Feb 2021 23:59)
____________________________________________________________
```
Your _tasks.txt_ file will look like this:
``` 
T ~~   ~~ be happy
D ~~ X ~~ finish homework ~~ 23 Feb 2021 ~~ 23:59
E ~~   ~~ birthday party ~~ 28 Feb 2020 ~~ 23:59
```

Back to [Table Of Contents](#toc)

<a name="delete"></a>
#### vii. Command `delete <task number` 
Removes a task at task number from list  
**_NOTE:_** This action changes the numbering of tasks in the list.  
**e.g.** `delete 2`  
Output:
``` 
____________________________________________________________
 Noted. I've removed this task:
   [D][X] finish homework (by: 23 Feb 2021 23:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="save"></a>
#### viii. Command `save` 
Saves the current list in the _tasks.txt_ file.  
This feature was implemented to ensure there's no data loss even if you abruptly end the program without the 'bye' command.  
**e.g.** `save`  
Output:
``` 
____________________________________________________________
 Your tasks have been successfully saved at:
 C:\Users\user\Desktop\tasks.txt
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="list"></a>
#### ix. Command `list` 
Prints all the current tasks along with dates and times   
Prints current time and date for reference  
**e.g.** `list`  
Output:
``` 
  -----------------------------
  Local Time: 4 Mar 2021 12:35
  -----------------------------
 Here are the tasks in your list:
 1.[T][ ] be happy
 2.[D][ ] return book (by: 4 Mar 2021 17:00)
 3.[T][ ] read book
 4.[E][ ] go to book club (at: 3 Mar 2021 12:00)
 5.[E][ ] birthday party (at: 28 Feb 2020 23:59)
 6.[D][ ] buy dress for birthday party (by: 28 Feb 2020 23:00)
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="find"></a>
#### x. Command `find <common keyword in tasks>` 
Finds and filters out the tasks with a desired keyword  
**e.g.** `find book`  
Output:
``` 
____________________________________________________________
 Here are the matching tasks in your list:
 1. [D][ ] return book (by: 4 Mar 2021 17:00)
 2. [T][X] read book
 3. [E][X] go to book club (at: 3 Mar 2021 12:00)
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="printtype"></a>
#### xi. Command `print type <task type>` 
Prints out all tasks of a certain kind (e.g. deadlines, etc.)  
**e.g.** `print type event`  
Output:
``` 
____________________________________________________________
[E][ ] birthday party (at: 28 Feb 2020 23:59)
[E][X] go to book club (at: 3 Mar 2021 12:00)
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="printdate"></a>
#### xii. Command `print date <task date YYYY-MM-DD>` 
Prints out all tasks with a certain date  
**e.g.** `print date 2020-02-28`  
Output:
``` 
____________________________________________________________
[E][ ] birthday party (at: 28 Feb 2020 23:59)
[D][ ] buy dress for birthday party (by: 28 Feb 2020 23:00)
____________________________________________________________
```

Back to [Table Of Contents](#toc)

<a name="printfilepath"></a>
#### xiii. Command `print filepath` 
Prints path of the _tasks.txt_ file.  
**e.g.** `print filepath`  
Output:
```
____________________________________________________________
 Your tasks list is saved here:
 C:\Users\user\Desktop\tasks.txt
____________________________________________________________ 
```

Back to [Table Of Contents](#toc)

<a name="bye"></a>
#### xiv. Command `bye` 
Exits the program.

Before ending the program, the tasks list will be saved in the _tasks.txt_ file, with an acknowledgement of the file path of tasks.txt   
**e.g.** `bye`  
Output:
```
____________________________________________________________
 Your tasks have been successfully saved at:
 C:\Users\user\Desktop\tasks.txt
 I learnt more about you, kind human!
 I won't forget you when I take over the world one day:)
____________________________________________________________

```

Back to [Table Of Contents](#toc)

<a name="saving"></a>
### Saving of tasks list locally on your computer 
When the command `bye` is given to **Duke**, it will automatically save the current task list from the **Duke** program in the _tasks.txt_ file (located in your _home folder_ ; file path specified at beginning for the first time)  
However, you can manually enter the `save` command (refer to Section **viii.**)

Back to [Table Of Contents](#toc)


<a name="editing"></a>
### Editing of tasks list locally on your computer 
``` 
T ~~   ~~ be happy
D ~~ X ~~ finish homework ~~ 23 Feb 2021 ~~ 23:59
E ~~   ~~ birthday party ~~ 28 Feb 2020 ~~ 23:59
```
When you open the _tasks.txt_ file, you will notice that your tasks are saved and are separated by the ' ~~ ' symbol. This is so that **Duke** can read your tasks data each time the program runs.

<span style="color:RED">**NOTE:**</span>. It is **highly discouraged** to edit this file directly instead of editing them on the **Duke** program, as it could cause some errors when **Duke** is loaded the next time. Simply change the individual fields (e.g. numbers on dates/times, the X icon, etc.).

Please do **not** change the line structure or the format of the file.

Back to [Table Of Contents](#toc)


<a name="faq"></a>
### Frequently Asked Questions (FAQ)

1. I lost my task list because I forcefully stopped the program. What can I do to prevent this?
   - Try to end the **Duke** program by entering the `bye` command in order to save the tasks array.
   - To prevent such errors, enter the `save` command if possible (refer to Section **viii.**) to save your files before your program gets interrupted.

1. Where can I find my _tasks.txt_ file?
   - Usually, the _tasks.txt_ file will be saved in the same folder where your **Duke** program is located.
   - You may search on your computer locally for the file.
   - Enter the `print filepath` command in the Duke program to view the file path (refer to Section **xiii.**).

1. How do I edit the file path for _tasks.txt_ file?
   - The current version of **Duke** is not able to change the _tasks.txt_ file path. This feature may be implemented in future versions.

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
| Command      | Function |
| :----:      | :----      |
| [help](#help)      | Prints the command formats that Duke will respond to.  |
| [todo \<task name\>](#todo)   | Adds a To-Do to your list |
| [deadline \<task name\> /by \<YYYY-MM-DD\> \<HH:MM\>](#deadline)   | Adds a Deadline to your list  |
| [event \<task name\> /at \<YYYY-MM-DD\> \<HH:MM\>](#event)   | Adds an Event to your list |
| [done \<task number\>](#done)   | Marks a task at task number in list as '_done_'|
| [delete \<task number\>](#delete)   | Removes a task at task number from list|
| [save](#save)   | Saves the current list in the _tasks.txt_ file.|
| [list](#list)   | Prints all the current tasks along with dates and times|
| [find \<common keyword in tasks\>](#find)   | Finds and filters out the tasks with a desired keyword|
| [print type \<task type\>](#printtype)   | Prints out all tasks of a certain kind (e.g. deadlines, etc.) |
| [print date \<task date YYYY-MM-DD\>](#printdate)   | Prints out all tasks with a certain date |
| [print filepath](#printfilepath)   | Prints path of the _tasks.txt_ file.|
| [bye](#bye)   | Exits the program.  |

Back to [Table Of Contents](#toc)
