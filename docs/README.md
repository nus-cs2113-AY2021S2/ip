# User Guide

## Features 

&nbsp;

| Syntax      | Description |<br />
| ----------- | ----------- |<br />
| list      | View list       |<br />
| event   | Add event        |<br />
| todo      | Add todo       |<br />
| deadline   | Add deadline  |<br />
| done      | Done Item      |<br />
| bye   | End system        |<br />

&nbsp;

### Feature 1 
1. **list** to view **The list of activities**
### Feature 2
2. **event xxx** to add **A new event**
### Feature 3
3. **deadline xxx /by** to add **A new deadline**
### Feature 4
4. **todo xxx /at** to add **A new todo**
### Feature 5
5. **delete xxx** to delete **An activity**
### Feature 6
6. **done xxx** to mark **One activity as done**
### Feature 7
7. **bye** to end **The system**

## Usage

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

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`
list
`

Expected outcome:

`
1: [deadline][done] return book (by: Sunday)
2: [event][done] project meeting (at: Mon 2-4pm)
3: [todo][done] run book (by: Sunday)
`

Example of usage:

`
find book
`

Expected outcome:

`
[deadline][done] return book (by: Sunday)
`