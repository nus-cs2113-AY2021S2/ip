package duke.viewmodel;

public class DukeException extends Exception {

    /**
     * Initiates the exception handling object in Duke.
     * @param type Type of Duke exception.
     */
    public DukeException(int type) {
        super(categorizeException(type));
    }

    /**
     * Determines the printed line of the exception.
     * @param type Type of Duke exception.
     * @return Exception description.
     */
    private static String categorizeException(int type) {
        switch (type) {
        case Constants.EMPTY_TASK_COMPLETE_LIST:
            return Constants.COMPLETE_TASK_ERROR_MESSAGE;
        case Constants.EMPTY_TASK_LIST:
            return Constants.EMPTY_TASK_LIST_MESSAGE;
        case Constants.INVALID_COMMAND:
            return Constants.INVALID_COMMAND_MESSAGE;
        case Constants.EMPTY_TODO:
            return Constants.TODO_EMPTY_MESSAGE;
        case Constants.EMPTY_DEADLINE:
            return Constants.DEADLINE_EMPTY_MESSAGE;
        case Constants.EMPTY_DEADLINE_TIME:
            return Constants.DEADLINE_EMPTY_TIME_MESSAGE;
        case Constants.EMPTY_EVENT:
            return Constants.EVENT_EMPTY_MESSAGE;
        case Constants.EMPTY_EVENT_TIME:
            return Constants.EVENT_EMPTY_TIME_MESSAGE;
        }
        return Constants.INTERNAL_ERROR_MESSAGE;
    }
}
