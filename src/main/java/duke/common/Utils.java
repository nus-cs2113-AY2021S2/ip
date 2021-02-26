package duke.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private static final DateTimeFormatter[] POSSIBLE_DATETIME_FORMATS = {
            DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d.M.yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy.M.d HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HHmm")
    };

    private static final DateTimeFormatter[] POSSIBLE_DATE_FORMATS = {
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ofPattern("ddMMyyyy"),
            DateTimeFormatter.ofPattern("d.M.yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy.M.d"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy/M/d")
    };

    public static LocalDateTime parseDate(String dateInput) {
        for (DateTimeFormatter dtf : POSSIBLE_DATE_FORMATS) {
            try {
                return LocalDate.parse(dateInput, dtf).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Used to catch errors from parsing date.
                // Try next possible date format until loop ends.
            }
        }
        return null;
    }

    public static LocalDateTime parseDateTime(String dateTimeInput) {
        for (DateTimeFormatter dtf : POSSIBLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeInput, dtf);
            } catch (DateTimeParseException e) {
                // Used to catch errors from parsing datetime.
                // Try next possible date format until loop ends.
            }
        }
        return parseDate(dateTimeInput);
    }

    public static String outputDateTime(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATETIME_FORMAT);
    }
}
