package duke.parser;

import java.util.Scanner;

public class Parser {

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    public int getIndexOfTask(String line) {
        String[] parts = line.split("(?<=\\D)(?=\\d)");
        return Integer.parseInt(parts[1]) - 1;
    }
}
