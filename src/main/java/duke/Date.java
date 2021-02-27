package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date. A date object corresponds to a date (yyyy-MM-dd) given by the user input. 
 * Applicable for deadline and event tasks. 
 */
public class Date {
    private LocalDate date;
    
    /**
     * Converts date into LocalDate and store in Date class.  
     */
    public Date(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Gets the date. 
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Converts given date into LocalDate and sets the Date class. 
     * 
     * @param date Date to be set. 
     * @throws DateTimeParseException If dateString is not a valid date.
     */
    public void setDate(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date);
    }

    /**
     * Formats the date into string using a date format. 
     * 
     * @param dateFormat A string to indicate the format of date to output.
     *                   E.g. "yyyy-MM-dd", "dd-MMM-yyyy". 
     * @return Formatted String. 
     */
    public String formatDateWithDelimeter(String dateFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
