package duke.data.exceptions;

/**
 * A general exception class for duke used to indicate input
 * errors made in Duke app. The method handling this exception
 * should print message error to the user.
 */
public class DukeException extends Exception {

    /**
     * @param message a message to print regarding an error
     *                occurrence.
     */
    public DukeException(String message) {
        super(message);
    }
}
