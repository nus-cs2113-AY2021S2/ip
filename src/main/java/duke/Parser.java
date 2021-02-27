package duke;

public class Parser {

    /**
     * Parses the user input into the command and arguments
     * 
     * @param input User input with both command and arguments
     * @return String array containing 2 items: command and arguments
     */
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

    /**
     * Parses save data from data.txt
     *
     * @param data A line of save data
     * @return Array containing task description, boolean indicating if the task is done and a date (null if no date)
     */
    public static Object[] dataParser(String data) {
        String desc;
        boolean isDone = true;
        String date;

        if (data.charAt(0) == '0') {
            isDone = false;
        }

        int indexOfSeparator;
        switch (data.charAt(1)) {
        case 'T':
            desc = data.substring(2);
            date = null;
            break;
        case 'D':
        case 'E':
            indexOfSeparator = data.indexOf('|');
            desc = data.substring(2, indexOfSeparator);
            date = data.substring(indexOfSeparator + 1);
            break;
        default:
            desc = null;
            date = null;
        }

        return new Object[]{desc, isDone, date};
    }
}
