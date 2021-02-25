package duke.exception;

import duke.common.Messages;

/**
 * Exception for unknown user input commands
 */
public class UnknownCommandException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.UNKNOWN_COMMAND_EXCEPTION;
    }
}
