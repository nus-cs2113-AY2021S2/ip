package duke.exception;

import duke.common.Messages;

public class LoadingException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " " + Messages.MESSAGE_LOADING_FAILED;
    }
}
