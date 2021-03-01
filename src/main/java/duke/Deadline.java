package duke;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Constructor for Deadline class
     *
     * @param description  the description of the task
     * @param by date which the task is to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
        this.separator = "/by";
    }

    /**
     * Returns the date which the task is to be completed by, after converting it to a string (from LocalDate class)
     *
     * @return the string form of the task deadline
     */
    public String getBy() {
        return this.byDate.toString();
    }

    /**
     * Returns the string form of the specific type of task
     *
     * @return string description of the specific type of task
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Returns the task in string form
     *
     * @return String form of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}