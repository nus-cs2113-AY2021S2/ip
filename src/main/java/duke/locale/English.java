package duke.locale;

public class English {
    // %s: A sample date
    public static final String GREETING =
            "Hello! I'm Duke.\n"
            + "What can I do for you?\n"
            + "Note: When input a date (& time), please use format like '%s'.";

    // No args
    public static final String FAREWELL =
            "Bye. Hope to see you again soon!";

    // %s: Exception String
    public static final String EXCEPTION =
            "[We have a problem!] %s";

    // %s: Filepath
    public static final String SAVE_EXCEPTION =
            "Got a problem when loading save file at '%s'.\n"
            + "An empty list will be used instead!";

    // %s: Additional text
    public static final String TASK_LIST =
            "Here are the tasks in your list%s:";

    // %s: Additional text
    public static final String TASK_LIST_EMPTY =
            "You don't have a task in your list%s!";
    
    // %s: at/by DateTime object
    public static final String TASK_LIST_AT_BY =
            " at/by %s";
    
    // %s: Latest added task
    // %d: Task counts
    public static final String NEW_TASK =
            "Great. We added a new task:\n"
            + "%s\n"
            + "You have in total %d tasks";
}
