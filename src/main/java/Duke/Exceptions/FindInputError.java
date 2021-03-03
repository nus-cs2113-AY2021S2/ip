package Duke.Exceptions;

public class FindInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public FindInputError() {

        this.errorMessage = "\u2639 OOPS!! \"follow\" command must be followed by ONLY ONE \nkeyword for searching";

    }

}
