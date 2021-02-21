package duke.taskActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.common.Messages;
import duke.ui.Ui;

public class DateTimeFormatter {
    private static final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern deadlineTimePattern = Pattern.compile("\\d{2}.\\d{2}");
    private static final Pattern eventTimePattern = Pattern.compile("\\d{2}.\\d{2}-\\d{2}.\\d{2}");

    /**
     * Format given deadline and events into standardized patterns.
     * @param dateTime Deadline / Event date and time.
     * @param isEvent True if is event, false if is deadline
     * @return YYYY-MM-DD, HH.MM AmPm
     * @throws DateTimeException When date / time not given or is an invalid date / time.
     */
    public static String deriveDateTime(
        String dateTime, boolean isEvent) throws DateTimeException {

        Matcher dateMatcher = datePattern.matcher(dateTime);
        if (!dateMatcher.find()) {
            throw new DateTimeException("No matching date format");
        }

        LocalDate date = LocalDate.parse(dateMatcher.group());
        String month = date.getMonth().toString();
        String day = Integer.toString(date.getDayOfMonth());
        String year = Integer.toString(date.getYear());

        String timeString = dateTime.replaceAll(dateMatcher.group(), "");
        String time = "";
        if (isEvent) {
            time = deriveEvent(timeString);
        }

        if (!isEvent) {
            time = deriveDeadline(timeString);
        }
        return String.format("%s %s %s, %s", month, day, year, time);
    }

    private static String deriveDeadline(String deadline) throws DateTimeException {
        Matcher timeMatcher = deadlineTimePattern.matcher(deadline);
        if (!timeMatcher.find()) {
            throw new DateTimeException("No matching time format");
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        try{
            SimpleDateFormat twelveHourTimeFormat = new SimpleDateFormat("hh.mm aa");
            return twelveHourTimeFormat.format(timeFormat.parse(deadline));
        } catch (ParseException e){
            throw new DateTimeException("Invalid time given");
        }
    }

    private static String deriveEvent(String event) throws DateTimeException {
        Matcher timeMatcher = eventTimePattern.matcher(event);
        if (!timeMatcher.find()) {
            throw new DateTimeException("No matching time format");
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        String[] eventTimes = event.split("-");
        try{
            SimpleDateFormat twelveHourTimeFormat = new SimpleDateFormat("hh.mm aa");
            Date startTime = timeFormat.parse(eventTimes[0]);
            Date endTime = timeFormat.parse(eventTimes[1]);
            if (startTime.after(endTime)) {
                Ui.notifyError(Messages.INVALID_EVENT_MESSAGE);
                throw new DateTimeException("Invalid time given");
            }
            String eventStart = twelveHourTimeFormat.format(startTime);
            String eventEnd = twelveHourTimeFormat.format(endTime);
            return eventStart + " - " + eventEnd;
        } catch (ParseException e){
            throw new DateTimeException("Invalid time given");
        }
    }
}
