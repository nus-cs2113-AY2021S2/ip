package duke.common;

public class Constants {

    // Constants used for displaying messages
    public static final String NEWLINE = System.lineSeparator();
    public static final String BORDER = "____________________________________________________________"
            + NEWLINE + NEWLINE;

    // Constants used to set status of a task
    public static final String DEFAULT_STATUS = " ";
    public static final String DONE_STATUS = "X";
    public static final int DEFAULT_STATUS_AS_INT = 0;
    public static final int DONE_STATUS_AS_INT = 1;

    // Constants used to set type of a task
    public static final String TODO_TASK_TYPE = "T";
    public static final String DEADLINE_TASK_TYPE = "D";
    public static final String EVENT_TASK_TYPE = "E";
}
