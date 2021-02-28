package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private static String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Takes in the deadline and changes the format of it
     * @param by the date as entered by user
     * @return the formatted string
     * @throws DateTimeParseException if the date format entered by user is invalid
     */
    public static String formatDeadline(String by) throws DateTimeParseException {
        LocalDate d1 = LocalDate.parse(by);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String getBy() {
            return by;
    }
}
