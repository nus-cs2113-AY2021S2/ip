package duke.data.exception;

import duke.common.Strings;

public class DukeException extends Exception {

    /**
     * Initiates the exception handling object in Duke.
     * @param type Type of Duke exception.
     */
    public DukeException(DukeExceptionKey type) {
        super(categorizeException(type));
    }

    /**
     * Determines the printed line of the exception.
     * @param type Type of Duke exception.
     * @return Exception description.
     */
    private static String categorizeException(DukeExceptionKey type) {
        switch (type) {
        case EMPTY_TASK_COMPLETE_LIST:
            return Strings.COMPLETE_TASK_ERROR_MESSAGE;
        case EMPTY_TASK_LIST:
            return Strings.EMPTY_TASK_LIST_MESSAGE;
        case INVALID_COMMAND:
            return Strings.INVALID_COMMAND_MESSAGE;
        case EMPTY_TODO:
            return Strings.TODO_EMPTY_MESSAGE;
        case EMPTY_DEADLINE:
            return Strings.DEADLINE_EMPTY_MESSAGE;
        case EMPTY_DEADLINE_TIME:
            return Strings.DEADLINE_EMPTY_TIME_MESSAGE;
        case EMPTY_EVENT:
            return Strings.EVENT_EMPTY_MESSAGE;
        case EMPTY_EVENT_TIME:
            return Strings.EVENT_EMPTY_TIME_MESSAGE;
        case NO_TASK_FOUND:
            return Strings.TASK_DOES_NOT_EXIST;
        case INDEX_FORMAT_ERROR:
            return Strings.INDEX_NUMBER_INPUT_MESSAGE;
        }
        return Strings.INTERNAL_ERROR_MESSAGE;
    }
}
