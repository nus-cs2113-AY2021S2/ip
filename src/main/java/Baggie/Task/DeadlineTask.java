package Baggie.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Baggie.UI.TEXT.DATE_FORMAT;
import static Baggie.UI.TEXT.DEADLINE_ICON;

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
            return d1.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception e) {
            return time;
        }
    }

    /**
     * Prints task type
     * @return [D]
     */
    @Override
    public String getTaskType() {
        return DEADLINE_ICON;
    }

    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (by: " + getTaskTime() + ")";
    }
}
