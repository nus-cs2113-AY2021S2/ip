package duke.parser;

public class Parser {
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
}
