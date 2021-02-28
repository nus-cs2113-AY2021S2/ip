package duke.Exceptions;

/**
 * Formatting errors when using <code>EventCommand</code>
 */
public class EventFormatException extends DukeException {

    public EventFormatException() {
        this.errorMessage = "Event command must be in the format: event name /at yyyy-mm-dd hhmm";
    }

}
