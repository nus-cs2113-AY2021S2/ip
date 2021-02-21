package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getTaskTime(){
        try {
            LocalDate d1 = LocalDate.parse(time);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return time;
        }
    }

    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (at: " + getTaskTime() + ")";
    }
}
