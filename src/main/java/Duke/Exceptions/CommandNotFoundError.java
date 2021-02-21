package Duke.Exceptions;

public class CommandNotFoundError extends DukeException {

    public CommandNotFoundError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! I'm sorry, but I don't know what that means :-(";

    }

}
