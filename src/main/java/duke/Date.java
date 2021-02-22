package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    private LocalDate date;
    
    public Date(String date) {
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public String formatDateWithDelimeter(String dateFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    protected static void checkValidDate(String dateString) throws DateTimeParseException {
        LocalDate.parse(dateString);
    }
}
