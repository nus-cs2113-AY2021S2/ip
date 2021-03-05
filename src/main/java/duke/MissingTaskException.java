package duke;


public class MissingTaskException extends Exception {
    /**
     * Throws an exception to denote missing task inputs
     * when leading spaces is added behind Commands,
     * i.e. todo, event, deadline, delete, done.
     *
     * @param message
     */
    public MissingTaskException(String message) {
        super(message);
    }
}
