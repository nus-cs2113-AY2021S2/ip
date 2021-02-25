package parser;

import exceptions.MissingInfoException;
import exceptions.UnknownCommandException;
import exceptions.UnknownFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandVerifier {

    private static final String MISSING_INFO_MESSAGE =
            "\uD83D\uDE2D %s of a %s cannot be empty.";
    private static final String UNKNOWN_COMMAND_MESSAGE =
            "\uD83D\uDE2D Sorry mate I do not understand your request. Please specify task";
    private static final String UNKNOWN_FORMAT_MESSAGE =
            "\uD83D\uDE2D Format for %s is invalid!";

    private String[] subStrings;
    private String command;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public void verify(String command) throws UnknownCommandException, MissingInfoException {

        this.command = command;
        subStrings = command.split(" ");
        if (!isKnownCommand(subStrings[0])) {
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
        default:
            break;
        }
    }

    private boolean isKnownCommand(String firstWord) {
        return firstWord.equals("todo")
                || firstWord.equals("list")
                || firstWord.equals("deadline")
                || firstWord.equals("event")
                || firstWord.equals("done")
                || firstWord.equals("bye")
                || firstWord.equals("delete");
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
        checkFlagExists("by", "/by");
        String[] subStringsByFlag = command.split("/by");

        if (subStringsByFlag.length < 2 || subStringsByFlag[1].trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "by", subStrings[0]));
        }

        if (subStringsByFlag[0].substring(8).trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "description", subStrings[0]));
        }

        try {
            dateFormat.parse(subStringsByFlag[1].trim());
        } catch (ParseException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "date"));
        }
    }

    private void checkEvent() {
        checkFlagExists("at", "/at");
        String[] subStringsByFlag = command.split("/at");
        if (subStringsByFlag.length < 2 || subStringsByFlag[1].trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "at", subStrings[0]));
        }
        if (subStringsByFlag[0].substring(5).trim().equals("")) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "description", subStrings[0]));
        }
        try {
            dateFormat.parse(subStringsByFlag[1].trim());
        } catch (ParseException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "date"));
        }
    }

    private void checkFlagExists(String infoMissing, String flag) {
        if (!command.contains(flag)) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, infoMissing, subStrings[0]));
        }
    }

    private void checkValidRange() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "number", subStrings[0]));
        }
        checkValidNumber();
    }

    private void checkValidNumber() {
        try {
            Integer.parseInt(subStrings[1]);
        } catch (NumberFormatException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, subStrings[0]));
        }
    }
}
