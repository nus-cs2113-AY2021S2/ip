# Duke project create by Liu Ruiqian

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
#### How to use it

It has the function of save and read users activities including event activity, deadline activity and todo activity.
You can start by typing:
 1. **list** to view **The list of activities**
 2. **event xxx** to add **A new event**
 3. **deadline xxx /by** to add **A new deadline**
 4. **todo xxx /at** to add **A new todo**
 5. **delete xxx** to delete **An activity**
 6. **done xxx** to mark **One activity as done**
 7. **bye** to end **The system**

#### Possible user journey
```
===================================================
Duke - Version 1.0
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke
What can I do for you?

===================================================
Enter command: list
[Command entered:list]
===================================================
 1: [deadline][done] return book (by: Sunday)
 2: [event][done] project meeting (at: Mon 2-4pm)
 3: [todo][done] run book (by: Sunday)
Enter command: event finish breakfast /at Mon
[Command entered:event finish breakfast /at Mon]
===================================================
Enter command: list
[Command entered:list]
===================================================
 1: [deadline][done] return book (by: Sunday)
 2: [event][done] project meeting (at: Mon 2-4pm)
 3: [todo][done] run book (by: Sunday)
 4: [event][undo] finish breakfast (at: Mon)
Enter command: done 4
[Command entered:done 4]
===================================================
Enter command: list
[Command entered:list]
===================================================
 1: [deadline][done] return book (by: Sunday)
 2: [event][done] project meeting (at: Mon 2-4pm)
 3: [todo][done] run book (by: Sunday)
 4: [event][done] finish breakfast (at: Mon)
Enter command: delete 3
[Command entered:delete 3]
===================================================
Enter command: find book
[Command entered:find book]
===================================================
[deadline][done] return book (by: Sunday)
Enter command: bye
[Command entered:bye]
===================================================
Bye. Hope to see you again soon!
```

If you need more instruction, feel free to contact Ruiqian @ 2595903341@qq.com
Thanks

