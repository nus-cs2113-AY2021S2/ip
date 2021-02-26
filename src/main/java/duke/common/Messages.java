package duke.common;

public class Messages {
    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";

    public static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String LIST_NO_TASK_MESSAGE = "No task in record.";
    public static final String DOUBLE_SPACE_PREFIX_STRING_FORMAT = "  %s";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task:";
    public static final String TASK_TOTAL_TASKS_STRING_FORMAT = "Now you have %d tasks in the list.";
    public static final String TASK_MARK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String TASK_MATCH_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String ERROR_PREFIX_MESSAGE = "ERROR: ";
    public static final String ERROR_EMPTY_DEADLINE_BY_MESSAGE = "The deadline's /by argument cannot be empty.";
    public static final String ERROR_EMPTY_EVENT_AT_MESSAGE = "The event's /at argument cannot be empty.";
    public static final String ERROR_WRITE_TO_FILE_MESSAGE = "Unable to write to file. :<(";
    public static final String ERROR_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_MISSING_KEYWORD_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_TASK_NO_MATCH_MESSAGE = "There are no tasks that match your keyword.";

    public static final String ERROR_EMPTY_TASK_NUMBER_MESSAGE = "Missing task number,"
            + "please specify a valid task number.";
    public static final String ERROR_INVALID_TASK_NUMBER_MESSAGE = "The task number you've entered is invalid.";
    public static final String ERROR_NOT_A_TASK_NUMBER_MESSAGE =
            "Please enter a valid positive integer for a task number.";
    public static final String ERROR_EMPTY_TASK_STRING_FORMAT = "The description of a %s cannot be empty.";
}
