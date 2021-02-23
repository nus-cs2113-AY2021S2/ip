package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        setBy(by);
    }

    public void setBy(String by) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(by, dateTimeFormatter);
            this.by = dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date or time format is incorrect.\n" +
                    "Correct format: 2021-02-04 22:59");
        }
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getBy() + ")";
    }
}