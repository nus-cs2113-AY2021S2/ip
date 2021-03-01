package duke.common;

import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDateTimeFormatException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Utility methods
 */
public class Utils {

    /**
     * Takes in user input and splits it into 2 as long as there is whitespace
     * character in the middle.
     * Reused from Lecture Week 4 Contacts program.
     * @param userInput user input string
     * @return Array of command type and command arguments
     */
    public static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }

    /**
     * Takes in user input and splits it into 2 following the regex pattern given.
     * Generalized version of commandAndArgs.
     * @param userInput user input string
     * @param regex pattern to match and split string
     * @return Array of 2 strings
     */
    public static String[] splitUserInputByRegex(String userInput, String regex) {
        String[] split = userInput.split(regex, 2);
        return split.length == 2 ? split : new String[] {split[0], "" };
    }

    /**
     * Checks if input task number is valid.
     * @param tasks, number
     * @return validity of input.
     */
    public static boolean isValidTaskNumber(ArrayList<Task> tasks, String number) {
        try {
            tasks.get(Integer.parseInt(number) - 1);
        } catch (NumberFormatException e){
            return false;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Takes in user input string and parses it to a LocalDateTime object if it matches the pattern.
     * @param userInput user input string
     * @return LocalDateTime object with date specified in user input string.
     * @throws InvalidDateFormatException invalid date format
     */
    public static LocalDateTime getDateFromUserInput(String userInput) throws InvalidDateFormatException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDateTime date = LocalDate.parse(userInput, dateTimeFormatter).atStartOfDay();
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Checks if deadline specified is before current date.
     * @param date date of deadline
     * @return validity of deadline.
     */
    public static boolean isValidDeadline(LocalDateTime date) {
        if (date.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            return false;
        }
        return true;
    }

    /**
     * Takes in user input string and parses it to a LocalDateTime object if it matches the pattern.
     * @param userInput user input string
     * @return LocalDateTime object with date and time specified in user input string.
     * @throws InvalidDateTimeFormatException invalid date time format
     */
    public static LocalDateTime getDateTimeFromUserInput(String userInput) throws InvalidDateTimeFormatException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(userInput, dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Checks if event specified is before current date and time.
     * @param dateTime date and time of event
     * @return validity of event.
     */
    public static boolean isValidEvent(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }
}
