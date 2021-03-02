package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the user input into the command and arguments
     *
     * @param input User input with both command and arguments
     * @return String array containing 2 items: command and arguments
     */
    public static String[] inputParser(String input) {
        String command;
        String arguments;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            arguments = input.substring(input.indexOf(" ") + 1);
        } else {
            command = input;
            arguments = null;
        }
        return new String[]{command, arguments};
    }

    /**
     * Parses save data from data.txt
     * If the save data is invalid, return description and date as null
     *
     * @param data A line of save data
     * @return Array containing task description, boolean indicating if the task is done and a date (null if no date)
     */
    public static Object[] dataParser(String data) {

        String desc;
        boolean isDone = true;
        String date;

        if (data.charAt(0) == '0') {
            isDone = false;
        }

        int indexOfSeparator;
        switch (data.charAt(1)) {
        case 'T':
            desc = data.substring(2);
            date = null;
            break;
        case 'D':
        case 'E':
            indexOfSeparator = data.indexOf('|');
            desc = data.substring(2, indexOfSeparator);
            date = data.substring(indexOfSeparator + 1);
            break;
        default:
            desc = null;
            date = null;
        }

        return new Object[]{desc, isDone, date};
    }

    /**
     * Parses arguments for tasks with descriptions and dates
     * i.e. Deadlines and Events
     *
     * @param data String to be parsed
     * @param delimiter Delimiter used for splitting data
     * @return String array containing task description and date
     */
    public static String[] descDateParser(String data, String delimiter) {
        String desc = data.substring(0, data.toLowerCase().indexOf(delimiter) - 1);
        String date;
        try {
            date = convertDate(data.substring(data.toLowerCase().indexOf(delimiter) + 4));
        } catch (IndexOutOfBoundsException e) {
            date = null;
        }

        return new String[]{desc, date};
    }

    /**
     * Converts user input date to java.time.localDate format if possible
     *
     * @param date User input
     * @return LocalDate format of user input date if possible. User input if not possible.
     */
    private static String convertDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return String.valueOf(parsedDate.getDayOfMonth()) +
                    ' ' + parsedDate.getMonth() +
                    ' ' + parsedDate.getYear();
        } catch (DateTimeParseException e) {
            return date;
        }

    }
}
