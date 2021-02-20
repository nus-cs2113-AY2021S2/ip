package duke;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime implements Serializable {
    public static final long serialVersionUID = 1L;

    // Default datetime is "d-M-y H:m", e.g. 01-05-2021 12:30
    // Store as a static member to ensure consistent representation of all objects
    protected static String datePattern = "dd/MM/yyyy";
    protected static String timePattern = "HH:mm";

    protected LocalDateTime dateTime;

    public DateTime(String dateTimeString) {
        try {
            dateTime =
                    LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(datePattern + " " + timePattern));
        } catch (DateTimeParseException e) {
            // Fail to parse a datetime - try to parse a date (with time set at 12 PM noon)
            LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern(datePattern));
            dateTime = LocalDateTime.of(date, LocalTime.parse("12:00"));
        }
    }

    public Boolean isSameDate(DateTime dateTime) {
        return this.dateTime.toLocalDate().isEqual(dateTime.dateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern(datePattern + " " + timePattern));
    }
}
