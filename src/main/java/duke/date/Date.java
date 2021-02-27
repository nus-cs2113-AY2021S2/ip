package duke.date;

import duke.parser.Parser;
import duke.task.Task;
import java.time.LocalDate;

public class Date {
    private static final String JAN = "01";
    private static final String FEB = "02";
    private static final String MAR = "03";
    private static final String APR = "04";
    private static final String MAY = "05";
    private static final String JUN = "06";
    private static final String JUL = "07";
    private static final String AUG = "08";
    private static final String SEP = "09";
    private static final String OCT = "10";
    private static final String NOV = "11";
    private static final String DEC = "12";


    private static String getYear(String[] splitDate) {
        return splitDate[2];
    }

    private static String getDay(String[] splitDate) {
        return splitDate[1];
    }

    private static String getMonth(String[] splitDate) {
        return splitDate[0];
    }

    /**
     * Returns true if the date is invalid,
     * false otherwise.
     * The date is invalid if it is in the incorrect
     * format, out of range or not a future date,
     * it's valid otherwise.
     *
     * @param t Task object being validated.
     * @return true if date is invalid, false otherwise.
     */
    public static boolean isInvalidDate(Task t){
        LocalDate currentDate = LocalDate.now();
        String[] splitDate = t.getDate().trim().split("-");

        if (isIncorrectFormatDate(splitDate)){
            return true;
        }

        String month = getMonth(splitDate);
        String day = getDay(splitDate);
        String year = getYear(splitDate);
        String[] splitCurrentDate = Parser.parseCurrentDate(currentDate.toString());
        String currentMonth = getMonth(splitCurrentDate);
        String currentDay = getDay(splitCurrentDate);
        String currentYear = getYear(splitCurrentDate);

        if (isOutOfRangeMonth(month)) {
            return true;
        }

        if (isOutOfRangeDay(month, day, year)) {
            return true;
        }

        if (!isFutureYear(year, currentYear)) {
            return true;
        } else if (!isFutureMonth(month, currentMonth)) {
            return true;
        } else if (!isFutureDay(day, currentDay)) {
            return true;
        }

        return false;
    }

    private static boolean isOutOfRangeDay(String month, String day, String year) {
        if (Integer.parseInt(day) < 1){
            return true;
        }

        switch(month){
        case FEB:
            if (isLeapYear(Integer.parseInt(year))){
                if (Integer.parseInt(day) > 29){
                    return true;
                }
            } else {
                if (Integer.parseInt(day) > 28){
                    return true;
                }
            }
        case JAN:
        case MAR:
        case MAY:
        case JUL:
        case AUG:
        case OCT:
        case DEC:
            if (Integer.parseInt(day) > 31){
                return true;
            }
        case APR:
        case JUN:
        case SEP:
        case NOV:
            if (Integer.parseInt(day) > 30){
                return true;
            }
        }
        return false;
    }

    public static boolean isIncompleteDate(Task t){
        return t.getDate().equals("");
    }

    private static boolean isOutOfRangeMonth(String month) {
        return Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12;
    }

    private static boolean isFutureYear(String year, String currentYear) {
        return Integer.parseInt(year) > Integer.parseInt(currentYear);
    }

    private static boolean isFutureMonth(String month, String currentMonth) {
        return Integer.parseInt(month) > Integer.parseInt(currentMonth);
    }

    private static boolean isFutureDay(String day, String currentDay) {
        return Integer.parseInt(day) > Integer.parseInt(currentDay);
    }

    private static boolean isIncorrectFormatDate(String[] splitDate) {
        return splitDate.length != 3;
    }

    private static boolean isLeapYear(int year){
        return (year % 4 == 0);
    }
}
