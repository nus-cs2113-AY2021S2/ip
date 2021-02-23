package duke.exception;

import duke.common.Messages;

public class UnknownCommandException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " " + Messages.MESSAGE_UNKNOWN_COMMAND;
    }
}
