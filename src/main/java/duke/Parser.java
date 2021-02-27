package duke;

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
