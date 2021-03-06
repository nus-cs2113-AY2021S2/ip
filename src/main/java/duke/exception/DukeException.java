package duke.exception;

import duke.common.Messages;

/**
 * Superclass for all exceptions within Duke
 */
public class DukeException extends Exception {
    @Override
    public String toString() {
        return Messages.DUKE_EXCEPTION;
    }
}
