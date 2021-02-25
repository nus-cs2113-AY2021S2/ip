package duke.exception;

import duke.common.Messages;

/**
 * Exception for invalid event date
 */
public class InvalidEventException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.INVALID_EVENT_EXCEPTION;
    }
}
