package duke;

public class Constants {
    public static final String LINE = "_____________________________________________________\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String WELCOME_MESSAGE = LINE + "Hello from\n" + LOGO
            + "What can I do for you today?\n" + LINE;
    public static final String BYE_MESSAGE = LINE + "Goodbye! Hope to see you around soon!\n" + LINE;
    public static final String HELP_MESSAGE = LINE + "Add todo:\n"
            + "Command prefix: NONE\n"
            + "Argument(s): task\n\n"
            + "Show tasks:\n"
            + "Command prefix: list\n\n"
            + "Check task off:\n"
            + "Command prefix: done\n"
            + "Argument(s): task number\n";
    public static final String EMPTY_LIST_MESSAGE = "There are not tasks in the list\n";
    public static final String ADD_MESSAGE = " added to list\n";
    public static final String NO_DESCRIPTION_MESSAGE = "The description cannot be empty!\n";
    public static final String NO_DEADLINE_MESSAGE = "Please indicate a deadline after \"/by\"\n";
    public static final String NO_TIME_MESSAGE = "Please indicate the event time after \"/at\"\n";
    public static final String TASK_CHECKED_MESSAGE = "Task checked off!\n";
    public static final String TASK_ALREADY_CHECKED_MESSAGE = " is already checked off\n";
    public static final String ALL_TASKS_CHECKED_MESSAGE = "All remaining tasks have been checked off!\n";
    public static final String TASK_UNCHECKED_MESSAGE = "Task is no longer checked off\n";
    public static final String TASK_NOT_CHECKED_MESSAGE = " is not checked\n";
    public static final String TASK_DELETED_MESSAGE = "This task has been removed: \n";
    public static final String INVALID_ARGUMENT_MESSAGE = "Invalid argument!\n";
    public static final String INVALID_COMMAND_MESSAGE = "I am sorry, I do not recognise that command\n"
            + "Please try again\n";
    public static final String GENERIC_ERROR_MESSAGE = "Something went wrong!\n";

    public static final String FILENAME = "data.txt";
}
