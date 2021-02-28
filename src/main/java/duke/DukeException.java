package duke;

/**
 * An exception class used to handle all exceptions within Duke program.
 * A standardised header is attached to every exception message.
 */
public class DukeException extends Exception {

    private static final String HEADER = "☹ OOPS!!! ";

    public DukeException(String errorMessage) {
        super(getHeader() + errorMessage);
    }

    public DukeException(String errorMessage, Throwable err) {
        super(getHeader() + errorMessage, err);
    }

    private static String getHeader() {
        return HEADER;
    }
}
