package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Duke.UI.TEXT.DATE_FORMAT;
import static Duke.UI.TEXT.EVENT_ICON;

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
