package duke.data.task;

import static duke.common.Utils.parseDateTime;
import static duke.common.Utils.outputDateTime;

public class Event extends TaskWithDateTime {
    private static final String PRINT_STRING_FORMAT = "[E]%s (at: %s)";

    private String at;

    public Event(String description, String at) {
        this(description, false, at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        dateTime = parseDateTime(at);
        hasDateTime = dateTime != null;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        if (hasDateTime) {
            return String.format(PRINT_STRING_FORMAT, super.toString(), outputDateTime(dateTime));
        } else {
            return String.format(PRINT_STRING_FORMAT, super.toString(), at);
        }
    }

    @Override
    public String toFileEntry() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, 'E', super.toFileEntry(), at);
    }
}
