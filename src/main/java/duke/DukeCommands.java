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

    /* List of command delimiters, e.g. "/by" for "deadline" */
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";
}
