package models;

import java.text.DateFormat;
import java.util.Date;

public class Event extends Task {

    private Date eventTime;
    private DateFormat inputFormat = Dates.inputFormat;
    private DateFormat outputFormat = Dates.outputFormat;

    public Event(String description, Date eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, Date eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * @return String describing the Event Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + outputFormat.format(this.eventTime) + ")";
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
        return "E" + "#" + done + "#" + description + "#" + inputFormat.format(eventTime) + "\n";
    }
}
