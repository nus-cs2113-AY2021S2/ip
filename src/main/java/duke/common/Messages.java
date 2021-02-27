package duke.common;

/**
 * Container for user visible messages.
 */
public class Messages {

    /**
     * Informational
     */
    public static final String INFO_GREETING = " Hello! I'm Duke" + System.lineSeparator()
            + " What can I do for you?" + System.lineSeparator() + "Type \"help\" for usage information";
    public static final String INFO_HELP =
            "help\n - list this usage information and the commands available" + System.lineSeparator()
            + "todo <task>\n - create new todo task" + System.lineSeparator()
            + "deadline <task> /by <dd/MM/yyyy>\n - create new task with deadline" + System.lineSeparator()
            + "event <task> /at <dd/MM/yyyy hhmm>\n - create new event task" + System.lineSeparator()
            + "list\n - list all tasks" + System.lineSeparator()
            + "done <task number>\n - mark task as done via task number" + System.lineSeparator()
            + "delete <task number>\n - delete task via task number" + System.lineSeparator()
            + "find <keyword>\n - find tasks by description" + System.lineSeparator()
            + "date <dd/MM/yyyy>\n - find tasks by date" + System.lineSeparator()
            + "bye\n - exit the program";
    public static final String INFO_BYE = " Bye. Hope to see you again soon!";
    public static final String INFO_ADDED_TASK = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    public static final String INFO_DELETED_TASK = "Got it. I've deleted this task:" + System.lineSeparator() + "\t";
    public static final String INFO_LIST_TASKS = "Here are the tasks in your list:";
    public static final String INFO_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String INFO_TASK_ALREADY_MARKED = "Task already marked as done!";
    public static final String INFO_TASK_MARKED = "Nice! I've marked this task as done:" + System.lineSeparator()
            + "\t";
    public static final String INFO_TASKS_NOT_FOUND = "No tasks found with given keyword: ";
    public static final String INFO_DATES_NOT_FOUND = "No deadlines/events found on given date: ";

    /**
     * Exceptions
     */
    public static final String DUKE_EXCEPTION = "☹ OOPS!!!";
    public static final String INVALID_DEADLINE_EXCEPTION = "Please enter a valid deadline date!";
    public static final String INVALID_EVENT_EXCEPTION = "Please enter a valid event date and time!";
    public static final String INVALID_DATE_EXCEPTION =
            "Please follow the correct format and/or enter a valid date! (e.g. 25/12/2021)";
    public static final String INVALID_DATETIME_EXCEPTION =
            "Please follow the correct format and/or enter a valid date and time! (e.g. 25/12/2021 1600)";
    public static final String LOADING_EXCEPTION = "Unable to load tasks file! New list will be created.";
    public static final String UNKNOWN_COMMAND_EXCEPTION = "I'm sorry, but I don't know what that means :-(";

    /**
     * Miscellaneous
     */
    public static final String ICON_DONE = "\u2718";

}
