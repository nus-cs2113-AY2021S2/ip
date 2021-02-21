package duke.inputhandlers;

import duke.exception.InvalidCommandException;

import java.util.Arrays;

import static duke.constants.ProgramInts.*;

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

        String[] words = input.split(delimiter);

        if (words.length == 1) {
            throw new InvalidCommandException();
        }

        return words[1].trim();
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
