# User Guide for Duke 

Duke is a personal task planner. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Running in command line

Load the iP.jar file.

### Using Duke

1. If the setup is correct, you should see something like the output below.

   ```
	  ____________________________________________________________
	  Hello! I'm Duke. 
	  What can I do for you? 
	  ____________________________________________________________

   ```
   
1. The commands accepted by Duke are `list`, `done`, `delete`, `find`. To add a task for Duke to track, use the command words `todo`, `deadline`, and `event`. 

    1. A `todo` task will require a simple task description. Duke echos upon receiving a `todo` task successfully.
    
   ```
   todo read book
	    ____________________________________________________________
	    Got it. I've added this task: 
	    [T][✘] read book
	    Now you have 1 tasks in the list.
	    ____________________________________________________________

   ```
   
   2. A `deadline` task will require the user to enter a task description, as well as a deadline to when the task should be completed by. Use `/by` to separate the task description from the deadline. For example:
   
   ```
   deadline return book /by Tuesday 6pm
	    ____________________________________________________________
	    [D][✘] return book (by: Tuesday 6pm)
	    Now you have 2 tasks in the list.
	    ____________________________________________________________

   ```
   
   3.  A `event` task will require the user to enter a task description, as well as the timing for the event. Use `/at` to separate the task description from the event timing. For example:
   
   ```
   event family dinner /at Friday 6-7pm
	    ____________________________________________________________
	    [E][✘] family dinner (at: Friday 6-7pm)
	    Now you have 3 tasks in the list.
	    ____________________________________________________________

   ```
   
   4. Using the command word `list` will cause Duke to list out all the current tasks saved.
  
   ```
   list
	   ____________________________________________________________
	   Here are the tasks in your list:
	   01. [T][✘] read book
	   02. [D][✘] return book (by: Tuesday 6pm)
	   03. [E][✘] family dinner (at: Friday 6-7pm)
	   ____________________________________________________________
    ```
   
    5. Using the command word `done` will cause Duke to mark one of the tasks as completed. The status of the task will change from a ✘ to a ✓. When using the `done` command word, the index of the task which is to be marked as done must also be given. For example:
   
    ```
    done 2
	    ____________________________________________________________
	    Nice! I've marked this task as done: 
	    02. [✓] return book
	    ____________________________________________________________
    ```
   
    6. Using the command word `delete` will cause Duke to delete one of the tasks saved. Similar to `done`, the index of the task to be deleted must also be given. For example:
  
   ```
   delete 1
	    ____________________________________________________________
	    Noted. I have removed this task. 
	    [T][✘] read book
	    You now have 2tasks in the list.
	    ____________________________________________________________

   ```
   
   at this point, using `list` will cause Duke to reply with the following:
  
   ```
   list
	  ____________________________________________________________
	  Here are the tasks in your list:
	  01. [D][✓] return book (by: Tuesday 6pm)
	  02. [E][✘] family dinner (at: Friday 6-7pm)
	  ____________________________________________________________
   ```
   
   7. To properly terminate Duke, use the command word `bye`.
   
   ```
   bye
	  ____________________________________________________________
	  Bye. Hope to see you again soon!
	  ____________________________________________________________
   ```
   
1. If Duke has been used previously, a text file named `duke.txt` would be present in the same folder of the iP.jar file. When running Duke again, the contents of the `duke.txt` file would be read, and the tasks would be loaded automatically. If there are tasks loaded, using the command word `list` would cause the tasks to be shown. For example"

```
	____________________________________________________________
	Hello! I'm Duke. 
	What can I do for you? 
	____________________________________________________________
list
	____________________________________________________________
	 Here are the tasks in your list:
	01. [D][✓] return book (by: Tuesday 6pm)
	02. [E][✘] family dinner (at: Friday 6-7pm)
	____________________________________________________________
```
