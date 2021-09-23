package duke.exception;

/**
 * Throw exceptions related to Duke.
 *
 * @author Leonardo Irvin Pratama
 */
public class DukeException extends Exception {

    /**
     * Sorry message.
     */
    private static final String SORRY = "Sorry! ";

    /**
     * Initializes message to throw.
     *
     * @param message Exception message to show.
     */
    public DukeException(String message) {
        super(SORRY + message);
    }
}
