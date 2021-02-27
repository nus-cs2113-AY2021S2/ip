package duke.inputhandlers;

import duke.exception.InvalidCommandException;

import static duke.constants.ProgramInts.*;
import static duke.constants.ProgramStrings.INPUT_DATE_FORMAT;
import static duke.constants.ProgramStrings.OUTPUT_DATE_FORMAT;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse user input to determine command to be executed.
 * Also parses task details where applicable.
 */

public class Parser {

    /**
     * Parse user input into specified command.
     *
     * @param input full user input string
     * @return an integer representing the command parsed
     */
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
        } else if (startsWith(input, "undo")) {
            return UNDO_COMMAND;
        } else {
            return UNKNOWN_COMMAND;
        }
    }

    /**
     * Parses the description of a task from the user input string.
     *
     * @param input full user input string
     * @return task description string
     * @throws InvalidCommandException if input does not contain a description
     */
    public static String parseDescription(String input) throws InvalidCommandException {

        // split the input string into individual words
        String[] words = input.split(" ");

        // if true, indicates that input string does not have any other words other than the original command
        if (words.length < 2) {
            throw new InvalidCommandException();
        }

        return getDescriptionString(words);
    }

    /**
     * (Overload of {@code parseDescription} method)
     * Parse the description of a task from the user input string.
     * Delimiter is used to mark the end of task description in the user input, applicable for commands which have
     * further fields.
     *
     * @param input     full user input string
     * @param delimiter string sequence to indicate end of task description
     * @return task description string up till delimiter
     * @throws InvalidCommandException if input does not contain a description
     */
    public static String parseDescription(String input, String delimiter) throws InvalidCommandException {

        // split the input string into individual words
        String[] words = input.split(" ");

        // if true, indicates that input string does not have any other words other than the original command
        if (words.length < 2) {
            throw new InvalidCommandException();
        }

        return getDescriptionString(words, delimiter);
    }

    /**
     * Obtains entire task description from user input until end of input.
     *
     * @param words array of strings consisting of user input split into individual words
     * @return task description as a single string
     */
    private static String getDescriptionString(String[] words) {

        String description = words[1];

        for (int i = 2; i < words.length; i++) {
            description += " " + words[i];
        }

        return description;
    }

    /**
     * Obtains entire task description from user input until it reaches a delimiter.
     *
     * @param words     array of strings consisting of user input split into individual words
     * @param delimiter specifies the end of task description
     * @return task description as a single string
     */
    private static String getDescriptionString(String[] words, String delimiter) throws InvalidCommandException {

        String description = words[1];

        for (int i = 2; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(delimiter)) {
                break;
            }
            description += " " + words[i];
        }

        // handles case where user input does not contain task description
        if (description.contains(delimiter)) {
            throw new InvalidCommandException();
        }

        return description;
    }


    /**
     * Parse time/date from user input.
     * Reformats date entered as {@code d-M-yyyy} into {@code dd MMMM, yyyy}.
     *
     * @param input     full user input string
     * @param delimiter string sequence marking the start of the date/time field
     * @return date/time string
     * @throws InvalidCommandException if specified delimiter is not found
     */
    public static String parseDate(String input, String delimiter) throws InvalidCommandException {
        // splits user input using delimiter, date/time string starts after delimiter
        String[] words = input.split(delimiter);

        // delimiter not found
        if (words.length == 1) {
            throw new InvalidCommandException();
        }

        String dateString = words[1].trim();

        // reformats date string if it can be parsed (contains three "-")
        if (isDate(dateString)) {
            return convertToDate(dateString);
        }

        // returns string as entered by user
        return dateString;
    }

    /**
     * Reformats given date string to a different format: {@code OUTPUT_DATE_FORMAT}.
     * Date string is valid only if it follows specified format: {@code INPUT_DATE_FORMAT}.
     *
     * @param string date string from parsed from user input
     * @return reformatted date string
     * @throws DateTimeParseException if given date is invalid / wrong string format / values given are invalid
     */
    public static String convertToDate(String string) throws DateTimeParseException {
        // specifies the format that user has to follow
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        LocalDate dateObj = LocalDate.parse(string, parseFormat);

        // specifies the format that date will be printed out
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        //noinspection UnnecessaryLocalVariable
        String formattedString = dateObj.format(outputFormat);

        return formattedString;
    }

    /**
     * Checks if given string can be parsed as a formatted date.
     * Does not guarantee validity of date string.
     *
     * @param string date string parsed from user input
     * @return true if date string can be parsed
     */
    private static boolean isDate(String string) {
        String[] words = string.split("-");

        return (words.length == 3);
    }

    /**
     * Parses search keyword from user input string.
     *
     * @param input full user input string
     * @return keyword string
     * @throws InvalidCommandException if no keyword is found
     */
    public static String parseKeyword(String input) throws InvalidCommandException {

        String[] words = input.split(" ");
        if (words.length < 2) {
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

    /**
     * Tests if user input string starts with a particular command string.
     *
     * @param input   full user input string
     * @param command string sequence of command being checked
     * @return true if user input starts with command string
     */
    private static boolean startsWith(String input, String command) {
        return input.toUpperCase().startsWith(command.toUpperCase());
    }
}
