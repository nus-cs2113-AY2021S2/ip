package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;

public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private final DateTime dueDay;

    public Deadline(String taskName, String dueDay) {
        super(taskName);
        this.dueDay = new DateTime(dueDay);
    }

    public LocalDate getDate() {
        return dueDay.getDate();
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + dueDay.toString() + ")";
    }

    @Override
    public String getSaveString() {
        return TASK_TYPE + super.getSaveString() + " /by " + dueDay.toSave();
    }
}
