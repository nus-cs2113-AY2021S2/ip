package duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        this.ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
