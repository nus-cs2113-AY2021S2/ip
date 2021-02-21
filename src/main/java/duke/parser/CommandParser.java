package duke.parser;

public class CommandParser {
    public String[] parseCommand(String input) {
        String[] output = splitDescription(input, " ");
        output[0] = output[0].toLowerCase();
        return output;
    }

    public String[] parseDeadline(String description) {
        return splitDescription(description, " /by ");
    }

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
