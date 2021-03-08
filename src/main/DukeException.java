package main;

/**
 * Signals that some given data does not fulfill some constraints.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class DukeException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public DukeException(String message) {
        super(message);
    }
}
