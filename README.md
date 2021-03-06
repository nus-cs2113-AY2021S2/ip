# Duke project 

This is a project template for a greenfield Java project. It's named after the Java baddest mascot _Duke the Dawg_. Duke is a certified fresh task list handler that allows you to plan your activities according to your schedule. Quite sick right? Given below are input commands:

- todo _TASKNAME_ : adds a todo task to the list.
- event _TASKNAME_ /at _TASKDATETIME_* : adds a event task to the list.
- deadline _TASKNAME_ /by _TASKDATETIME_* : adds a deadline task to the list.
- list : displays list of tasks.
- done _NUMBER_ : checks task as done.
- delete _NUMBER_ : removes task from list.
- find KEYWORD: search and display tasks that contains keywords.
- date DATE**: search and display tasks that contains date.
- save : save task list in a seperate text file.
- bye : exits system.

If the task list is saved, it will be re-loaded into the console and available for further edits upon running duke.main().
Do take note:
* TASKDATETIME: must be entered in the following format dd-MM-yyyy HH:mm.
* DATE: must be entered in the following format dd-MM-yyyy.

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
   Credits:
   Had assistance from fellow course mate Jethro Phuah for following methods: save and load file.
   
