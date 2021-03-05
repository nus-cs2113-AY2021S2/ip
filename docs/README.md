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
USER GUIDE
Features
1. Ability to add
2. Ability to delete
3. Ability to update the status
4. Ability to find tasks
5. Ability to save the tasks to your Hard disk.


Commands you can give and their uses:
1. list
   The chatbot lists down all the tasks written by you.
   Ex:
   list
   Expected Outcome:

1. [T]["cross mark"] buy milk from 7-11

2. [D]["tick"] finish maths assignment (by: Jan 16 2021, 9:00pm)

3. [E]["cross mark"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm)


2. done [int number]
   This means that the respective task in the task bot will be done and will be "ticked"
   Ex:
   done 3
   Expected outcome:

Nice! I've marked this task as done: [E]["tick"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm). Now you have 3 tasks in the list

3. delete [int number]
   deletes teh respective task from the list
   Ex

delete 3

Expected outcome:

Noted. Ive remoevd this task:

[E]["tick"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm).

Now you have 2 tasks to the list

4. deadline
   add a deadline task at the time and date and adds it to the lsit of tasks.
   Ex: deadline get get groceries  /by 22/02/2021 23:59

Expected outcome:
Got it. I've added this task:

[D]["cross mark"] deadline get get groceries  /by 22/02/2021 23:59.
You have 7 tasks left to do.

5. event
   adds a event task at the time and date and adds it to the lsit of tasks.
   Ex: event go watch cinema  /at 23/02/2021 23:59

Expected outcome:
Got it. I've added this task:

[E]["crossmark"] event project meeting 23/02/2021 23:59
You have 7 tasks left to do.

6. todo
   adds a todo task to the list of objects.
   Ex: todo this assignemnt

Expected outcome:
Got it. I've added this task:

[E]["cross amrk"] todo this assignment
You have 5 tasks left to do.

7. find
   finds for the respective word from all the tasks in the list.
   Ex:
   find Attend
   Expected outcome:

[E]["cross mark"] Attend friend's wedding  (at: Jan 24 2021, 12:00pm)


