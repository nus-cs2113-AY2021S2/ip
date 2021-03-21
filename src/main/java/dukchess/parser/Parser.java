package dukchess.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukchess.entity.TaskList;
import dukchess.exceptions.InvalidCommandException;
import dukchess.storage.Storage;
import dukchess.ui.Ui;

public final class Parser {
    private static final int NEXT_COMMAND_INDEX = 0;
    private static final int COMMAND_ARGS_INDEX = 1;

    /**
     * Source: https://stackoverflow.com/questions/31412294/java-check-not-null-empty-else-assign-default-value
     * Get value of object, and if it is null, set it to the default value.
     * @param defaultValue - the default value to set the variable to
     * @return
     */
    private static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    private static List<String> parseInput(String input) throws InvalidCommandException {
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

    /**
     * Executes a given raw input from stdin.
     * @param input - raw input string from stdin
     * @return whether to exit from the interaction loop
     */
    public static boolean execute(String input) {
        try {
            boolean toExit = false;

            List<String> parsedInput = parseInput(input);
            String nextCommand = parsedInput.get(NEXT_COMMAND_INDEX);
            String commandArgs = parsedInput.get(COMMAND_ARGS_INDEX);

            switch (nextCommand) {
            case "bye":
                toExit = true;
                break;
            case "list":
                TaskList.printAddedTasks();
                break;
            case "done":
                TaskList.handleDoneTask(commandArgs);
                break;
            case "todo":
                TaskList.handleAddTodo(commandArgs);
                break;
            case "deadline":
                TaskList.handleAddDeadline(commandArgs);
                break;
            case "event":
                TaskList.handleAddEvent(commandArgs);
                break;
            case "delete":
                TaskList.handleDeleteTask(commandArgs);
                break;
            case "find":
                TaskList.handleFindTask(commandArgs);
                break;
            default:
                throw new InvalidCommandException();
            }
            Storage.saveData(TaskList.getTasksList());
            if (!toExit) {
                Ui.promptInputAgain();
            }
            return toExit;
        } catch (InvalidCommandException e) {
            Ui.printErrorMessage("Invalid command :(");
            Ui.promptInputAgain();
            return false;
        }
    }
}
