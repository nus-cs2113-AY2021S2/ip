package duke.data.exception;

import duke.common.Messages;

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
            return Messages.COMPLETE_TASK_ERROR_MESSAGE;
        case EMPTY_TASK_LIST:
            return Messages.EMPTY_TASK_LIST_MESSAGE;
        case INVALID_COMMAND:
            return Messages.INVALID_COMMAND_MESSAGE;
        case EMPTY_TODO:
            return Messages.TODO_EMPTY_MESSAGE;
        case EMPTY_DEADLINE:
            return Messages.DEADLINE_EMPTY_MESSAGE;
        case EMPTY_DEADLINE_TIME:
            return Messages.DEADLINE_EMPTY_TIME_MESSAGE;
        case EMPTY_EVENT:
            return Messages.EVENT_EMPTY_MESSAGE;
        case EMPTY_EVENT_TIME:
            return Messages.EVENT_EMPTY_TIME_MESSAGE;
        case NO_TASK_FOUND:
            return Messages.TASK_DOES_NOT_EXIST;
        case INDEX_FORMAT_ERROR:
            return Messages.INDEX_NUMBER_INPUT_MESSAGE;
        case EMPTY_KEYWORD:
            return Messages.EMPTY_KEYWORD_MESSAGE;
        case INVALID_DATETIME:
            return Messages.INVALID_DATETIME_MESSAGE;
        }
        return Messages.INTERNAL_ERROR_MESSAGE;
    }
}
