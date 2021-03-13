# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### On IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the output below.

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
1. Type the command in the command box and press Enter to execute it.
1. Refer to the Features section below for details of the commands

###Features
#####Adding todo task: todo
add a task of type "todo" to the task list

> Format: todo [name_of_task]
>
 >Examples: todo read books --> Got it. I've added this task:
                            [T][✘]read books
#####Adding deadline task: deadline
add a task of type "deadline" to the task list

> Format: deadline [name_of_task] /by [time_to_complete]
>
  >Examples: deadline return books /by Thursday --> Got it. I've added this task:
                             [D][✘]return books (by: Thursday)
#####Adding event task: event
add a task of type "event" to the task list
>Format: event [name_of_task] /at [time_of_event]
>
  >Examples: event attend lecture /at Tonight
>--> Got it. I've added this task:
                             [E][✘]attend lecture (at: Tonight)
#####Listing all the task: list
>Format: list
>
>Examples: list --> 1. [T][✓]123
                   2. [D][✓]ddl1  (by: Thursday)
#####Mark a task as done: done
mark a task of a specific index as done
>Format: done INDEX
#####Finding a task by keywords: find
find all the tasks whose names contain the keyword
>Format: find [keyword]
>
>Examples: find 12 --> Here are the matching tasks in your list:
                           1. [T][✓]123
#####Delete a task: delete
delete a task of a specific index
>Format: delete INDEX
>
>Examples: delete 1 --> Noted. I've removed this task: 
           [T][✓]123
#####End the project: bye
>Format: bye