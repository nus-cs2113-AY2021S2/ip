# Duke Project
##### By Nikhila Ravikumar

This project is named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Table of Contents:

1. Quick Start
    1. Setting up .JAR file
1. Running **Duke**
    1. Command: `help`
    1. Command: `hello`, `hey`
    1. Command: `todo <taskname>`
    1. Command: `deadline <taskname> /by YYYY-MM-DD HH:MM`
    1. Command: `event <taskname> /at YYYY-MM-DD HH:MM`
    1. Command: `list`
    1. Command: `done <task number>`
    1. Command: `delete <task number>`
    1. Command: `find <common keyword in tasks>`
    1. Command: `print type <task type>`
    1. Command: `print date <task date YYYY-MM-DD>`

1. Saving of tasks list locally on your computer
1. Editing of tasks list locally on your computer
1. Frequently Asked Questions (FAQ)
1. Command Summary

### Quick Start
The **Duke** app is run via a file with the extension `.jar`. The following steps will help you set up **Duke** to run on your computer.
1. Set up the `.jar` file by downloading the latest version from [here](https://github.com/nivikcivik/ip/releases)
    1. Click on `ip.jar` under the latest version (`v0.1` or beyond)
    1. Once the download prompt appears, `save` the file to your desired folder on your computer. This folder will be the _home folder_ for your tasks list.

1. Ensure you have installed `Java 11` on your computer.
    1. Look for the `terminal` or `command prompt` application on your computer.
    1. Type `java --version` in the terminal on your computer to check the version of java.

### Running Duke App
1. On the `terminal` or `command prompt`, locate your _home folder_ where you have saved the `.jar` file.
    1. Use commands `cd` to change directory ( or folder) and `ls` to view the files in your current directory.

1. Once you have entered the _home folder_, type the following command: `java -jar <name of .jar file>` e.g. `java -jar ip.jar`
1. You should see the **Duke** logo along with a welcome message. **Duke** is now running!
    1. You would see this greeting message:

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
1. If the `tasks.txt` file does not already exist in the _home folder_ that you are in currently, **Duke** will tell you the file path where the `tasks.txt` file is saved. It will be saved in your _home folder_ where your `.jar` file is located.


   #### Command: `help`
   Prints the command formats that **Duke** will respond to.

#### Command: `hello`, `hey`
   Prints a message
#### Command `todo <task name>`
   Adds a To-Do to your list
   indicated by `[T]` symbol
   **e.g.** `todo be happy`
#### Command `deadline <task name> /by <YYYY-MM-DD> <HH:MM>`
   Adds a Deadline to your list
   indicated by `[D]` symbol
   Time should be entered in 24-hour clock format
   **e.g.** `deadline finish homework /by 2021-02-23 23:59`
#### Command `event <task name> /at <YYYY-MM-DD> <HH:MM>`
   Adds an Event to your list
   indicated by `[E]` symbol
   Time should be entered in 24-hour clock format
   **e.g.** `event birthday party /at 2020-02-28 23:59`
#### Command `list`
   Prints all the current tasks along with dates and times
   Prints current time and date for reference
#### Command `done <task number`
   Marks a task at task number in list as '_done_'
      indicated by `[X]` in field beside task
   **e.g.** `done 2`
#### Command `delete <task number`
   Removes a task at task number from list
      **_NOTE:_** This action changes the numbering of tasks in the list.
   **e.g.** `delete 2`
#### Command `find <common keyword in tasks>`
   Finds and filters out the tasks with a desired keyword
   **e.g.** `find book` - this would print out all tasks (to-dos/deadlines/events) that contain the word `book`
#### Command `print type <task type>`
   Prints out all tasks of a certain kind (e.g. deadlines, etc.)
   **e.g.** `print type event` - this would print out all the events _in the same order of tasks entered_
#### Command `print date <task date YYYY-MM-DD>`
   Prints out all tasks with a certain date (e.g. 2020-02-28)
   **e.g.** `print date 2020-02-28` - this would print out all the tasks (deadlines and events only) with this particular date

### Saving of tasks list locally on your computer
When the command `bye` is given to **Duke**, it will automatically save the current task list from the **Duke** program in the `tasks.txt` file (located in your _home folder_ ; file path specified at beginning for the first time)

### Editing of tasks list locally on your computer
When you open the `tasks.txt` file, you will notice that your tasks are saved and are separated by the ' ~~ ' symbol. This is so that **Duke** can read your tasks data each time the program runs.

It is **highly discouraged** to edit this file directly instead of editing them on the **Duke** program, as there may be some error when **Duke** is loaded the next time. Simply change the individual fields (e.g. numbers on dates/times, the X icon, etc.).

Please do **not** change the line structure or the format of the file.

### Frequently Asked Questions (FAQ)

1. I lost my task list because I forcefully stopped the program. What can I do to prevent this?
    - Try to end the **Duke** program by entering the `bye` command in order to save the tasks array.

1. Where can I find my tasks.txt file?
    - Usually, the tasks.txt file will be saved in the same folder where your **Duke** program is located.
    - You may search on your computer locally for the file.

1. How do I edit the file path for tasks.txt file?
    - The current version of **Duke** is not able to change the tasks.txt file path. This feature may be implemented in future versions.

1. When I search for a keyword using the `find` command, or a particular task type using the `print` command, the list is **empty**. What does this mean?
    - This simply means that  there are no results after searching through the tasks.
