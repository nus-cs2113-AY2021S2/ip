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
     * @param userInput
     * @return Array of command type and command arguments
     */
    public static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
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

    public static LocalDateTime getDateFromUserInput(String userInput) throws InvalidDateFormatException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDateTime date = LocalDate.parse(userInput, dateTimeFormatter).atStartOfDay();
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    public static boolean isValidDeadline(LocalDateTime date) {
        if (date.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            return false;
        }
        return true;
    }

    public static LocalDateTime getDateTimeFromUserInput(String userInput) throws InvalidDateTimeFormatException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(userInput, dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    public static boolean isValidEvent(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }
}
