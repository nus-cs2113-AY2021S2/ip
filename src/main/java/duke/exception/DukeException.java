package duke.exception;

import duke.common.Messages;

public class DukeException extends Exception {
    @Override
    public String toString() {
        return Messages.MESSAGE_ERROR;
    }
}
