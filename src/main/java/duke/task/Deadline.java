package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String deadline;
    protected String formattedTaskDate;

    public Deadline(String taskDescription, String date) {
        super(taskDescription);
        LocalDate taskDate = LocalDate.parse(date);
        this.formattedTaskDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.deadline = date;
        this.taskType = "[D]";
    }

    /**
    Prints task to user.
    */
    @Override
    public String toString() {
        return super.toString() + "(by: " + formattedTaskDate + ")";
    }

    /**
    Translates data into text format.
     */
    @Override
    public String taskToText() {
        return "D|" + super.completed + "| " + super.taskDescription + "|" + deadline;
    }
}
