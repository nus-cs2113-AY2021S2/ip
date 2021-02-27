package Duke.Exceptions;

public class DeleteInputError extends DukeException {

    public DeleteInputError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! \"delete\" command must be followed by an integer.";

    }


}
