package duke.data.task;

import static duke.common.Utils.parseDateTime;
import static duke.common.Utils.outputDateTime;

public class Deadline extends TaskWithDateTime {
    private static final String PRINT_STRING_FORMAT = "[D]%s (by: %s)";

    private String by;

    public Deadline(String description, String by) {
        this(description, false, by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        dateTime = parseDateTime(by);
        hasDateTime = dateTime != null;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        if (hasDateTime) {
            return String.format(PRINT_STRING_FORMAT, super.toString(), outputDateTime(dateTime));
        } else {
            return String.format(PRINT_STRING_FORMAT, super.toString(), by);
        }
    }

    @Override
    public String toFileEntry() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, 'D', super.toFileEntry(), by);
    }
}
