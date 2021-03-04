package Duke.Exceptions;

public class EventInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public EventInputError() {

        this.errorMessage = "OOPS!! event command must be in the format: \nevent xxx /at yyyy-mm-dd";

    }

}
