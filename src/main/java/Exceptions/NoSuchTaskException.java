package Exceptions;

public class NoSuchTaskException extends DukeException {

    public String getMessage() {
        return "OOPS! No such task exists, please try again.";
    }
}
