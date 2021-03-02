package duke;

public class Parser {

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
