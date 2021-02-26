package duke.exception;

import duke.exception.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        this.ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
