package duke.Exceptions;

public class FindFormatException extends DukeException {

    public FindFormatException() {
        this.errorMessage = "Find command must be in format: find keyword(s)";
    }
}
