# User Guide for Duke

Duke is a basic task management application written in Java 11. It is a desktop application, but with a
Command Line Interface (CLI) only at this current stage. Although it is optimized for those who can type
fast and used to type commands, everyone is welcomed to use this application.

You can record and retrieve three kinds of tasks on this program - ToDo Tasks, Events and Deadlines. Each
time you modify the task list, it will be saved onto your hard disk so you will be able to see your list
after you close and open again this application.

## Quick Start

1. First, make sure you have Java 11 installed on your computer. If not, you may want to download it from
   [Oracle's Website](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html).
2. Obtain a copy of the application via our [Github Release Page](https://github.com/fsgmhoward/ip/releases).
3. Copy the application to a folder. Since it will create a separate save file on the same folder, it is
   suggested to put it in an empty folder.
4. Double click the file to launch the application. If the window is not shown up, you can open a Command
   Prompt (CMD), then run `java -jar ip.jar` to execute it.
5. After the program starts and prints a welcome message, you may type your command in it. For example, you
   can type `todo some task description` followed by `Enter` to execute it:

   ```
            [We have a problem!] duke.exception.SaveException: No save file is found at the given path.
            Got a problem when loading save file at 'duke.save'.
            An empty list will be used instead!
            ------------------------------------------------------------
            Hello! I'm Duke.
            What can I do for you?
            Note: When input a date (& time), please use format like '31/01/2021 23:59'.
            ------------------------------------------------------------
    todo Buy Christmas gift for my friend
            ------------------------------------------------------------
            Great. We added a new task:
                    [T][×] Buy Christmas gift for my friend
            You have in total 1 tasks
            ------------------------------------------------------------
    ```

    For more command usages, you may refer to a detailed explanation of each one of them below.

## Quick Reference

This table provides a quick reference for all the commands. If you are already familiar with the usage of them,
you may use this table without reading a long description below. If you are new, it is suggested to read the full
document as it contains more details on usage.

|        Purpose        |              Command and Parameters               |
|-----------------------|---------------------------------------------------|
| Add a task            | `todo <TASK DESCRIPTION>`                         |
|                       | `event <EVENT DESCRIPTION> /at <DATE|TIME>`       |
|                       | `deadline <DEADLINE DESCRIPTION> /by <DATE|TIME>` |
| List all tasks        | `list <INDEX>`                                    |
| Mark a task completed | `done <INDEX>`                                    |
| Delete a task         | `delete <INDEX>`                                  |
| Find a task           | `find <DESCRIPTION SUBTEXT>`                      |
|                       | `searchdate <DATE>`                               |
| Exit                  | `bye`                                             |

## Features

This section introduces the usage and input/output format of each one of the
available commands. Here are some notions used for this section:

- Words in `<>`: These are compulsory parameters you must provide. For example, for
  `todo <TASK DESCRIPTION>`, `todo buy food` would be valid input, while `todo` would not, since
  the description part is missing.
- Words in `[]`: These are optional parameters you may provide, but is not a "MUST". Not providing
  them is also a valid syntax and the program will accept either one.
- Words not in any brackets: **Exact** words must be used for input. For example, for
  `done <INDEX>`, `done 1` is valid while `doneat 1` is not valid.

Some parameters have additional constraints, such as only integers are accepted. This
will be stated clearly below.

For sample input/output (I/O), lines with indentation and wrapped in two long lines are
outputs. The remaining lines are the input lines.

Commands are case sensitive.

### Add "ToDo" Task: `todo`

ToDo task is one of the three types of task. It is a general one containing only a task
description. Description can be anything as long as it is readable.

Format: `todo <TASK DESCRIPTION>`

Sample I/O:
```
todo buy cup noodle
        ------------------------------------------------------------
        Great. We added a new task:
                [T][×] buy cup noodle
        You have in total 2 tasks
        ------------------------------------------------------------
```

### Add "Event" Task: `event`

Event is a special kind of task, which bear a "happening date". This date is specified after
`/at` keyword. The date has a format of `31/12/2020`. If you want to record time as well, you
can specify it in this format: `31/12/2020 17:59`. When no time is specified, it will be 12pm
by default.

> In more specific terms,
> - `<DATE>` is of format `dd/MM/yyyy`
> - `<TIME>` is of format `dd/MM/yyyy HH:mm`
> - `<DATE|TIME>` means either one of them are accepted

Format: `event <EVENT DESCRIPTION> /at <DATE|TIME>`

Sample I/O:
```
event friend's wedding ceremony /at 31/05/2021
        ------------------------------------------------------------
        Great. We added a new task:
                [E][×] friend's wedding ceremony (at: 31/05/2021 12:00)
        You have in total 3 tasks
        ------------------------------------------------------------

event flight to Tokyo /at 30/04/2021 17:00
        ------------------------------------------------------------
        Great. We added a new task:
                [E][×] flight to Tokyo (at: 30/04/2021 17:00)
        You have in total 4 tasks
        ------------------------------------------------------------
```

### Add "Deadline" Task: `deadline`

Deadline is the other special kind of task, which contains a date (and time) that you need to
finish it by. The date (and time) is specified after `/by` keyword. The date (and time) format
is exactly the same as the one for `event`.

Format: `deadline <DEADLINE DESCRIPTION> /by <DATE|TIME>`

Sample I/O:
```
deadline prepare for CS2012 mid term /by 04/03/2021
        ------------------------------------------------------------
        Great. We added a new task:
                [D][×] prepare for CS2012 mid term (by: 04/03/2021 12:00)
        You have in total 6 tasks
        ------------------------------------------------------------

deadline submit CS2101's recess reflection /by 02/03/2021 23:59
        ------------------------------------------------------------
        Great. We added a new task:
                [D][×] submit CS2101's recess reflection (by: 02/03/2021 23:59)
        You have in total 5 tasks
        ------------------------------------------------------------
```

> As you may have noticed, when it prints out the task after it is added to the list, there is
> `T` for ToDo task, `E` for event, and `D` for deadline. This character describes what kind of
> task it is, which is useful for the following command, `list`.

### List All Tasks in List: `list`

This command list all tasks in the current task list. If you don't have any task on the list,
it will tell you as well. Any task you have marked done (see `done` later), will have a tick after
its type character (`T/E/D`). Those with a cross are the ones you have not completed.

Format: `list`

Sample I/O:
```
list
        ------------------------------------------------------------
        You don't have a task in your list!
        ------------------------------------------------------------

list
        ------------------------------------------------------------
        Here are the tasks in your list:
        1.      [T][√] Buy Christmas gift for my friend
        2.      [T][√] buy cup noodle
        3.      [E][×] friend's wedding ceremony (at: 31/05/2021 12:00)
        4.      [E][×] flight to Tokyo (at: 30/04/2021 17:00)
        5.      [D][√] submit CS2101's recess reflection (by: 02/03/2021 23:59)
        6.      [D][×] prepare for CS2012 mid term (by: 04/03/2021 12:00)
        ------------------------------------------------------------
```

### Mark Task as Completed: `done`

This command marks a task as done. The `<INDEX>` here refers to the index of the task which you can
see from the output of `list` command. Take note that all indices are **positive integers**, i.e.
all `>0`. Please take note that this index is a relative one, and the index for a specific task can
change when you delete a task in front of it. See the explanation for `delete` command below.

Format: `done <INDEX>`

Sample I/O:
```
done 1
        ------------------------------------------------------------
        Nice! I've marked this task as done:
                [T][√] Buy Christmas gift for my friend
        ------------------------------------------------------------

done 6
        ------------------------------------------------------------
        Nice! I've marked this task as done:
                [D][√] prepare for CS2012 mid term (by: 04/03/2021 12:00)
        ------------------------------------------------------------
```

### Delete Task: `delete`

This command deletes a task from the list. A deletion action is irreversible so think twice before you
use this command. Similar to `done`, the `<INDEX>` is a positive integer.

A deletion action can cause indices of the tasks in the list to change since these indices are relative.
For example, there are two tasks **A** and **B** and they have indices of 1 and 2 respectively. After you
delete **A**, the slot of index 1 is empty, so **B** becomes 1 in the new list. If there is anything behind
**B**, their indices will deduct 1 as well. Sample I/O also illustrates this: deleting index 5 consecutively
deletes two different tasks (which are 5 and 6 in the original list).

Format: `delete <INDEX>`

Sample I/O:
```
delete 5
        ------------------------------------------------------------
        Sure! I've removed this task:
                [D][×] submit CS2101's recess reflection (by: 02/03/2021 23:59)
        ------------------------------------------------------------

delete 5
        ------------------------------------------------------------
        Sure! I've removed this task:
                [D][√] prepare for CS2012 mid term (by: 04/03/2021 12:00)
        ------------------------------------------------------------
```

### Find Task Based on Description: `find`

This command finds tasks based on their description. It will print out a new list of all the
tasks having description containing this subtext.

> **Note: the index print out here cannot be used for commands of `delete` and `done`. Please
> use the index printout in the full list (`list`) for those two commands!**

Format: `find <DESCRIPTION SUBTEXT>`

Sample I/O:
```
find buy
        ------------------------------------------------------------
        Here are the tasks in your list related to 'buy':
        1.      [T][×] buy food
        2.      [T][×] buy drinks
        ------------------------------------------------------------

find food
        ------------------------------------------------------------
        Here are the tasks in your list related to 'food':
        1.      [T][×] buy food
        2.      [E][×] food sales at supermarket (at: 05/03/2021 12:00)
        ------------------------------------------------------------

find some unknown terms
        ------------------------------------------------------------
        You don't have a task in your list related to 'some unknown terms'!
        ------------------------------------------------------------
```

### Find Task Based on Date: `searchdate`

This command searches for tasks falling on a specific date. It can be the "at" date of an Event,
or the "by" date of a Deadline. Similar to `find`, this returns a new list. All ToDo tasks will
be ignored and not contained in the result since they do not bear a date.

> **Note: the index print out here cannot be used for commands of `delete` and `done`. Please
> use the index printout in the full list (`list`) for those two commands!**

> `<DATE>` uses the same format as that for `event` and `deadline` commands.

Format: `searchdate <DATE>`

Sample I/O:
```
searchdate 05/03/2021
        ------------------------------------------------------------
        Here are the tasks in your list at/by 05/03/2021 12:00:
        1.      [E][×] food sales at supermarket (at: 05/03/2021 12:00)
        ------------------------------------------------------------

searchdate 06/03/2021
        ------------------------------------------------------------
        You don't have a task in your list at/by 06/03/2021 12:00!
        ------------------------------------------------------------
```

### Exit Program: `bye`

This command prints out a farewell message and exits the program.

Format: `bye`

Sample I/O:
```
bye
        ------------------------------------------------------------
        Bye. Hope to see you again soon!
        ------------------------------------------------------------
```

## FAQ

**Q**: What is the save file?

**A**: By default, all data is saved in a file called `duke.save`. If you do not like this name,
you can use a custom save file by launching the program with the first argument being the file name,
like `java -jar ip.jar new_file.save`. Take note that, if you decided to do so, you have to launch
this program every time with the same parameter for your list to be properly loaded.

**Q**: How can I transfer my save file to the other folder/computer?

**A**: You can just copy the save file to a new folder/computer and leave it in the same path as the
Java executable (`ip.jar`). Launching the program after that will recognize it automatically.