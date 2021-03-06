package duke.exception;

import duke.common.Messages;

/**
 * Exception for invalid date time format given by user
 */
public class InvalidDateTimeFormatException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.INVALID_DATETIME_EXCEPTION;
    }
}
