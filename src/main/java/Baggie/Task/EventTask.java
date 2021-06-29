package Baggie.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Baggie.UI.TEXT.DATE_FORMAT;
import static Baggie.UI.TEXT.EVENT_ICON;

public class EventTask extends Task {

    protected String time;

    /**
     * Initializes an event task with task description and event time.
     *
     * @param description Task description of the event task.
     * @param time
     */
    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns task type.
     *
     * @return event task type icon.
     */
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

    /**
     * Returns a task summary.
     *
     * @return a task summary.
     */
    @Override
    public String toString() {
        return getTaskType() + super.toString() + " (at: " + getTaskTime() + ")";
    }
}
