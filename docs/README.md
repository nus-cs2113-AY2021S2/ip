# User Guide

## Features 
<<<<<<< HEAD
| Syntax      | Description |
| ----------- | ----------- |
| list      | View list       |
| event   | Add event        |
| todo      | Add todo       |
| deadline   | Add deadline  |
| done      | Done Item      |
| bye   | End system        |
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
=======

&nbsp;

| Command | Format | Example |
|:-------:|:----------:|:-------------------:|
| List|`help`|`help`|
| Mark As Done|`list`|`list`|
| Delete Task|`delete [index]`|`delete 1`|
| Done Task|`delete [index]`|`done 1`|
| Find With Keyword|`keyword [keyword]`|`keyword football`|
| Add Deadline Task|`deadline [task]/by [time]`|`deadline book update/by 2021-01-03`|
| Add Event Task|`event [task]/at [time]`|`event FOOD delivery/at 2021-01-01`|
| Add Todo Task|`todo [task]`|`todo upgrade game`|
| Exit Program|`bye`|`bye`|

&nbsp;

### Feature 1 
**list** to view **The list of activities**
### Feature 2
**event xxx** to add **A new event**
### Feature 3
**deadline xxx /by** to add **A new deadline**
### Feature 4
**todo xxx /at** to add **A new todo**
### Feature 5
**delete xxx** to delete **An activity**
### Feature 6
**done xxx** to mark **One activity as done**
### Feature 7
**bye** to end **The system**
>>>>>>> 27d5070baf445a3d451104d77f01a27249f09c2a

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
<<<<<<< HEAD
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
=======

```
list
```

Expected outcome:

```
1: [deadline][done] return book (by: Sunday)
2: [event][done] project meeting (at: Mon 2-4pm)
3: [todo][done] run book (by: Sunday)
```

Example of usage:

```
find book
```

Expected outcome:

```
[deadline][done] return book (by: Sunday)
```

Example of usage:

```
done 4
```

Expected outcome:

```
[Command entered:done 4]
```

Example of usage:

```
delete 4
```

Expected outcome:

```
[Command entered:delete 4]
```

Example of usage:

```
event book
```

Expected outcome:

```
[Command entered:event book]
```

Example of usage:

```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
```
>>>>>>> 27d5070baf445a3d451104d77f01a27249f09c2a
