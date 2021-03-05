# User Guide
## Introduction
Duke is a simple task manager application that runs on a Command Line Interface
(CLI). By using Duke, you will be able to add, view and find different type of 
tasks you added previously. Once you finished a task, you will be able to 
mark it as done or delete it once it is no longer relevant.
Duke gives you a simple way to manage your daily tasks.
##Table of Contents
1. Quick Start
2. Features
   * Adding a to-do task
   * Adding an event
   * Adding a deadline
   * Listing all tasks
   * Marking a task as done
   * Finding tasks by keyword
   * Deleting a task
   * Exiting the program
3. FAQs
4. Other Information
## Quick Start
1. Ensure you have Java 11 or above installed. You can verify this with the command `java --version` in the CLI.
2. Download the latest version of `Duke.jar` from [here](https://github.com/e00426142/ip/releases). 
3. Open the CLI and navigate to the folder your `Duke.jar` is located at.
4. Type in the command `java -jar Duke.jar` to initiate the programme.
5. You will see the following example below if the programme is run correctly.
```
 ____         _        
|  _ \ _   _| |  _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
____________________________________________________________
Hello! I'm Duke
____________________________________________________________
What can I do for you?
____________________________________________________________
```
##Features

### 1. **Adding a to-do task:** `todo`
Adds the given todo type of task to the list.

**Format:** `todo <task description>`

**Example:** `todo read a book`

**Expected outcome:**
```
todo read a book
____________________________________________________________
Got it. I've added this task:
[T][] read a book
Now you have 1 tasks in the list.
____________________________________________________________
```

### 2. **Adding an event task:** `event`
adds the given event type of task to the list.

**Format:** `event <task description>/at <timing>`

**Example:** `event attend lecture/at Monday`

**Expected outcome:**
```
event attend lecture/at Monday
____________________________________________________________
Got it. I've added this task:
[E][] attend lecture (at: Monday)
Now you have 2 tasks in the list.
____________________________________________________________
```

### 3. **Adding a deadline task:** `deadline`
Adds the given deadline type of task to the list.

**Format:** `event <task description>/by <timing>`

**Example:** `deadline submit UG/by today 2359`

**Expected outcome:**
```
deadline submit UG/by today 2359
____________________________________________________________
Got it. I've added this task:
[D][] submit UG (by: today 2359)
Now you have 3 tasks in the list.
____________________________________________________________
```

### 4. **Listing all task:** `list`
Displays your tasks in a numbered list.

**Format:** `list`

**Example:** `list`

**Expected outcome:**
```
list
1. [T][] read a book
2. [E][] attend lecture (at: Monday)
3. [D][] submit UG (by: today 2359)
____________________________________________________________
```

### 5. **Marking a task as done:** `done`
Marks the task that has the given index in the list with an 'X', to indicate it is completed.

**Format:** `done <index>`

**Example:** `done 1`

**Expected outcome:**
```
done 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read a book
____________________________________________________________
```

### 6. **Finding tasks by keyword:** `find`
Displays a new, numbered list of tasks from the main list that contain the given keyword.

**Format:** `find <keywords>`

**Example:** `find read`

**Expected outcome:**
```
find read
____________________________________________________________
Here are the matching tasks in your list: 
1. [T][X] read a book
____________________________________________________________
```

### 7. **Deleting a task from list of tasks:** `delete`
Removes the task with the given index from the list.

**Format:** `delete <taskindex>`

**Example:** `delete 3`

**Expected outcome:**
```
delete 3
____________________________________________________________
Noted. I've removed this task: 
[D][] submit UG (by: today 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

### 8. **Terminating the programme:** `bye`
Stop the programme.

**Format:** `bye`

**Example:** `bye`

**Expected outcome:**
```
bye
____________________________________________________________
Bye. Hope to see you again!
____________________________________________________________

```
## FAQs
**Q: Does Duke save the tasks I added after I stop the program?**

**A:** Yes,Duke will save all changes made after each run and loads the previous task list before each run from the data folder that will be created whenever the programme starts.

**Q: Where can I view this list of tasks after the programme ends?**

**A:** You can view the`duke.txt`file inside the`data`folder that will be created in your user drive. In the format below.

```
T |1 |read a book
E |0 |attend lecture |Monday
```
## Other Information
Done by: `Qiu YiWen`

Originally: `Project Duke`from the module [CS2113T (Year 2021 Sem 2)](https://nus-cs2113-ay2021s2.github.io/website/se-book-adapted/projectDuke/index.html)


