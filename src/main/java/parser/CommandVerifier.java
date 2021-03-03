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

    /**
     * Verifies if the command is legal
     *
     * @param command String Command to be verified
     * @throws UnknownCommandException
     * @throws MissingInfoException
     */
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

    /**
     * Checks if the command is a valid command
     *
     * @param firstWord First Word of the Command
     * @return boolean whether the command is valid
     */
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

    /**
     * Checks if the Todo description is missing
     */
    private void checkTodo() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "description", subStrings[0]));
        }
    }

    /**
     * Checks if the index specified in the Delete command is out of range
     */
    private void checkDelete() {
        checkValidRange();
    }

    /**
     * Checks if the index specified in the Done command is out of range
     */
    private void checkDone() {
        checkValidRange();
    }

    /**
     * Checks if the description or the keyword is missing from the Deadline command
     */
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

    /**
     * Checks if the description or the keyword is missing from the Event command
     */
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

    /**
     * Checks if the keyword exists
     *
     * @param missing String of the missing keyword
     * @param keyword String of they keyword to check
     */
    private void checkKeywordExists(String missing, String keyword) {
        if (!command.contains(keyword)) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, missing, subStrings[0]));
        }
    }

    /**
     * Checks whether index is out of range
     */
    private void checkValidRange() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "Index", subStrings[0]));
        }
        checkValidNumber();
    }

    /**
     * Checks whether the input is a valid number
     */
    private void checkValidNumber() {
        try {
            Integer.parseInt(subStrings[1]);
        } catch (NumberFormatException e) {
            throw new UnknownFormatException(
                    String.format(UNKNOWN_FORMAT_MESSAGE, "index: " + subStrings[0]));
        }
    }

    /**
     * Checks whether the description is missing from the Find Command
     */
    private void checkFind() {
        if (subStrings.length < 2) {
            throw new MissingInfoException(
                    String.format(MISSING_INFO_MESSAGE, "The search keyword", subStrings[0] + " command"));
        }
    }
}
