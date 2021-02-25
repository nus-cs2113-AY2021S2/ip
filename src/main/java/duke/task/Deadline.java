package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String formatDeadline(String by) {
        LocalDate d1 = LocalDate.parse(by);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String getBy() {
        return formatDeadline(by);
    }
}
