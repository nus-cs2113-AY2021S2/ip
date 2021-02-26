package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String eventTime;
    protected String formattedTaskDate;

    public Event(String taskDescription, String date) {
        super(taskDescription);
        LocalDate taskDate = LocalDate.parse(date);
        this.formattedTaskDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.eventTime = date;
        this.taskType = "[E]";
    }

    /**
    Prints task to user.
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " + formattedTaskDate + ")";
    }

    /**
    Translates data into text format.
     */
    @Override
    public String taskToText() {
        return "E|" + super.completed + "| " + super.taskDescription + "|" + eventTime;
    }
}
