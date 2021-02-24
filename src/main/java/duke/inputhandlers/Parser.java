package duke.inputhandlers;

import duke.exception.InvalidCommandException;
import static duke.constants.ProgramInts.*;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse user input to determine command to be executed.
 * Also parses task details where applicable.
 */

public class Parser {

    public static int parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return BYE_COMMAND;
        } else if (input.equalsIgnoreCase("list")) {
            return LIST_COMMAND;
        } else if (input.equalsIgnoreCase("help")) {
            return HELP_COMMAND;
        } else if (startsWith(input, "done")) {
            return DONE_COMMAND;
        } else if (startsWith(input, "todo")) {
            return TODO_COMMAND;
        } else if (startsWith(input, "deadline")) {
            return DEADLINE_COMMAND;
        } else if (startsWith(input, "event")) {
            return EVENTS_COMMAND;
        } else if (startsWith(input, "delete")) {
            return DELETE_COMMAND;
        } else if (startsWith(input, "find")) {
            return FIND_COMMAND;
        } else {
            return UNKNOWN_COMMAND;
        }
    }

    public static String parseJob(String input, String delimiter) throws InvalidCommandException {

        String[] words = input.split(" ");

        if (words.length < 2) {
            throw new InvalidCommandException();
        }

        return getJobString(words, delimiter);
    }

    public static String parseDate(String input, String delimiter) throws InvalidCommandException {
        // deadline job job /by 29-01-2021
        String[] words = input.split(delimiter);

        if (words.length == 1) {
            throw new InvalidCommandException();
        }

        String dateString = words[1].trim();

        if (isDate(dateString)) {
            return convertToDate(dateString);
        }

        return dateString;
    }


    public static String convertToDate(String string) throws DateTimeParseException {
        // specifies the format that user has to follow
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate dateObj = LocalDate.parse(string, parseFormat);

        // specifies the format that date will be printed out
        DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        //noinspection UnnecessaryLocalVariable
        String formattedString = dateObj.format(stringFormat);

        return formattedString;
    }

    private static boolean isDate(String string) {
        String[] words = string.split("-");
        
        return (words.length == 3);
    }

    public static String parseKeyword(String input) throws InvalidCommandException{

        String[] words = input.split(" ");
        if (words.length <2) {
            throw new InvalidCommandException();
        }
        
        String[] keywords = Arrays.copyOfRange(words, 1, words.length);

        String keywordString = "";

        for (String eachWord : keywords) {
            keywordString += eachWord;
            keywordString += " ";
        }
        
        return keywordString.trim();
    }

    private static String getJobString(String[] words, String delimiter) {

        String job = words[1];

        for (int i = 2; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(delimiter)) {
                break;
            }
            job += " " + words[i];
        }
        return job;
    }

    private static boolean startsWith(String input, String command) {
        return input.toUpperCase().startsWith(command.toUpperCase());
    }
}
