package duke.task.dateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents dates/times for deadlines and events.
 * <br>
 * A {@code DateTime} object consists of two part:<br>
 * 1. Date: represented by a built-in Java class {@code LocalDate}<br>
 * 2. Time: represented by two integers, which are {@code hourIn24} and {@code minuteIn24}
 */
public class DateTime {
    LocalDate date;
    int hourIn24 = -1;
    int minuteIn24;

    /**
     * Constructor of {@code DateTime}<br>
     * Initializes the object with the given parameter {@code dateTime}
     *
     * @param dateTime date and time [optional] provide by users in the format of "yyyy-mm-dd [hhmm]"
     * @throws IllegalArgumentException If the inputted {@code dateTime} is invalid
     */
    public DateTime(String dateTime) throws IllegalArgumentException {
        initializeDateTime(dateTime);
    }

    /**
     * Returns the date stored in the class
     *
     * @return date of deadline/event
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string of date and time for displaying in Command-Line Interface
     *
     * @return date/time for CLI displaying
     */
    public String toString() {
        return getDateToPrintFormat() + (hourIn24 < 0 ? "" : ", " + convertTimeFormat(hourIn24, minuteIn24));
    }

    /**
     * Returns a string of date and time for storage in text file
     *
     * @return date/time for saving
     */
    public String toSave() {
        return getDateToSaveFormat() + (hourIn24 < 0 ? "" : " " + hourIn24 + minuteIn24);
    }

    private void initializeDateTime(String datetime) throws IllegalArgumentException {
        String[] dateFragments = datetime.split(" ");
        if (dateFragments.length > 2) {
            throw new IllegalArgumentException("Invalid format of date and time provided, format: yyyy-mm-dd [hhmm]");
        }

        initializeDate(dateFragments[0]);
        if (dateFragments.length == 2) {
            initializeTime(dateFragments[1]);
        }
    }

    private void initializeTime(String time) throws IllegalArgumentException {
        if (time.length() != 4) {
            throw new IllegalArgumentException("Invalid time is given, format: tttt in 24 hour time format");
        }
        try {
            hourIn24 = Integer.parseInt(time.substring(0, 2));
            minuteIn24 = Integer.parseInt(time.substring(2, 4));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time is given (only digits are allowed)");
        }
        if (hourIn24 < 0 || hourIn24 > 23 || minuteIn24 < 0 || minuteIn24 > 60) {
            throw new IllegalArgumentException("Invalid time is given (either hour/minute is out of bound)");
        }

    }

    private String convertTimeFormat(int hour, int minute) {
        boolean isMorning = true;
        int hourInNewFormat;
        if (hour > 12) {
            isMorning = false;
        }
        if (isMorning) {
            hourInNewFormat = hour == 0 ? 12 : hour;
            return hourInNewFormat + ":" + minute + "am";
        }
        hourInNewFormat = hour - 12;
        return hourInNewFormat + ":" + minute + "pm";
    }

    private void initializeDate(String date) throws IllegalArgumentException {
        try {
            this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date is given, format: yyyy-MM-dd");
        }
    }

    private String getDateToPrintFormat() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getDateToSaveFormat() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
