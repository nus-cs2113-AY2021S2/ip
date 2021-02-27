package duke;

public class Constants {
    public static final String LINE = "_____________________________________________________\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String WELCOME_MESSAGE = "Hello from\n" + LOGO
            + "What can I do for you today?\n"
            + "Type 'help' for list of commands and usage";
    public static final String BYE_MESSAGE = "Goodbye! Hope to see you around soon!";
    public static final String UNDONE_TASKS_REMAINING_MESSAGE = "There are %d undone task(s) on the list";
    public static final String EMPTY_LIST_MESSAGE = "There are no tasks in the list";
    public static final String ADD_MESSAGE = "\"%s\" added to list";
    public static final String NO_DESCRIPTION_MESSAGE = "The description cannot be empty!";
    public static final String DEADLINE_DUE_DATE_MESSAGE = "Please complete by: %s";
    public static final String NO_DEADLINE_MESSAGE = "Please indicate a deadline after \"/by\"";
    public static final String EVENT_TIME_MESSAGE = "It occurs at: %s";
    public static final String NO_TIME_MESSAGE = "Please indicate the event time after \"/at\"";
    public static final String TASK_CHECKED_MESSAGE = "Task %d checked off!\n    %s %s";
    public static final String TASK_ALREADY_CHECKED_MESSAGE = "Task %d: \"%s\" is already checked off";
    public static final String ALL_TASKS_CHECKED_MESSAGE = "All remaining tasks have been checked off!";
    public static final String TASK_UNCHECKED_MESSAGE = "Task %d is no longer checked off\n    %s %s";
    public static final String TASK_NOT_CHECKED_MESSAGE = "Task %d: \"%s\" is not checked";
    public static final String TASK_DOES_NOT_EXIST_MESSAGE = "Task %d does not exist!";
    public static final String TASK_DELETED_MESSAGE = "Task %d:%s has been removed";
    public static final String INVALID_ARGUMENT_MESSAGE = "Invalid argument!";
    public static final String INVALID_COMMAND_MESSAGE = "I am sorry, I do not recognise that command\n"
            + "Please try again";
    public static final String GENERIC_ERROR_MESSAGE = "Something went wrong!";

    public static final String TODO_INFO = "Add todo:\n"
            + "     Command prefix: todo\n"
            + "     Argument(s): description\n"
            + "     Usage: todo <description>\n"
            + "     Example: todo CS2113T lecture 5 quiz\n";
    public static final String EVENT_INFO = "Add event:\n"
            + "     Command prefix:  event\n"
            + "     Argument(s): description, date\n"
            + "     Usage: event <description> /at <date>\n"
            + "     Example: event CS2113T finals /at 29/4 1pm\n";
    public static final String DEADLINE_INFO = "Add deadline:\n"
            + "     Command prefix: deadline\n"
            + "     Argument(s): description, due date\n"
            + "     Usage: deadline <description> /by <due date>\n"
            + "     Example: deadline release v2.0 jar /by 5/3 2359\n";
    public static final String LIST_INFO = "Show list of tasks:\n"
            + "     Command: list\n"
            + "     Usage: list\n";
    public static final String DONE_INFO = "Check off completed task:\n"
            + "     Command prefix: done\n"
            + "     Argument(s): task index (as seen from list)\n"
            + "     Usage: done <task index>\n"
            + "     Example: done 3\n";
    public static final String UNDO_INFO = "Uncheck task: \n"
            + "     Command prefix: undo\n"
            + "     Argument(s): task index (as seen from list)\n"
            + "     Usage: undo <task index>\n"
            + "     Example: undo 3\n";
    public static final String DELETE_INFO = "Remove task:\n"
            + "! Note that deleting a task will cause the indices of tasks after in to change !\n"
            + "     Command prefix: delete\n"
            + "     Argument(s): task index (as seen from list)\n"
            + "     Usage: delete <task index>\n"
            + "     Example: delete 3\n";
    public static final String BYE_INFO = "Exit Duke:\n"
            + "     Command: bye\n"
            + "     Usage: bye";


    public static final String FILENAME = "data.txt";
}
