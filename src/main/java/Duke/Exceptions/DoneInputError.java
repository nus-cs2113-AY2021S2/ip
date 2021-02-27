package Duke.Exceptions;

public class DoneInputError extends DukeException {

    public DoneInputError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! \"done\" command must be followed by an integer.";

    }

}
