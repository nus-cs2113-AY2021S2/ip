package duke;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
        this.separator = "/by";
    }

    public String getBy() {
        return this.byDate.toString();
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}