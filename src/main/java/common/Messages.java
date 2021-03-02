package common;

/**
 * Collection of messages used by GuiltySpark for easy access
 */
public class Messages {

    public static final String WELCOME_MESSAGE = "Hellow! I'm GuiltySpark. How may I help you?";
    public static final String RECEIVE_MESSAGE = "Got it! Added that task to list!";
    public static final String GETSIZE_MESSAGE = "Now you have 2 tasks";
    public static final String BLANK_EXCEPTION_MESSAGE = "Looks like you're missing something! Try again!";
    public static final String MISSING_DATE_EXCEPTION_MESSAGE
            = "Looks like there isn't a date/time set! Use a '/' as a prefix!!";
    public static final String EMPTY_LIST_EXCEPTION_MESSAGE = "Looks like your list is currently empty!" +
            " Populate it!";
    public static final String DELETE_TASK_MESSAGE = "Task has been deleted from list!";
    public static final String INVALID_NUMBER_MESSAGE = "Invalid value detected. Please input a number!";
    public static final String OUT_OF_RANGE_MESSAGE = "Your number is out of the range of the list! Try again!";
    public static final String SEARCH_LIMIT_EXCEEDED_MESSAGE = "I can only search for 1 keyword at a time! Try again!";
    public static final String NO_MATCHES_FOUND_MESSAGE = "No tasks with that keyword exists!";
    public static final String MARKED_AS_DONE_MESSAGE = "Your task has been marked as done!";
}
