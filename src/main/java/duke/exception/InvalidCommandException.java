package duke.exception;

import duke.exception.DukeException;

public class InvalidCommandException extends DukeException {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means :-(";

    public InvalidCommandException() {
    }

    public String getMessage() {
        return MESSAGE;
    }
}
