import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the parsing of specific date inputs
 */

public class DateParser {
    private static final List<String> dateFormats = Arrays.asList("d/M/y H:m",
            "d-M-y H:m", "y/M/d H:m", "y-M-d H:m", "d/M/y", "d-M-y", "y/M/d", "y-M-d",
            "H:m", "Hm");

    public static String readTime(String startTime) {
        try {
            LocalDate parseTime = parseDate(startTime);
            return parseTime.toString();
        } catch (NoTimeFormatException e) {
            return startTime;
        }
    }

    private static LocalDate parseDate(String dateString) throws
            NoTimeFormatException {
        for (String format: dateFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (java.time.format.DateTimeParseException  e) {
                continue;
            }
        }

        throw new NoTimeFormatException();
    }
}
