# Arthur User Guide
&nbsp;

Arthur is an interactive task-manager. He can be *sceptical* from time to time. Nevertheless, if you manage to earn his trust, he will be *amiable* towards you.

&nbsp;

## Table of Contents
* [Welcome](#welcome)
* [About](#about)
    * [How to use guide](#how-to-use-guide)
    * [Tasks classification](#tasks-classification)
    * [Storage](#storage)
* [Getting Started](#getting-started)
* [Features](#features)
    * [Need help : `help`](#need-help--help)
    * [List all tasks : `list`](#list-all-tasks--list)
    * [Add a todo task : `todo`](#add-a-todo-task--todo)
    * [Add an event : `event`](#add-an-event--event)
    * [Add a task with a deadline : `deadline`](#add-a-task-with-a-deadline--deadline)
    * [Delete a task : `delete`](#delete-a-task--delete)
    * [Mark a task as done : `done`](#mark-a-task-as-done--done)
    * [Find a task based on its description : `find`](#find-a-task-based-on-its-description--find)
    * [Search a task based on its date : `search`](#search-a-task-based-on-its-date--search)
    * [Exit : `bye`](#exit--bye)
* [Cheat Sheet](#cheat-sheet)
* [Troubleshoot](#troubleshoot)
* [Contributor(s)](#contributors)

&nbsp;

## Welcome
&nbsp;
```
Ssshhhhhh!!!!!
                             * ** ** ** ** ** ** ** *
                         ** ** ** ** ** ** ** ** ** ** *
                        ** ** ** ** ** ** ** ** ** ** ** ** *
                      * ** ** ** * ** ** ** ** ** ** ** ** **
                    ** ** **                     * ** ** ** **
                   ** ** *                         * ** ** ** *
                  ** **                                * ** ** **
                 * ** *                                   ** ** **
                ** **                                      * ** ** *
               ** **                           * ** ** ** * * ** ** *
              * ** **                    * ** ** ** ** ** ** ** ** ** **
              * ** *                 ** ** ** ** ** ** ** ** ** ** ** **
              * ** *               * ** ** ** ** ** ** ** ** ** ** ** ** *
              * ** *               ** ** ** ** **  *  *  *  *  ** * ** ** **
              * ** *              ** ** ** *                       * ** ** *
              * **              * ** ** *                           * ** **
              * **              * ** ** **                          ** ** *
            ** **              * ** ** **                         * ** *
            *  **              ** ** ** **                      ** ** *
           **  **               * ** ** ** *                * * ** **
        ** **  **                * ** ** ** ** ** ** ** ** ** ** ** *
     ** ** **  **                  * ** ** ** ** ** ** ** ** *
  ** ** ** **  **                                        ** **
  ** **    **  **                                        ** **
  ** *     **  **                                      * ** **
  ** *     **  **                                     * ** **
  ** *     **  **                                     ** ** *
  ** *     **  **                                     ** ** *
  ** *     **  **                                     ** ** *
  ** *     **  **                                     ** ** *
  ** *     **  **                                   * ** ** *
  ** *     **  **                                   * ** ** *
  ** *     **  **                                   * ** ** *
  ** *     **  **                                   * ** ** *
  ** *    **  **                                    * ** ** *
  ** ** ** **  **                                   * ** ** *
  ** ** ** **  **                                   * ** ** *
      * ** **  **                                   * ** ** *
        ** **  **                                   * ** ** *
         ** **  *              ** ** ** ** ** *        * **
            **  **             ** ** ** ** ** *        * **
             **  **             ** **     * ** *       * **
              *  **             ** **     ** **        * **
               **             ** **       ** **        * **
                **            ** **        * **        * **
                *            ** **          **        * **
                 **         ** *            **        **
                  * ** ** ** *               ** ** ** **
                   ** ** ***                  * ** **

____________________________________________________________________________________
Hello Crewmate! I'm Arthur, ( = ^ _ ^ = )
Please assign me my tasks to complete!
```

&nbsp;
## About
&nbsp;

### How to use guide
&nbsp;
1. You will find general information about the application in the [About](#about) section.
2. If you need help setting up Arthur, you can always look into the [Getting Started](#getting-started) section.
3. You can learn more about the features, Arthur can offer, by viewing the [Features](#features) section.
4. If you want to quickly glance a look at the features, you can resort to the [Cheat Sheet](#cheat-sheet).
5. If you are experiencing any issue with Arthur, you can view the [Troubleshoot](#troubleshoot) section or get in touch with the [Contributor(s)](#contributors).

&nbsp;
### Tasks classification

&nbsp;

A task can be classified into three different categories:
1. To do task:
    * Can be found based on its description.
    * Can be marked as done.
    * Can be deleted.
    * Can be printed from the list.
    * Does NOT have a due date attribute.
    * CANNOT be searched based on date.
2. Deadline
    * Can be found based on its description.
    * Can be marked as done.
    * Can be deleted.
    * Can be printed from the list.
    * Has a due date attribute.
    * Can be searched based on date.
3. Event
* Can be found based on its description.
    * Can be marked as done.
    * Can be deleted.
    * Can be printed from the list.
    * Has a due date attribute.
    * Can be searched based on date.

&nbsp;
### Storage

&nbsp;

All changes will be automatically saved to a file `arthur.txt`.
This file will be located in the same directory as `Arthur.jar`.
It is advisable that you do not make any changes to the saved file as it can be corrupted.

&nbsp;
## Getting Started

&nbsp;

Download `Arthur.jar` from [here](https://github.com/H-horizon/ip/releases/download/v0.2/Arthur.jar):

**Requirement:**
* Java 11

**Usage:**
1. Place `Arthur.jar` in a directory.
2. Within the directory, open a terminal.
3. Use the command `java -jar Arthur.jar` to run Arthur.

&nbsp;

## Features
&nbsp;
### Need help : `help`
&nbsp;

This operation will print a cheat sheet for the user.

Format: `help`

Command: `help`

Expected Output:
```
               help                 :prints list of all commands
               list                 :prints all lists on list
               bye                  :shuts down Arthur
            todo <task>             :add a todo task to list
  event <task> // yyyy/MM/dd HHmm   :add an event to the list
 deadline <task> // yyyy/MM/dd HHmm :add a task with a deadline to the list
            done  <index>           :mark task <index> as done on the list
            delete  <index>         :delete task <index> on the list
            find  <task>            :find task(s) based on their description
         search yyyy/MM/dd          :search for task(s) based on their date


```
### List all tasks : `list`
&nbsp;

This operation will print all the tasks on the list.

Format: `list`

Command: `list`

Expected Output:
```
ATTENTION, Here's your list of tasks Crewmate!!!
1. [E][OK] plan party (by: No Event time!! Noice!!! :))
2. [E][OK] gf's birthday party (by: 02-22-2021 18:00)
3. [D][OK] school's project (by: 02-22-2021 23:59)
4. [D][OK] homework for CS2101 (by: No Deadline!! Hehe! :))
5. [D][OK] user guide (by: 02-22-2021 23:59)
6. [T][OK] user guide
7. [T][XX] buy groceries
You still have 1 task(s) left on your list ! Hurry up!! ( X _ X )
```
&nbsp;

### Add a todo task : `todo`
&nbsp;

This operation will add a Todo task to the list.

Format: `todo <task>`

Command: `todo Get a haircut`

Expected Output:
```
Aight Crewmate!! I've got a new task for you to do!!! ( ^ _ ^ ): Get a haircut
```
### Add an event : `event`
&nbsp;

This operation will add an event to the list.

Format: `event <task> // yyyy/MM/dd HHmm`

Command: `event sister's wedding ceremony // 2021/02/22 1800`

Expected Output:
```
Aight Crewmate!! I've got a new event for you!!! ( ^ _ ^ ): sister's wedding ceremony
```
&nbsp;

You can also choose not to specify any time


Command: `event plan party`

Expected Output:
```
Aight Crewmate!! I've got a new event for you!!! ( ^ _ ^ ): plan party
```
&nbsp;

If you happened to input a date in the wrong format:

example: `event gf's birthday party // 23/02/22 1800`

You will be prompted with this message:
```
Wrong Date format Crewmate!!
Please re-enter the date ONLY in this format yyyy/MM/dd HHmm
PS: NO NEED to use event <task> nor deadline command <task> again!!
```
Then you have to enter the correct date only

example: `2021/02/22 1800`

expected output:
```
Aight Crewmate!! I've got a new event for you!!! ( ^ _ ^ ): gf's birthday party
```
&nbsp;

### Add a task with a deadline : `deadline`
&nbsp;

This operation will add a task with a deadline to the list.

Format: `deadline <task> // yyyy/MM/dd HHmm`

Command: `deadline school's project // 2021/02/22 2359`

Expected Output:
```
Aight Crewmate!! I've got a new deadline for you!!! ( ^ _ ^ ): school's project
```
&nbsp;

You can also choose not to specify any time


Command: `deadline homework for CS2101`

Expected Output:
```
Aight Crewmate!! I've got a new deadline for you!!! ( ^ _ ^ ): homework for CS2101
```
&nbsp;

If you happened to input a date in the wrong format:

example: `deadline user guide // 23/02/22 1800`

You will be prompted with this message:
```
Wrong Date format Crewmate!!
Please re-enter the date ONLY in this format yyyy/MM/dd HHmm
PS: NO NEED to use event <task> nor deadline command <task> again!!
```
Then you have to enter the correct date only

example: `2021/02/22 1800`

expected output:
```
Aight Crewmate!! I've got a new deadline for you!!! ( ^ _ ^ ): user guide
```
&nbsp;

### Delete a task : `delete`
&nbsp;

This operation will delete a task on the list based on its index.

Format: `delete <index> `

Command: `delete 1`

Expected Output:
```
( o . ^ ) Hol' up! I'm deleting this task:
[T][XX] Get a haircut
Are you really a Crewmate??? You haven't done any work on this list! ( X _ X ' )
```

&nbsp;

Command: `delete 2`

Expected Output:
```
( o . ^ ) Hol' up! I'm deleting this task:
[E][XX] sister's wedding ceremony (by: 02-22-2021 18:00)
You still have 4 task(s) left on your lists! Hurry up!! ( X _ X )
```

&nbsp;

### Mark a task as done : `done`
&nbsp;

This operation will mark a task on the list as done based on its index.

Format: `done <index> `

Command: `done 1`

Expected Output:
```
Nice! I've marked this task as done:
[E][OK] plan party (by: No Event time!! Noice!!! :))
____________________________________________________________________________________
You still have 3 task(s) left on your lists! Hurry up!! ( X _ X )
```

&nbsp;

If you happen to mark a task already done as done again:

Command: `done 1`

Expected Output:
```
What are you doing??? This job was already completed!! ( O . o )
You still have 3 tasks left on this list Crewmate! Hurry up!! ( X _ X )"
```

*This interaction will change based on your relationship with Arthur.*

&nbsp;

### Find a task based on its description : `find`
&nbsp;

This operation will find a task on the list based on its description.

Format: `find  <task>`

Command: `find user guide`

Expected Output:
```
Here is task number: 1
[D][XX] user guide (by: 02-22-2021 23:59)
Here is task number: 2
[T][XX] user guide
Your search has been completed Crewmate! ( ^ _ ^ )
```
You will be able to see all your tasks with the description specified irrespective of their types.

&nbsp;

You can also search for part of a task's description.

Command: `find eat`

Expected Output:
```
Here is task number: 1
[T][XX] eat food
Here is task number: 2
[D][XX] eat fruit (by: No Deadline!! Hehe! :))
Your search has been completed Crewmate! ( ^ _ ^ )
```

&nbsp;
### Search a task based on its date : `search`
This operation will search for a task on the list based on its due date.

Format: `search yyyy/MM/dd`

Command: `search 2021/02/22`

Expected Output:
```
[E][OK] gf's birthday party (by: 02-22-2021 18:00)
[D][XX] school's project (by: 02-22-2021 23:59)
[D][XX] user guide (by: 02-22-2021 23:59)
Search has been completed!!
```
You will be able to see all your tasks with the date specified irrespective of their types.


If you happened to input a date in the wrong format:

example: `search 23/02/22`

You will receive this message:
```
No match found!! （/ . \）
```
&nbsp;

### Exit : `bye`

**Format:** `bye`

Exits from the application.

Command: `bye`

Expected output:

```
Thanks for your help Crewmate!!
We wouldn't have done this without your help!!
Goodbye!!!! (^ . ^)
```

*This interaction will change based on your relationship with Arthur.*



&nbsp;
## Cheat Sheet

&nbsp;

| Command | Format |
| --------- | ------------------------------------------------------- |
| help | `help` |
| list | `list` |
| todo | `todo <task>` |
| event | `event <task> // yyyy/MM/dd HHmm` |
| deadline | `deadline <task> // yyyy/MM/dd HHmm` |
| delete | `delete  <index>` |
| done | `done  <index>` |
| find | `find  <task>` |
| search | `search yyyy/MM/dd>` |
| bye | `bye` |

&nbsp;

## Troubleshoot

&nbsp;

If your saved file gets corrupted, Arthur will handle the error and close the application. You can load a backup file to Arthur's directory. Remember to rename it to `arthur.txt` before continuing. You will then need to restart the application.
&nbsp;

## Contributor(s)
&nbsp;

* [H-horizon](https://github.com/H-horizon)

&nbsp;
