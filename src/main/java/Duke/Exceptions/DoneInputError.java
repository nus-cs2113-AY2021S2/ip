package Duke.Exceptions;

public class DoneInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DoneInputError() {

        this.errorMessage = "OOPS!! \"done\" command must be followed by ONLY ONE integer.";

    }

}
