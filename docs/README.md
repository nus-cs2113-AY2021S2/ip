# User Guide

## Features 

### Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Usage

### `list` - Fetches all tasks created by user.

Example of usage: 

`list`

Expected outcome:

`1.[T][ ] user guide`
`2.[D][ ] duke launch (by: MARCH 5 2021, 06.00 PM)`

### `todo` - Creates a new task without any date/time attached to it.
`todo {task description}`

Example of usage:

`todo read a book`

Expected outcome:

`Got it. I've added this task:`
`[T][ ] read a book`
`Tasks left: 1`

### `deadline` - Creates a new task that need to be done before a specific date/time.
`deadline {task description} /by {YYYY-MM-DD HH.MM}`

Example of usage:

`deadline return book /by 2021-01-10 18.00`

Expected outcome:

`Got it. I've added this task:`
`[D][ ] return book (by: JANUARY 10 2021, 06.00 PM)`
`Tasks left: 2`

### `event` - Creates a new task that start at a specific time and ends at a specific time.
`event {task description} /by {YYYY-MM-DD HH.MM-HH.MM}`

Example of usage:

`event zoom meeting /by 2021-01-10 18.00-20.00`

Expected outcome:

`Got it. I've added this task:`
`[E][ ] zoom meeting (by: JANUARY 10 2021, 06.00 PM - 08.00 PM)`
`Tasks left: 3`

### `done` - Marks a task as done by its index number.
`done {task index number}`

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`
`[T][X] read a book`
`Tasks left: 2`

### `delete` - Removes a specific task by its index number.
`delete {task index number}`

Example of usage:

`delete 3`

Expected outcome:

`Noted. I've removed this task:`
`[D][ ] return book (by: JANUARY 10 2021, 06.00 PM - 08.00 PM)`
`Tasks left: 1`

### `find` - Find a task by searching for a keyword.
`find {keyword}`

Example of usage:

`find book`

Expected outcome:

`Here are the matching tasks in your list:`
`1.[T][X] read a book`
`2.[D][ ] return book (by: JANUARY 10 2021, 06.00 PM)`
