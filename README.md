# Lyon user guide

This is a user guide for the Lyon chatbot Java project (originally named Duke). Given below are instructions on how to use it.

### Prerequisites: JDK 11

### Special note: The special character | has been reserved by Lyon to store information into the .txt file, so any inputs containing this character will be rejected by Lyon.


## Setting up
1. Copy the jar file (CS2113.jar) into an empty folder, and open a command window in that folder.
2. Run this command in the command window:
	java -jar CS2113.jar
3. The console appliation will begin to run.
4. You may now use any of the commands below, following the given format.


## Commands

### 1. list
-Simply enter "list" into the command window.  
-A list of all of the user's tasks will be shown.

### 2. todo {input}
-{input} can be anything that the user types.
-Creates a new "todo" item and adds it to the list of user's tasks.  
-The task will be reflected as {input}

### 3. deadline {input} /by {date_input}
-{input} can be anything that the user types.  
-{date_input} has to be a real date, given in the format yyyy-mm-dd  
-Creates a new "deadline" item and adds it to the list of user's tasks.  
-The task will be reflected as {input} (by: {date_input})  

### 4. event {input} /at {date_input}
-{input} can be anything that the user types.  
-{date_input} has to be a real date, given in the format yyyy-mm-dd  
-Creates a new "event" item and adds it to the list of user's tasks.  
-The task will be reflected as {input} (at: {date_input})  

### 5. done {input}
-{input} is to be the index number of a task in the user's task list.  
-The task will be reflected as done, with a [X] replacing [ ] to show completion.  

### 6. delete {input}
-{input} is to be the index number of a task in the list of user's tasks.  
-The task will be removed from the list of user's tasks.  

### 7. find {input}
-{input} can be anything that the user types.
-A list of tasks, containing {input} will be returned.

### 8. bye
-Terminates the program.