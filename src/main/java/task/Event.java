package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the task list.
 */
public class Event extends Task{
    protected String at;
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
     * Create event.
     * @param task String description of the event.
     * @param at String date and time of the event.
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
        setDateTime(at);
    }

    private void setDateTime(String at) {
        String trimmedAt = at.trim();
        if (Pattern.matches(DATETIME_FORMAT, trimmedAt)) {
            String[] byParts = trimmedAt.split(" ");
            LocalDate tempDate = LocalDate.parse(byParts[0]);
            int tempHour = Integer.parseInt(byParts[1].substring(0, 2));
            int tempMinute = Integer.parseInt(byParts[1].substring(2));
            LocalTime tempTime = LocalTime.of(tempHour, tempMinute);
            this.datetime = LocalDateTime.of(tempDate, tempTime);
        } else if (Pattern.matches(TIME_FORMAT, trimmedAt)) {
            int hour = Integer.parseInt(trimmedAt.substring(0, 2));
            int minute = Integer.parseInt(trimmedAt.substring(2));
            this.time = LocalTime.of(hour, minute);
        } else if (Pattern.matches(DATE_FORMAT, trimmedAt)) {
            this.date = LocalDate.parse(trimmedAt);
        }
    }

    /**
     * Add [E] symbol that represents an event.
     * @return String formatted task in text format with task type,
     * done status and task description.
     */
    @Override
    public String toString() {
        if (Pattern.matches(DATETIME_FORMAT, at.trim())) {
            String formattedDateTime = this.datetime.format(DateTimeFormatter.ofPattern(FINAL_DATETIME_FORMAT));
            return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
        } else if (Pattern.matches(TIME_FORMAT, at.trim())) {
            String formattedTime = this.time.format(DateTimeFormatter.ofPattern(FINAL_TIME_FORMAT));
            return "[E]" + super.toString() + " (at: " + formattedTime + ")";
        } else if (Pattern.matches(DATE_FORMAT, at.trim())) {
            String formattedDate = this.date.format(DateTimeFormatter.ofPattern(FINAL_DATE_FORMAT));
            return "[E]" + super.toString() + " (at: " + formattedDate + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
}