package duke.parser;

import java.util.Scanner;

public class Parser {

    /**
     * Gets user input
     *
     * @return User input
     */
    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Returns the command of user
     *
     * @param text User input
     * @return First word of user input
     */
    public String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) {

            return text.substring(0, index).trim();

        } else {

            return text;
        }
    }

    /**
     * Returns index of task if delete/done command is inputted
     *
     * @param text User input
     * @return Index of task
     */
    public int getIndexOfTask(String text) {
        String[] parts = text.split("(?<=\\D)(?=\\d)");
        return Integer.parseInt(parts[1]) - 1;
    }
}
