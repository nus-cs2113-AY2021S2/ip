package duke.common;

/**
 * Container for user visible messages and icons.
 */
public class Messages {
    public static final String MESSAGE_GREETING = " Hello! I'm Duke" + System.lineSeparator()
            + " What can I do for you?";
    public static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";
    public static final String MESSAGE_ERROR = "☹ OOPS!!!";
    public static final String MESSAGE_LOADING_FAILED = "Unable to load tasks file!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "Task already marked as done!";
    public static final String MESSAGE_TASK_MARKED = "Nice! I've marked this task as done:" + System.lineSeparator()
            + "\t";
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    public static final String MESSAGE_DELETED_TASK = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    public static final String MESSAGE_LIST_TASKS = "Here are the tasks in your list:";
    public static final String MESSAGE_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String ICON_DONE = "\u2718";

}
