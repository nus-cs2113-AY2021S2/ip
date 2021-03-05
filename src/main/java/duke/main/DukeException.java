package duke.main;

/**
 * Represent exception class in the application
 */
public class DukeException extends Exception {
    public DukeException () {}
    public DukeException (String message) {
        super(message);
    }
}
