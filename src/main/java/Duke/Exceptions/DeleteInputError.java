package Duke.Exceptions;

public class DeleteInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DeleteInputError() {

        this.errorMessage = "\u2639 OOPS!! \"delete\" command must be followed by ONLY ONE integer.";

    }


}
