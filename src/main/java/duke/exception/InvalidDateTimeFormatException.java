package duke.exception;

import duke.common.Messages;

public class InvalidDateTimeFormatException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.MESSAGE_INVALID_DATETIME;
    }
}
