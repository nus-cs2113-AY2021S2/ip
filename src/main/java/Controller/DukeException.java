package Controller;
public class DukeException extends Exception {
    public DukeException(String message) {
        super("OOPS!!! The description of a/an " + message + " cannot be empty.");
    }

    public DukeException() {
        super();
    }
}
