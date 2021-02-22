package constant;

/**
 * Constant class containing the fixed constants.
 */
public class Constant {
    /**
     * Constants used to represent each individual common code.
     */
    public static final int INPUT_CODE_EXIT = -1;
    public static final int INPUT_CODE_DEFAULT_INVALID = 0;
    public static final int INPUT_CODE_LIST = 1;
    public static final int INPUT_CODE_DONE = 2;
    public static final int INPUT_CODE_INVALID = 3;
    public static final int INPUT_CODE_TODO = 4;
    public static final int INPUT_CODE_DEADLINE = 5;
    public static final int INPUT_CODE_EVENT = 6;
    public static final int INPUT_CODE_DELETE = 7;
    public static final int INPUT_CODE_FIND = 8;

    /**
     * Constants used for printing repetitive messages.
     */
    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static final String LIST_STARTING_MESSAGE = "Below are all your task:";
    public static final String FILE_PATH = "task_logs.txt";
    public static final String FILE_LOAD_MESSAGE = "Loading file...";
    public static final String FILE_LOAD_SUCCESS = "Loading Successful!";
    public static final String FILE_LOAD_FAILURE = "Loading Failed - Unable to detect file.";
    public static final String FILE_LOAD_SUCCESS_WELCOME = "What can Mushroom Tracker do for you?";
}
