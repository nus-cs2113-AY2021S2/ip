package duke;

/**
 * A class that deals with the parsing of string when loading tasks from saved file
 */
public class Parser {

    /**
     * parses the input from user to identify the command that was intended
     *
     * @param input Input from user
     * @return either the first word if the input has more than 1 word,
     * or just return the only word if the input is only 1 word
     */
    public static String parse(String input) {
        input = input.trim();
        if (!input.contains(" ")) { //one word
            return input;
        } else { //multiple words
            int i = input.indexOf(' ');
            return input.substring(0, i);
        }
    }
}
