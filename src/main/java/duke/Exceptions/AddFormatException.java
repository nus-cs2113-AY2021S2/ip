package duke.Exceptions;

public class AddFormatException extends DukeException {

    public AddFormatException(String command) {
        this.errorMessage = "Please enter the description of " + command;
    }
}
