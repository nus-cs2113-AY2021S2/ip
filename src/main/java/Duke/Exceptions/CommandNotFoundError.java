package Duke.Exceptions;

public class CommandNotFoundError extends DukeException {

    /**
     * Constructor for the error message
     */
    public CommandNotFoundError() {

        this.errorMessage = "OOPS!! I'm sorry, but I don't know what that means :-(";

    }

}
