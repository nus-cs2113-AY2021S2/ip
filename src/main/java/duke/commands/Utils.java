package duke.commands;

import duke.data.exceptions.DukeException;

import static duke.common.Messages.ERROR_EMPTY_TASK_NUMBER_MESSAGE;

public class Utils {
    /**
     * Parses an argument value after a given token.
     * Reads until a next token or the end of commandArgs string.
     * If token is null, read from the start of the string until
     * a next token or the end of commandArgs string.
     *
     * @param commandArgs a full string of command arguments
     * @param token a string representing an option portion of an argument e.g. "/by"
     * @return the argument value after the given token
     */
    public static String parseArgument(String commandArgs, String token) {
        int readFromIndex = 0;
        int readUntilIndex = commandArgs.length();
        boolean hasToken = (token != null) && (commandArgs.contains(token));
        boolean hasMissingToken = (token != null) && !(commandArgs.contains(token));
        if (hasToken) {
            readFromIndex = commandArgs.indexOf(token) + token.length();
        } else if (hasMissingToken) {
            return null;
        }
        int result = commandArgs.indexOf("/", readFromIndex);
        if (result != -1) {
            readUntilIndex = result;
        }
        return commandArgs.substring(readFromIndex, readUntilIndex).trim();
    }

    public static int parseNumberFromArgument(String commandArgs) throws DukeException, NumberFormatException {
        String argValue = parseArgument(commandArgs, null);
        if (argValue == null) {
            throw new DukeException(ERROR_EMPTY_TASK_NUMBER_MESSAGE);
        }
        int number = Integer.parseInt(argValue);

        return number;
    }

    /**
     * Checks if argument value string is empty.
     *
     * @param argValue argument value of a command argument
     * @return true if empty, false if not empty
     */
    public static boolean isArgumentValueEmpty(String argValue) {
        return (argValue == null) || (argValue.length() == 0);
    }
}
