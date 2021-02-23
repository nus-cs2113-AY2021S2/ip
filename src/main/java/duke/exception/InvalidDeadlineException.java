package duke.exception;

import duke.common.Messages;

public class InvalidDeadlineException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.MESSAGE_INVALID_DEADLINE;
    }
}
