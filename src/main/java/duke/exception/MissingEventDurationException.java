package duke.exception;

/**
 * This exception is thrown when a new event is created with no corresponding duration
 */
public class MissingEventDurationException extends Exception {
    @Override
    public String getMessage() {
        return "Hmm...have you input the duration of the event?";
    }
}
