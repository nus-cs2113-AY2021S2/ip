package duke.task.dateTime;

import duke.exception.InvalidArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents dates/times for class {@code Deadline} and {@code Event}. An {@code DateTime} object is initialized
 * with a date and a time. Also, It provides methods for returning the stored date and a string of the date/time
 * of the object in displaying/saving formats.
 */
public class DateTime {
    LocalDate date;
    int hourIn24HourClock = -1;
    int minuteIn24HourClock;

    /**
     * Constructor of {@code DateTime}<br>
     * Initializes the object with the given parameter. The given {@code dateTimeInput} will be processed and key
     * information (i.e. date and time) will be extracted and stored in the object.
     *
     * @param dateTimeInput date and time [optional] provide by users in the format of "yyyy-mm-dd [hhmm]"
     * @throws InvalidArgumentException If the inputted {@code dateTime} is invalid
     */
    public DateTime(String dateTimeInput) throws InvalidArgumentException {
        initializeDateTime(dateTimeInput);
    }

    public LocalDate getDate() {
        return date;
    }

    private void initializeDateTime(String datetime) throws InvalidArgumentException {
        String[] dateFragments = datetime.split(" ");
        if (dateFragments.length > 2 || dateFragments.length <= 0) {
            throw new InvalidArgumentException("Invalid format of date and time provided, format: yyyy-mm-dd [hhmm]");
        }

        initializeDate(dateFragments[0]);
        if (dateFragments.length == 2) {
            initializeTime(dateFragments[1]);
        }
    }

    private void initializeDate(String date) throws InvalidArgumentException {
        try {
            this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Invalid date is given, format: yyyy-MM-dd");
        }
    }

    private void initializeTime(String time) throws InvalidArgumentException {
        if (time.length() != 4) {
            throw new InvalidArgumentException("Invalid time is given, format: tttt in 24 hour time format");
        }

        try {
            hourIn24HourClock = Integer.parseInt(time.substring(0, 2));
            minuteIn24HourClock = Integer.parseInt(time.substring(2, 4));
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid time is given (only digits are allowed)");
        }

        if (hourIn24HourClock < 0 || hourIn24HourClock > 23 || minuteIn24HourClock < 0 || minuteIn24HourClock > 60) {
            throw new InvalidArgumentException("Invalid time is given (hour/minute is out of bound)");
        }
    }

    /**
     * Returns a string of date and time for displaying in Command-Line Interface
     *
     * @return date/time for CLI displaying
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getDateOfPrintFormat());
        if (hourIn24HourClock >= 0) {
            builder.append(", ").append(getTimeIn12HourClock());
        }
        return builder.toString();
    }

    /**
     * Returns a string of date and time for storage in text file
     *
     * @return date/time for saving
     */
    public String toSave() {
        StringBuilder builder = new StringBuilder();
        builder.append(getDateOfSaveFormat());
        if (hourIn24HourClock >= 0) {
            builder.append(" ");
            String hourPadding = hourIn24HourClock < 10 ? "0" : "";
            String minutePadding = minuteIn24HourClock < 10 ? "0" : "";
            builder.append(hourPadding).append(hourIn24HourClock).append(minutePadding).append(minuteIn24HourClock);
        }
        return builder.toString();
    }

    private String getTimeIn12HourClock() {
        if (hourIn24HourClock < 12) {
            int hourIn12HourClock = hourIn24HourClock == 0 ? 12 : hourIn24HourClock;
            return hourIn12HourClock + ":" + (minuteIn24HourClock < 10 ? "0" : "") + minuteIn24HourClock + "am";
        }
        int hourIn12HourClock = hourIn24HourClock == 12 ? hourIn24HourClock : hourIn24HourClock - 12;
        return hourIn12HourClock + ":" + (minuteIn24HourClock < 10 ? "0" : "") + minuteIn24HourClock + "pm";
    }

    private String getDateOfPrintFormat() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getDateOfSaveFormat() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
