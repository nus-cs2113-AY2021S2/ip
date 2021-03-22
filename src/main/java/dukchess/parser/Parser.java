package dukchess.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukchess.exceptions.InvalidCommandException;

public final class Parser {
    public static final int NEXT_COMMAND_INDEX = 0;
    public static final int COMMAND_ARGS_INDEX = 1;

    /**
     * Source: https://stackoverflow.com/questions/31412294/java-check-not-null-empty-else-assign-default-value
     * Get value of object, and if it is null, set it to the default value.
     * @param defaultValue - the default value to set the variable to
     * @return
     */
    private static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * Parses the input by a user into the command to execute, and the arguments for that command.
     * @param input
     * @return nextCommand - the command to execute, commandArgs - the arguments for the command
     * @throws InvalidCommandException
     */
    public static List<String> parseInput(String input) throws InvalidCommandException {
        Pattern commandPattern = Pattern.compile("(\\w+)( .+)?");
        Matcher commandMatches = commandPattern.matcher(input);
        if (!commandMatches.matches()) {
            throw new InvalidCommandException();
        }
        String nextCommand = commandMatches.group(1);
        String commandArgs = getValueOrDefault(commandMatches.group(2), "");
        commandArgs = commandArgs.trim();

        return new ArrayList<>(Arrays.asList(nextCommand, commandArgs));
    }
}
