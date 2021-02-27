package duke.commands;

import duke.data.exceptions.DukeException;

import static duke.common.Messages.MESSAGE_ERROR_EMPTY_TASK_NUMBER;

public class Utils {
    /**
     * Parses an argument value after a given token.
     * Reads until a next token or the end of commandArgs string.
     * If token is null, read from the start of the commandArgs string.
     * If stopAtToken is null, read until the end of commandArgs string.
     *
     * @param commandArgs a full string of command arguments
     * @param token a string representing an option portion of an argument e.g. "/by"
     * @param stopAtToken read until this token
     * @return the argument value after the given token
     */
    public static String parseArgument(String commandArgs, String token, String stopAtToken) {
        int readFromIndex = 0;
        int readUntilIndex = commandArgs.length();
        boolean hasToken = (token != null) && (commandArgs.contains(token));
        boolean hasMissingToken = (token != null) && !(commandArgs.contains(token));

        if (hasToken) {
            readFromIndex = commandArgs.indexOf(token) + token.length();
        } else if (hasMissingToken) {
            return null;
        }
        if (stopAtToken != null) {
            int result = commandArgs.indexOf(stopAtToken, readFromIndex);
            if (result != -1) {
                readUntilIndex = result;
            }
        }
        return commandArgs.substring(readFromIndex, readUntilIndex).trim();
    }

    /**
     * Returns an in parsed from the commandArgs string.
     *
     * @param commandArgs a full string of command arguments.
     * @return an integer parsed from the commandArgs string.
     * @throws DukeException If command argument value is empty.
     * @throws NumberFormatException If command argument value is not an integer.
     */
    public static int parseNumberFromArgument(String commandArgs) throws DukeException, NumberFormatException {
        String argValue = parseArgument(commandArgs, null, null);
        if (argValue == null) {
            throw new DukeException(MESSAGE_ERROR_EMPTY_TASK_NUMBER);
        }
        int number = Integer.parseInt(argValue);

        return number;
    }

    /**
     * Checks if argument value string is empty.
     *
     * @param argValue an argument value of a command argument
     * @return true if null or empty, false if not empty.
     */
    public static boolean isArgumentValueEmpty(String argValue) {
        boolean isNull = (argValue == null);
        boolean isEmpty = isNull || (argValue.length() == 0);
        return isEmpty;
    }
}
