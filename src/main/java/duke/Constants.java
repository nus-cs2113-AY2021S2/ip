package duke;

public final class Constants {
    public Constants() {

    }

    // Words for commands.
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_MARK_WORD = "done";
    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_FIND_WORD = "find";
    public static final String COMMAND_EXIT_WORD = "bye";

    // Display messages.
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke, what can I do for you?";
    public static final String MESSAGE_BORDER = "-=-=-=-=-=-".repeat(6);
    public static final String MESSAGE_ADDED = "Got it. I've added this task: ";
    public static final String MESSAGE_MARKED = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_DELETE = "Noted. I have removed this task: ";
    public static final String MESSAGE_FIND = "Here are some of the tasks in your list: ";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon! ";
    public static final String MESSAGE_NUMBER_OF_TASKS = "You have %s task(s) in the list. "; 

    public static final String ERROR_INDEX_OUT_OF_RANGE = "Index out of range. ";
    public static final String ERROR_INVALID_COMMAND_RECEIVED = "Invalid command. Available commands: "
            + System.lineSeparator() + "\t  list, done, todo, deadline, event, delete, find, bye";
    public static final String ERROR_EMPTY_LIST = "No tasks found.";
    public static final String ERROR_INVALID_SYNTAX_RECEIVED = "Invalid syntax! Usage: ";
    public static final String ERROR_IO = "IO Error, please try again. ";
    public static final String ERROR_IMPORT_TASK = "Failed to import some tasks. ";
    public static final String ERROR_FILE_NOT_FOUND = "File not found, loading new tasklist.";
    public static final String ERROR_NO_MATCH_FOUND = "No matches found. Please refine your search";

    // Syntax messages for the commands.
    public static final String MESSAGE_MARK_SYNTAX = " done <task number>";
    public static final String MESSAGE_TODO_SYNTAX = " todo <task name>";
    public static final String MESSAGE_DEADLINE_SYNTAX = " deadline <task name> /by <date>";
    public static final String MESSAGE_EVENT_SYNTAX = " event <task name> /at <date>";
    public static final String MESSAGE_DELETE_SYNTAX = " delete <task number>";
    public static final String MESSAGE_FIND_SYNTAX = " find <keyword>";

    // File path location
    public static final String FILE_PATH = "Duke.txt";

    // Index for command parameters
    public static final int COMMAND_INDEX = 0;
    public static final int MARK_INDEX = 1;
    public static final int TASK_DESCRIPTION_INDEX = 2;
    
    // Index specific to deadline and event tasks 
    public static final int TASK_DATE_INDEX = 3;

    // Filter words to locate date for deadline and event tasks.
    public static final String DEADLINE_DATA_PREFIX_BY = "/by";
    public static final String EVENT_DATA_PREFIX_AT = "/at";
}
