package constant;

/**
 * Constant class containing the fixed constants.
 */
public class Constant {
    /**
     * Constants used to represent each individual common code.
     */
    public final int INPUT_CODE_EXIT = -1;
    public final int INPUT_CODE_DEFAULT_INVALID = 0;
    public final int INPUT_CODE_LIST = 1;
    public final int INPUT_CODE_DONE = 2;
    public final int INPUT_CODE_INVALID = 3;
    public final int INPUT_CODE_TODO = 4;
    public final int INPUT_CODE_DEADLINE = 5;
    public final int INPUT_CODE_EVENT = 6;
    public final int INPUT_CODE_DELETE = 7;
    public final int INPUT_CODE_FIND = 8;

    /**
     * Constants used for printing repetitive messages.
     */
    public final String DIVIDER_LINE = "____________________________________________________________";
    public final String LIST_STARTING_MESSAGE = "Below are all your list of mushroom points:";
    public final String FILE_PATH = "task_logs.txt";
    public final String FILE_LOAD_MESSAGE = "Loading file...";
    public final String FILE_LOAD_SUCCESS = "Loading Successful!";
    public final String FILE_LOAD_FAILURE = "File not found! :(";
    public final String FILE_LOAD_SUCCESS_WELCOME = "What can mushroom boy do for you?";
}
