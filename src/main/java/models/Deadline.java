package models;

import java.text.DateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;
    private DateFormat inputFormat = Dates.inputFormat;
    private DateFormat outputFormat = Dates.outputFormat;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outputFormat.format(this.by) + ")";
    }

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
