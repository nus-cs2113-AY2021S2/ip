package duke.exception;

/**
 * Signals an error occur when the user saved tasks file is in the wrong format.
 */
public class InvalidFileFormatException extends Exception {
    private static final String errorMessage = "Invalid File Format.\n" +
            "Abort loading file.\n";

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
