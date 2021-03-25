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

# **Duke UserGuide**

   ```
_____________________________
Hello from
____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
What can I do for you?
_____________________________
   ```
## `Setting up Duke`  <br />

1. Open your Terminal <br />
2. Change you directory to where the Jar file is located at. <br />
3. Type in the command **java -jar ip.jar** to execute. <br />

## `Features` <br />

1.list <br />
2.done <br />
3.delete <br />
4.deadline <br />
5.event <br />
6.todo <br />
7.find <br />
8.bye <br />

`Commands you can give and their uses:`

### 1. `list` <br />
The chatbot lists down all the tasks written by you. <br />
Ex: <br />
list <br />
Expected Outcome: <br />

1. [T]["cross mark"] buy milk from 7-11 <br />

2. [D]["tick"] finish maths assignment (by: Jan 16 2021, 9:00pm) <br />

3. [E]["cross mark"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm) <br />


### 2. `done` <br />
This means that the respective task in the task bot will be done and will be "ticked" <br />
Ex: <br />
done 3 <br />

Expected outcome: <br />

Nice! I've marked this task as done: [E]["tick"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm). Now you have 3 tasks in the list <br />


### 3. `delete` <br />
deletes the respective task from the list <br />
Ex: <br /e>
delete 3 <br />

Expected outcome: <br />

Noted. I've removed this task: <br />

[E]["tick"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm). <br />

Now you have 2 tasks on the list <br />

### 3. `deadline` <br />
add a deadline task at the time and date and adds it to the lsit of tasks. <br />
Ex: <br />
deadline get groceries  /by 22/02/2021 23:59 <br />

Expected outcome:<br />
Got it. I've added this task: <br />

[D]["cross mark"] deadline get groceries by 22/02/2021 23:59. <br />
You have 7 tasks left to do. <br />

### 5. `event` <br />
adds a event task at the time and date and adds it to the lsit of tasks. <br />
Ex:  <br />
event go watch cinema  /at 23/02/2021 23:59 <br />

Expected outcome: <br />
Got it. I've added this task: <br />

[E]["crossmark"] event project meeting at 23/02/2021 23:59 <br />
You have 7 tasks left to do. <br />

### 6. `todo` <br />
adds a todo task to the list of objects.<br />
Ex: <br />
todo this assignment <br />

Expected outcome: <br />
Got it. I've added this task: <br />

[E]["cross mark"] todo this assignment <br />
You have 5 tasks left to do. <br />

### 7. `find` <br />
finds for the respective word from all the tasks in the list.<br />
Ex: <br />
find Attend <br />

Expected outcome: <br />

[E]["cross mark"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm) <br />

### 8. `bye` <br />
Entering this word will exit the program and will display the good bye message. <br />
Ex: <br />
bye <br />

Expected Outcome: <br />

Bye. Hope to see you again soon! <br />


