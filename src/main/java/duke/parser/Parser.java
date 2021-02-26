package duke.parser;

import duke.exception.IllegalTaskCommandException;

import java.util.StringJoiner;

public class Parser {
    public static String getCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        return command.toUpperCase();
    }

    public static String getSubstring(String userInput) throws IllegalTaskCommandException {
        String cleanUserInput = userInput.strip(); // Removes any leading and trailing spaces
        int spacePosition = cleanUserInput.indexOf(" ");
        if (spacePosition < 0) { // Is single command
            throw new IllegalTaskCommandException("Insufficient command parameters commander!");
        }
        return cleanUserInput.substring(spacePosition + 1);
    }

    public static String getErrand(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        if (slashPosition < 0) {
            throw new IllegalTaskCommandException("Timestamp not found commander!");
        }
        String errand = inputSubstring.substring(0, slashPosition);
        return errand.trim();
    }

    public static String getTimestampHeader(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestampHeader = inputSubstring.substring(slashPosition);
        int spacePosition = timestampHeader.indexOf(" ");
        if (timestampHeader.split(" ").length < 2) {
            throw new IllegalTaskCommandException("Missing timestamp commander!");
        }
        timestampHeader = timestampHeader.substring(1, spacePosition);
        return timestampHeader;
    }

    public static String getTimestamp(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestamp = inputSubstring.substring(slashPosition);
        int spacePosition = timestamp.indexOf(" ");
        timestamp = timestamp.substring(spacePosition);
        return timestamp.trim();
    }

    public static String getLoadedCommand(String[] fileInput) {
        StringJoiner commandString = new StringJoiner(" ");
        for (int i = 0; i < fileInput.length; i++) {
            if (i == 1) {
                continue; // Skips the completion status
            }
            commandString.add(fileInput[i].strip());
        }
        return commandString.toString();
    }
}
