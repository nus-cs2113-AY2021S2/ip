package Duke.Constant;

public class Message {
    public static final String logo = "     ____        _        \n" +
            "    |  _ \\ _   _| | _____ \n" +
            "    | | | | | | | |/ / _ \\\n" +
            "    | |_| | |_| |   <  __/\n" +
            "    |____/ \\__,_|_|\\_\\___|\n";

    public static final String GREET_MESSAGE = logo + "\n" + SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.LONG_SPACE +
            "Hello! I'm Duke\n" + SpaceAndLine.LONG_SPACE + "What can I do for you?\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String EXIT_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            "     Bye. Hope to see you again soon!\n" +
            SpaceAndLine.SEPARATION_LINE;

    public static final String EMPTY_LIST_MESSAGE = SpaceAndLine.SHORT_SPACE + "You do not have any task to do.";
    public static final String MARK_AS_DONE_MESSAGE = SpaceAndLine.SHORT_SPACE +
            "Nice! I've marked this task as done:";
    public static final String ONE_TASK_IN_LIST_MESSAGE = SpaceAndLine.SHORT_SPACE +
            "Here is the task in your list:";
    public static final String MORE_THAN_ONE_TASK_IN_LIST_MESSAGE = SpaceAndLine.SHORT_SPACE +
            "Here are the tasks in your list:";
    public static final String ADD_TASK_MESSAGE = SpaceAndLine.SHORT_SPACE + "Got it. I've added this task:";
    public static final String REMOVE_TASK_MESSAGE = SpaceAndLine.SHORT_SPACE + "Noted. I've removed this task:";
    public static final String SHOW_MATCHING_KEYWORD_TASKS_MESSAGE = "Here are the matching task(s) in your list:";
    public static final String SHOW_SAME_DATE_TASKS_MESSAGE =
            "Here are the task(s) occurring on the same date in your list:";

    public static final String WRONG_COMMAND_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE +
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_TODO_CONTENT_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The description of a todo cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_DEADLINE_CONTENT_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_EVENT_CONTENT_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The description of a event cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_DELETE_INDEX_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The index of the deletion cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String WRONG_DELETE_INDEX_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The index of the deletion is out of bound.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_MARK_AS_DONE_INDEX_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The index of the task to be marked as done cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String WRONG_MARK_AS_DONE_INDEX_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The index of the task to be marked as done is out of bound.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! I cannot find the file.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_KEYWORD_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The keyword of a find command cannot be empty.\n" +
            SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String MISSING_DATE_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
    SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The date of a date command cannot be empty.\n" +
    SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String WRONG_DATE_FORMAT_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! The date format is wrong.\n" + SpaceAndLine.SHORT_SPACE +
            "Example: 2020-03-05\n" + SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String NON_EXISTING_LETTER_WRONG_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! Something wrong happened!.\n" + SpaceAndLine.SHORT_SPACE +
            "Example: 2020-03-05\n" + SpaceAndLine.SEPARATION_LINE + "\n";
    public static final String LOADING_ERROR_MESSAGE = SpaceAndLine.SEPARATION_LINE + "\n" +
            SpaceAndLine.SHORT_SPACE + "☹ OOPS!!! Unable to load any file!.\n" + SpaceAndLine.SHORT_SPACE +
            "Example: 2020-03-05\n" + SpaceAndLine.SEPARATION_LINE + "\n";
}
