package duke.exception;

import duke.common.Messages;

/**
 * Exception for invalid date format given by user
 */
public class InvalidDateFormatException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.INVALID_DATE_EXCEPTION;
    }
}
