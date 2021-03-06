package duke.exception;

import duke.common.Messages;

/**
 * Exception for failing to load the storage file
 */
public class LoadingException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + Messages.LOADING_EXCEPTION;
    }
}
