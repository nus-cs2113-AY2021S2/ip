package Baggie.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Baggie.UI.TEXT.DATE_FORMAT;
import static Baggie.UI.TEXT.DEADLINE_ICON;

public class DeadlineTask extends Task {

    protected String time;

    /**
     * Initializes a deadline task with task description and due time.
     *
     * @param taskDescription Task description of the deadline task.
     * @param time Due time of the deadline task.
     */
    public DeadlineTask(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    /**
     * Returns the due time.
     *
     * @return the due time.
     */
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
     * Returns task type.
     *
     * @return deadline task type icon.
     */
    @Override
    public String getTaskType() {
        return DEADLINE_ICON;
    }

    /**
     * Returns a task summary.
     *
     * @return a task summary.
     */
    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (by: " + getTaskTime() + ")";
    }
}
