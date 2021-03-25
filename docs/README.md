# User Guide
_**by Wong Li Ping**_

## Introduction
This project is named after the Java mascot Duke. Duke, is a command line 
program to keep track of your tasks in a list.

## Features
Duke allows you to add three different type of tasks in your list:<p>
- Todo - tasks you plan to do. This is denoted as `[T]` in your task list!<br>
- Deadline - tasks that contains a deadline. This is denoted as `[D]` in your task list!<br>
Event - event you wish to attend. This is denoted as `[E]` in your task list!<br>

- Duke saves your list of tasks every time you input your command `[Refer to Commands Section to learn more!]` and
saves your progress even after you exit the program!
  
Duke also allows you to delete tasks from your list. Below is the before and after of the list of tasks with the use of command `delete 1`.

#### Initial List  

````
Here are the tasks in your list:  
1.[T][X] read book  
2.[D][ ] return book (by: 29/2/2021)
3.[T][ ] math homework
````

Input:
`delete 1`

#### Final List

````
Here are the tasks in your list:
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] math homework
````

Duke allows you to list all of your tasks with the command `list`. Below is an illustration of the use of command `list`.

Input: `list`

````
Here are the tasks in your list: 
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] maths homework
3.[T][ ] english assignment
4.[T][ ] english practice paper
````

Duke allows you to mark tasks as done! Below shows the before and after list of tasks with the use of command `done 1`.

````
Here are the tasks in your list:  
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] math homework
````

Input:
`done 1`

#### Final List

````
Here are the tasks in your list:
1.[D][X] return book (by: 29/2/2021)
2.[T][ ] math homework
````

Duke can help you find your tasks in the list too! Given a word, Duke can help to filter out tasks that contain the keyword you specified!  

Below shows the initial list of tasks and the filtered list of tasks that contains the keyword.

#### Initial List

````
Here are the tasks in your list: 
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] maths homework
3.[T][ ] english assignment
4.[T][ ] english practice paper
````

Input: `find english`

#### Filtered List
````
Here are the matching tasks in your list: 
1. [T][ ] english assignment
2. [T][ ] english practice paper
````
Duke can handle simple error handling too! 
## Command Summary  
#### Below is a table that illustrates the list of commands available.
Command | Functionality
--------|---------------
todo \<task\> | Adds a ToDo task object to the list
deadline \<task\> /by \<date or time\> | Adds a Deadline task object to the list
event \<task\> /at \<venue or date\> | Adds a Deadline task object to the list
list | Prints the tasks in a list with indexing
delete \<task index\> | Removes the task at specified task index 
done \<task index\> | Mark task at specified task index with a `X`
find \<keyword\> | Find and print tasks that contains the keyword
bye | Exits and terminates Duke. Progress is saved.


## Usage

### `todo` - Adds a ToDo task object to the list

Example of usage: 

`todo cs2113t homework`

Expected outcome:

`Got it. I've added this task:`<br>
`[T][ ] cs2113t homework`<br>
`Now you have 1 tasks in the list.`

### `deadline` - Adds a Deadline task object to the list

Example of usage:

`deadline cs2113t iP /by 5/3/2021 2359`

Expected outcome:

````
[D][ ] cs2113t iP (by: 5/3/2021 2359)
Now you have 2 tasks in the list.
Got it. I've added this task:
````

###### Note: event command usage is similar.


### `list` - Prints the tasks in a list with indexing

Example of usage:

`list`

Expected outcome:

````
Here are the tasks in your list:
1.[T][ ] cs2113t homework
2.[D][ ] cs2113t iP (by: 5/3/2021 2359)
3.[T][ ] read book
````


### `delete` - Delete and print the remaining tasks in the list

#### Initial List
````
Here are the tasks in your list: 
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] maths homework
3.[T][ ] english assignment
4.[T][ ] english practice paper
5.[T][ ] science reading
````

Example of usage:

`delete 5`

Expected outcome:

````
[T][ ] science reading
I've removed the above task for you!
Now you have 4 tasks in the list.
````

### `done` - Marks a task as done

#### Initial List
````
Here are the tasks in your list: 
1.[D][ ] return book (by: 29/2/2021)
2.[T][ ] maths homework
3.[T][ ] english assignment
4.[T][ ] english practice paper
````

Example of usage:

`done 3`

Expected outcome:

````
Nice! I've marked this task as done: 
[T][X] english assignment
````


### `find` - Find and print tasks that contains the keyword

Example of usage:

`find cs2113t`

Expected outcome:

````
Here are the matching tasks in your list:
1.[T][ ] cs2113t homework
2.[D][ ] cs2113t iP (by: 5/3/2021 2359)
````




###### Duke is still not capable of doing many functions sadly...
###### pst... He is trying his best to learn! :)

### `random` - User inputs random words

Example of usage:

`I love software programming!`

Expected outcome:

`OOPS!!! I'm sorry, but I don't know what that means :-(`


### Hope you have fun using Duke! More functionalities to come...!

