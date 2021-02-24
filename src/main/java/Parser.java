import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Returns an integer that can be used as task index
     * @param input input string that user entered
     * @return the integer index that user entered
     */
    protected static int parseInt(String input) {
        return Integer.parseInt(input);
    }

    protected static void parseDate(String by) {
        LocalDate date = LocalDate.parse(by);
        by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
