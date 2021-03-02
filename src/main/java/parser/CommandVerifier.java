package parser;

import exceptions.MissingInfoException;
import exceptions.UnknownCommandException;
import exceptions.UnknownFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandVerifier {

    private static final String MISSING_INFO_MESSAGE =
            "\uD83D\uDE2D %s of the %s is missing.";
    private static final String UNKNOWN_COMMAND_MESSAGE =
            "\uD83D\uDE2D Sorry mate I do not understand your request. Please specify task";
    private static final String UNKNOWN_FORMAT_MESSAGE =
            "\uD83D\uDE2D Format for %s is invalid! (Date format: dd/MM/yyyy HHmm)";
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private String[] subStrings;
    private String command;

    public void verify(String command) throws UnknownCommandException, MissingInfoException {

        this.command = command;
        subStrings = command.split(" ");
        if (!validCommand(subStrings[0])) {
            throw new UnknownCommandException(UNKNOWN_COMMAND_MESSAGE);
        }

        switch (subStrings[0]) {
        case "todo":
            checkTodo();
            break;
        case "deadline":
            checkDeadline();
            break;
        case "event":
            checkEvent();
            break;
        case "done":
            checkDone();
            break;
        case "delete":
            checkDelete();
            break;
        case "find":
            checkFind();
        default:
            break;
        }
    }

    private boolean validCommand(String firstWord) {
        return firstWord.equals("todo")
                || firstWord.equals("list")
                || firstWord.equals("deadline")
                || firstWord.equals("event")
                || firstWord.equals("done")
                || firstWord.equals("bye")
                || firstWord.equals("delete")
                || firstWord.equals("find");
    }

    private void checkTodo() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "description", subStrings[0]));
        }
    }

    private void checkDelete() {
        checkValidRange();
    }

    private void checkDone() {
        checkValidRange();
    }

    private void checkDeadline() {
        checkKeywordExists("The keyword '/by'", "/by");
        String[] subStringsByKeyword = command.split("/by");

        if (subStringsByKeyword[0].substring(8).trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "The description", subStrings[0]));
        }

        try {
            dateFormat.parse(subStringsByKeyword[1].trim());
        } catch (ParseException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "date"));
        }
    }

    private void checkEvent() {
        checkKeywordExists("The keyword '/at'", "/at");
        String[] subStringsByKeyword = command.split("/at");

        if (subStringsByKeyword[0].substring(5).trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "description", subStrings[0]));
        }
        try {
            dateFormat.parse(subStringsByKeyword[1].trim());
        } catch (ParseException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "date"));
        }
    }

    private void checkKeywordExists(String missing, String keyword) {
        if (!command.contains(keyword)) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, missing, subStrings[0]));
        }
    }

    private void checkValidRange() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "Index", subStrings[0]));
        }
        checkValidNumber();
    }

    private void checkValidNumber() {
        try {
            Integer.parseInt(subStrings[1]);
        } catch (NumberFormatException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "index: " + subStrings[0]));
        }
    }

    private void checkFind() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "The search keyword", subStrings[0] + " command"));
        }
    }
}
