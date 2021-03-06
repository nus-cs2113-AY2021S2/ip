package duke.exception;

import duke.common.Messages;

/**
 * Exception for invalid deadline date
 */
public class InvalidDeadlineException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.INVALID_DEADLINE_EXCEPTION;
    }
}
