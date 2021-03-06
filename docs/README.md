# User Guide
Duke is simple to use, so to get you started... here are command lines that you can input for Duke to execute swiftly! 

## Commands 

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

## Expected outcomes (examples):

- **list**

  ```
  Here are the tings in yo list: 
  1. [T][ ] eat dinner
  2. [E][ ] party time (at: 1 Mar 2021 10:00 PM)
  3. [D][ ] ip assignment submission time (by: 1 Mar 2021 07:00 PM)
  ```
- **todo eat dinner**

  ```
  Ayy I got you my brother. I've added this ting: 
  [T][ ] eat dinner
  Dayuum son! You have 1 mad tings in the list.
  ```
- **event party time /at 01-03-2021 22:00**

  ```
  Ayy I got you my brother. I've added this ting: 
  [E][ ] party time (at: 1 Mar 2021 10:00 PM)
  I feer! You have 2 mad tings in the list.
  ```
- **deadline ip assignment submission time /at 01-03-2021 19:00**

  ```
  Ayy I got you my brother. I've added this ting: 
  [D][ ] ip assignment submission time (by: 1 Mar 2021 07:00 PM)
  Jeeeeeeez! You have 3 mad tings in the list.
  ```
- **done 1**

  ```
  Awwww yeah! I've marked this task as done... brrrrrap brrrrrap: 
  [T][✓] eat dinner
  ```
- **delete 1**

  ```
  Awwww yeah! I've deleted this task like a beast: 
  [T][✓] eat dinner
  Now you have 2 tasks in the list.
  ```
- **find time**

  ```
  Here are the search results:
  [E][ ] party time (at: 1 Mar 2021 10:00 PM)
  [D][ ] ip assignment submission time (by: 1 Mar 2021 07:00 PM)
  ```
- **date 01-03-2021**

  ```
  Here are the search results:
  [E][ ] party time (at: 1 Mar 2021 10:00 PM)
  [D][ ] ip assignment submission time (by: 1 Mar 2021 07:00 PM)
  ```
- **save**

  ```
  Yea cuhhhh... filed saved!
  ```
- **bye**

  ```
  Ciao Ciao. See ya soon fam!

  Process finished with exit code 0
  ```
## Credits

I had assistance from fellow course mate Jethro Phuah for following methods: save and load file.
