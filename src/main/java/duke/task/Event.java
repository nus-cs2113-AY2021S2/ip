package duke.task;

import duke.DateTime;
import duke.exception.InvalidInputException;

public class Event extends Task {
    // This value indicates the version of this class when doing serialization
    public static final long serialVersionUID = 2L;

    protected DateTime at;

    public Event(String description, DateTime at) throws InvalidInputException {
        super(description);
        this.at = at;
        typeIcon = "E";
    }

    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return at.isSameDate(dateTime);
    };

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", at);
    }
}
