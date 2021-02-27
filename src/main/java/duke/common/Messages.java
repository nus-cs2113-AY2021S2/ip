package duke.common;

public class Messages {
    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\n"
            + "What can I do for you?";

    public static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:";
    public static final String MESSAGE_LIST_TASK_NONE = "No task in record.";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_REMOVED = "Noted. I've removed this task:";
    public static final String MESSAGE_TASK_MARK_AS_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASK_MATCH_FOUND = "Here are the task(s) in the list that match the keyword:";
    public static final String MESSAGE_NO_MATCH_FOUND = "There are no tasks that match your keyword.";

    public static final String MESSAGE_ERROR_PREFIX = "ERROR: ";
    public static final String MESSAGE_ERROR_EMPTY_DEADLINE_BY = "The deadline's /by argument cannot be empty.";
    public static final String MESSAGE_ERROR_EMPTY_EVENT_AT = "The event's /at argument cannot be empty.";
    public static final String MESSAGE_ERROR_WRITE_TO_FILE = "Unable to write to file. :<(";
    public static final String MESSAGE_ERROR_MISSING_DATE = "Missing date, please specify a valid date.";
    public static final String MESSAGE_ERROR_INVALID_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_MISSING_KEYWORD = "Please enter a keyword to search.";
    public static final String MESSAGE_ERROR_INVALID_DATE = "Invalid date input. Please enter a valid date.";
    public static final String MESSAGE_ERROR_EMPTY_TASK_NUMBER = "Missing task number,"
            + "please specify a valid task number.";
    public static final String MESSAGE_ERROR_INVALID_TASK_NUMBER = "The task number you've entered is invalid.";
    public static final String MESSAGE_ERROR_NOT_A_TASK_NUMBER =
            "Please enter a valid positive integer for a task number.";

    // SF stands for StringFormat
    public static final String SF_DOUBLE_SPACE_PREFIX = "  %s";
    public static final String SF_TASK_TOTAL_TASKS = "Now you have %d tasks in the list.";
    public static final String SF_FOUND_DATE_TASK = "Found %d Deadline(s) and %d Event(s) on the %s.";
    public static final String SF_ERROR_EMPTY_DESCRIPTION = "The description of a %s cannot be empty.";

    public static final String HELP_USAGE_MESSAGE = "List of commands and their description:\n\n"
            + "help      - displays the list of commands and their description.\n"
            + "list      - lists all the tasks (Todos, Deadlines & Events).\n"
            + "todo <description>      - records the task name.\n"
            + "deadline <description> /by <datetime>  - records the task name and\n"
            + "                                         datetime of deadline.\n"
            + "event <description> /at <datetime>     - records the task (event)\n"
            + "                                         name and datetime of event.\n"
            + "done <task number>      - marks a task as done based on its task\n"
            + "                          number in the list.\n"
            + "delete <task number>    - deletes a task record based on its task\n"
            + "                          number in the list.\n"
            + "find <keyword>          - find tasks with a specified keyword.\n"
            + "date <date>             - search for deadlines or events that have a\n"
            + "                          date in the format of either:\n"
            + "                          -  DDMMYYYY or DD/MM/YYYY or YYYY.MM.DD or\n"
            + "                             DD-MM-YYYY.\n"
            + "bye      - exits the application.\n";
}
