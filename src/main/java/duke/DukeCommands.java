package duke;

public interface DukeCommands {
    /* List of all commands that Duke accepts */
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String HELP_COMMAND = "help";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String CLEAR_COMMAND = "clear";

    /* List of command delimiters, e.g. "/by" for "deadline" */
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";

    /* List of help messages for each command*/
    public static final String BYE_HELP_MESSAGE = "bye - Exits program";
    public static final String HELP_HELP_MESSAGE = "help - Prints this help message";
    public static final String LIST_HELP_MESSAGE = "list - Lists all tasks";
    public static final String DONE_HELP_MESSAGE = "done <TASK_NUMBER> - Mark the specified task as done";
    public static final String TODO_HELP_MESSAGE =
            "todo <TASK_DESCRIPTION> - Create a new task with the specified description";
    public static final String DEADLINE_HELP_MESSAGE =
            "deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE> - Create a new task with the specified description and deadline";
    public static final String EVENT_HELP_MESSAGE =
            "event <TASK_DESCRIPTION> /at <EVENT_DATE> - Create a new task with the specified description and event date";
    public static final String DELETE_HELP_MESSAGE = "delete <TASK_NUMBER> - delete the specified task";
    public static final String CLEAR_HELP_MESSAGE = "clear - deletes all tasks";
}
