package duke.parser;

/**
 * Parse user input into a format recognizable by the program.
 */
public class CommandParser {

    /**
     * Extracts the command from user input.
     * @param input User input.
     * @return Command and its description extracted from the input.
     */
    public String[] parseCommand(String input) {
        String[] output = splitDescription(input, " ");
        output[0] = output[0].toLowerCase();
        return output;
    }

    /**
     * Separates the deadline's date and name from the description.
     * @param description deadline's description provided by user
     * @return deadline's name and its date
     */
    public String[] parseDeadline(String description) {
        return splitDescription(description, " /by ");
    }

    /**
     * Separates the event's date and name from the description.
     * @param description event's description provided by user
     * @return event's name and its date
     */
    public String[] parseEvent(String description) {
        return splitDescription(description, " /at ");
    }

    private String[] splitDescription(String description, String regex) {
        String[] splitted = description.split(regex, 2);
        if (splitted.length == 2) {
            return splitted;
        } else {
            return new String[]{splitted[0], ""};
        }
    }

}
