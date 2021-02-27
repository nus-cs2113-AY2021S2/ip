package duke.data.exceptions;

/**
 * This exception is raised when a data field is invalid (i.e. empty).
 * An example of a line containing data fields (stored in a file):
 * - "D | 1 | math hw | 27-02-2021 00:50"
 * - A data field is a fancy term for data segment separated by a delimiter.
 */
public class InvalidFieldException extends Exception {
    public InvalidFieldException() {
        super();
    }

    public InvalidFieldException(String message) {
        super(message);
    }
}
