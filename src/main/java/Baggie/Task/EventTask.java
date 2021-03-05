package Baggie.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Baggie.UI.TEXT.DATE_FORMAT;
import static Baggie.UI.TEXT.EVENT_ICON;

public class EventTask extends Task {

    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskType() {
        return EVENT_ICON;
    }

    /**
     * Gets time info
     * @return time with correct format if applicable
     */
    @Override
    public String getTaskTime(){
        try {
            LocalDate d1 = LocalDate.parse(time);
            return d1.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception e) {
            return time;
        }
    }

    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (at: " + getTaskTime() + ")";
    }
}
