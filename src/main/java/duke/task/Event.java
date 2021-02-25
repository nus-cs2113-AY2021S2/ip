package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;

public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private final DateTime date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = new DateTime(date);
    }

    public LocalDate getDate() {
        return date.getDate();
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + date.toString() + ")";
    }

    @Override
    public String getSaveString() {
        return TASK_TYPE + super.getSaveString() + " /at " + date.toSave();
    }
}
