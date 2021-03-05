# Duke User Guide

Duke is a desktop app for managing tasks using a Command Line Interface (CLI). You can keep track of your tasks to be done and their respective dates. 

* [Quick start](#quick-start)
* [Features](#features)
    * [Adding tasks: `todo/event/deadline`](#add)
    * [Listing tasks: `list`](#list)
    * [Completing tasks: `done`](#done)
    * [Finding tasks: `find`](#find)
    * [Deleting tasks: `delete`](#delete)
    * [Saving tasks: `save`](#save)
    * [Exiting: `exit`](#exit)
* [Command summary](#command-summary)

---

## Quick start
1. Ensure you have Java 11 or above installed in your Computer. 
2. Download the latest duke.jar from [here](https://github.com/huachen24/ip/releases/tag/v0.2)
3. Copy the file to the folder you want to use as the _home folder_ for Duke
4. Open a new terminal and navigate to the folder you just copied into. 
5. In your terminal, type `java -jar Duke.jar` and press enter. This should start Duke. 
6. Type in commands and press Enter to execute it. 
    Refer to the [Command summary](#command-summary) for a list of recognised commands. 
7. You should notice a new _data folder_ created in the _home folder_ you have chosen in step 3. The data for duke is saved in `duke.txt` in the _data folder_. 

---

## Features 

### <a name="add"></a>Adding tasks: `todo/event/deadline`
Duke can add 3 types of tasks: [Todos](#todo), [Events](#event) and [Deadlines](#deadline). 

#### Todo
Format: `todo <DESCRIPTION>`
* `<DESCRIPTION>`: description of the todo

#### Event
Format: `event <DESCRIPTION> /at <DATE>`
* `<DESCRIPTION>`: description of the event
* `<DATE>`: date of the event in dd/mm/yy

#### Deadline
Format: `deadline <DESCRIPTION /by <DATE>`
* `<DESCRIPTION>`: description of the deadline task
* `<DATE>`: due date of the deadline task in dd/mm/yy


### <a name="list"></a>Listing tasks: `list`
Duke will list all the current tasks and their details. 

Format: `list`


### <a name="done"></a>Completing tasks: `done`
Duke can mark any task as complete. This will continue displaying the task in the list function. Use `delete` to remove the task. 

Format: `done <INDEX>`
* `<INDEX>`: index of the task to be marked as complete. 
* !!! Use `list` to find out the index of the task. 


### <a name="find"></a>Finding tasks: `find`
Duke can search for any task based on the task description. Find returns a list of the tasks that contain the search term in its description. 

Format: `find <SEARCHTERM>`
* `<SEARCHTERM>`: search term to be used, it is case-sensitive. 


### <a name="delete"></a>Deleting tasks: `delete`
Duke can delete any task, even if it's not completed yet. 

Format: `delete <INDEX>`
* `<INDEX>`: index of the task to be deleted. 
* !!! Delete removes the task and refreshes task indices, use `list` after deleting a task to see the new task indices. 

### <a name="save"></a>Saving tasks: `save`
Duke can save your tasks onto your computer so that it remains there even if you restart Duke. This is automatically executed when you exit duke. 

Format: `save`


### <a name="exit"></a>Exiting: `exit`
You can exit Duke with the exit command. 

Format: `exit`

---

## Command summary

<table>
    <thead>
        <tr>
            <th>Command</th>
            <th>Format, Examples</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=2>todo</td>
            <td> <code> todo <DESCRIPTION> </code> </td>
        </tr>
        <tr>
            <td> <code> todo CS2113T exercises </code> </td>
        </tr>
        <tr>
            <td rowspan=2>event</td>
            <td> <code> event <DESCRIPTION> /at <DATE(dd/mm/yy)> </code> </td>
        </tr>
        <tr>
            <td> <code> event CS2113T lecture /at 05/03/21 </code> </td>
        </tr>
        <tr>
            <td rowspan=2>deadline</td>
            <td> <code> deadline <DESCRIPTION> /by <DATE(dd/mm/yy)> </code> </td>
        </tr>
        <tr>
            <td> <code> deadline submit duke /by 05/03/21 </code> </td>
        </tr>
        <tr>
            <td>list</td>
            <td> <code> list </code> </td>
        </tr>
        <tr>
            <td>done</td>
            <td> <code> done </code> </td>
        </tr>
        <tr>
            <td rowspan=2>find</td>
            <td> <code> find <SEARCHTERM> </code> </td>
        </tr>
        <tr>
            <td> <code> find CS2113T </code> </td>
        </tr>
        <tr>
            <td rowspan=2>delete</td>
            <td> <code> delete <INDEX> </code> </td>
        </tr>
        <tr>
            <td> <code> delete 1 </code> </td>
        </tr>
        <tr>
            <td>save</td>
            <td> <code> save </code> </td>
        </tr>
        <tr>
            <td>exit</td>
            <td> <code> exit </code> </td>
        </tr>
    </tbody>
</table>
