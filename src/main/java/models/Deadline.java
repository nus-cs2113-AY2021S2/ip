package models;

import java.text.DateFormat;
import java.util.Date;

public class Deadline extends Task {

    private final DateFormat inputFormat = Dates.inputFormat;
    private final DateFormat outputFormat = Dates.outputFormat;
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * @return String describing the Deadline Task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outputFormat.format(this.by) + ")";
    }

    /**
     * @return String with formatted Data to be written onto Text file
     */
    @Override
    public String formatData() {
        int done;
        if (isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "D" + "#" + done + "#" + description + "#" + inputFormat.format(this.by) + "\n";
    }
}
