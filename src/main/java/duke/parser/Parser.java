package duke.parser;

import duke.exception.IllegalTaskCommandException;

import java.util.StringJoiner;

public class Parser {
    /**
     * Parses raw userInput to extract the command string.
     *
     * @param userInput is the raw input from the console.
     * @return command string in upper case.
     */
    public static String getCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        return command.toUpperCase();
    }

    /**
     * Parses raw userInput to return the Task segment.
     *
     * @param userInput is the raw input from the console.
     * @return Task segment from the userInput.
     * @throws IllegalTaskCommandException when there is no trailing Task segment.
     */
    public static String getSubstring(String userInput) throws IllegalTaskCommandException {
        String cleanUserInput = userInput.strip(); // Removes any leading and trailing spaces
        int spacePosition = cleanUserInput.indexOf(" ");
        if (spacePosition < 0) { // Is single command
            throw new IllegalTaskCommandException("Insufficient command parameters commander!");
        }
        return cleanUserInput.substring(spacePosition + 1);
    }

    /**
     * Parses raw userInput to return the Task description.
     *
     * @param userInput is the raw input from the console.
     * @return Description of the Task.
     * @throws IllegalTaskCommandException when Tasks is expected to have a date/time segment.
     */
    public static String getErrand(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        if (slashPosition < 0) {
            throw new IllegalTaskCommandException("Timestamp not found commander!");
        }
        String errand = inputSubstring.substring(0, slashPosition);
        return errand.trim();
    }

    /**
     * Parses raw userInput to return the Task description.
     *
     * @param userInput is the raw input from the console.
     * @return Timestamp header of the Task.
     * @throws IllegalTaskCommandException when Tasks is expected to have a date/time header.
     */
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

    /**
     * Parses raw userInput to return the Task description.
     *
     * @param userInput is the raw input from the console.
     * @return Timestamp of the Task.
     */
    public static String getTimestamp(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestamp = inputSubstring.substring(slashPosition);
        int spacePosition = timestamp.indexOf(" ");
        timestamp = timestamp.substring(spacePosition);
        return timestamp.trim();
    }

    /**
     * Loads the commands from the file into an input format that is
     * recognised by the CommandHandler.
     *
     * @param fileInput is the raw input from the loaded save file.
     * @return A string of command that is recognised by the CommandHandler.
     */
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
