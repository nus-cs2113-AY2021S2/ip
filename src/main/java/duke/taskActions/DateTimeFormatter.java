package duke.taskActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeFormatter {
    private static final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern timePattern = Pattern.compile("\\d{2}.\\d{2}");

    /**
     * Format given deadline and events into standardized patterns.
     * @param dateTime Deadline / Event date and time.
     * @return YYYY-MM-DD, HH.MM AmPm
     * @throws DateTimeException When date / time not given or is an invalid date / time.
     */
    public static String deriveDateTime(String dateTime) throws DateTimeException {
        Matcher dateMatcher = datePattern.matcher(dateTime);
        if (!dateMatcher.find()) {
            throw new DateTimeException("No matching date format");
        }

        LocalDate date = LocalDate.parse(dateMatcher.group());
        String month = date.getMonth().toString();
        String day = Integer.toString(date.getDayOfMonth());
        String year = Integer.toString(date.getYear());

        String timeString = dateTime.replaceAll(dateMatcher.group(), "");
        Matcher timeMatcher = timePattern.matcher(timeString);
        if (!timeMatcher.find()) {
            throw new DateTimeException("No matching time format");
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        try{
            SimpleDateFormat twelveHourTimeFormat = new SimpleDateFormat("hh.mm aa");
            String time = twelveHourTimeFormat.format(timeFormat.parse(timeString));
            return String.format("%s %s %s, %s", month, day, year, time);
        } catch (ParseException e){
            throw new DateTimeException("Invalid time given");
        }
    }
}
