package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DeadlineTask extends Task {

    protected String time;

    public DeadlineTask(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String getTaskTime() {
        try {
            LocalDate d1 = LocalDate.parse(time);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return time;
        }
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (by: " + getTaskTime() + ")";
    }
}
