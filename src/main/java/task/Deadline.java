package task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline in the task list.
 */
public class Deadline extends Task{
    protected String by;
    protected LocalTime time;
    protected LocalDate date;
    protected LocalDateTime datetime;
    private static final String DATE_FORMAT = "\\d+[-]\\d+[-]\\d+";
    private static final String TIME_FORMAT = "\\d{4}";
    private static final String DATETIME_FORMAT = "\\d+-\\d+-\\d+\\s+\\d{4}";
    private static final String FINAL_DATETIME_FORMAT = "MMM dd yyyy HH:mm";
    private static final String FINAL_DATE_FORMAT = "MMM dd yyyy";
    private static final String FINAL_TIME_FORMAT = "HH:mm";

    /**
     * Create deadline.
     * @param task String description of the deadline.
     * @param by String date and time of the deadline.
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
        setDateTime(by);
    }

    private void setDateTime(String by) {
        String trimmedBy = by.trim();
        if (Pattern.matches(DATETIME_FORMAT, trimmedBy)) {
            String[] byParts = trimmedBy.split(" ");
            LocalDate tempDate = LocalDate.parse(byParts[0]);
            int tempHour = Integer.parseInt(byParts[1].substring(0, 2));
            int tempMinute = Integer.parseInt(byParts[1].substring(2));
            LocalTime tempTime = LocalTime.of(tempHour, tempMinute);
            this.datetime = LocalDateTime.of(tempDate, tempTime);
        } else if (Pattern.matches(TIME_FORMAT, trimmedBy)) {
            int hour = Integer.parseInt(trimmedBy.substring(0, 2));
            int minute = Integer.parseInt(trimmedBy.substring(2));
            this.time = LocalTime.of(hour, minute);
        } else if (Pattern.matches(DATE_FORMAT, trimmedBy)) {
            this.date = LocalDate.parse(trimmedBy);
        }
    }


    /**
     * Add [D] symbol that represents a deadline.
     * @return String formatted task in text format with task type,
     * done status and task description.
     */
    @Override
    public String toString() {
        if (Pattern.matches(DATETIME_FORMAT, by.trim())) {
            String formattedDateTime = this.datetime.format(DateTimeFormatter.ofPattern(FINAL_DATETIME_FORMAT));
            return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
        } else if (Pattern.matches(TIME_FORMAT, by.trim())) {
            String formattedTime = this.time.format(DateTimeFormatter.ofPattern(FINAL_TIME_FORMAT));
            return "[D]" + super.toString() + " (by: " + formattedTime + ")";
        } else if (Pattern.matches(DATE_FORMAT, by.trim())) {
            String formattedDate = this.date.format(DateTimeFormatter.ofPattern(FINAL_DATE_FORMAT));
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}