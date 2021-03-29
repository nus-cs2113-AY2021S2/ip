Duke User Guide

Introduction
Duke is a Personal Assistant Chatbot that helps you to keep track of various things.

Setting up Duke
Download Duke file.
Go to /ip/src/main/java

In command line key in:
javac duke/Duke.java
java duke.Duke   

Functions
---------------------------------------
List
Display all tasks in the list
Format: list
---------------------------------------

Example:
list

Todo
Add a todo task
Format: todo [DESCRIPTION]
---------------------------------------

Example:
todo read book


Deadline
Add a deadline task
Format: deadline [DESCRIPTION] /by [yyyy-mm-dd]
---------------------------------------

Example:
deadline return book /by 2020-10-01


Event
Add an event task
Format: event [DESCRIPTION] /at [yyyy-mm-dd]
---------------------------------------

Example:
event school celebration /at 2020-10-01


Done
Mark a task as done
Format: done [index]
---------------------------------------

Example:
done 2


Delete
Delete a task from duke
Format: delete [index]
---------------------------------------

Example:
delete 2


Find
Find tasks that contains a certain keyword
Format: find [keyword]
---------------------------------------

Example:
find book


Bye
Exits Duke Format: bye
---------------------------------------

Example:
bye
