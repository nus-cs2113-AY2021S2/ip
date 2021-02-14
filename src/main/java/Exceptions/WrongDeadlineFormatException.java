package Exceptions;

public class WrongDeadlineFormatException extends DukeException {
    public String getMessage() {
        return "OOPS!!! Please follow the Deadline format! ";
    }
}
