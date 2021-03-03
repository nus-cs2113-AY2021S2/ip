package Duke.Exceptions;

public class FindInputError extends DukeException {

    public FindInputError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! \"follow\" command must be followed by ONLY ONE \nkeyword for searching";

    }

}
