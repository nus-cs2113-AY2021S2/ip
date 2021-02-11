public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "OOPS!!! The description of a " + this.message + "cannot be empty.";
    }
}