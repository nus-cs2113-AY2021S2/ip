package duke.task;

import duke.DateTime;
import duke.exception.InvalidInputException;

public class Deadline extends Task {
    // This value indicates the version of this class when doing serialization
    public static final long serialVersionUID = 01L;

    protected DateTime by;

    public Deadline(String description, DateTime by) throws InvalidInputException {
        super(description);
        this.by = by;
        typeIcon = "D";
    }

    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return by.isSameDate(dateTime);
    };

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", by);
    }
}
