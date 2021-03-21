package dukchess.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukchess.entity.TaskList;
import dukchess.storage.Storage;
import dukchess.ui.Ui;

public final class Parser {

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
     * Executes a given raw input from stdin.
     * @param input - raw input string from stdin
     * @return whether to exit from the interaction loop
     */
    public static boolean execute(String input) {
        boolean toExit = false;
        Pattern commandPattern = Pattern.compile("(\\w+)( .+)?");
        Matcher commandMatches = commandPattern.matcher(input);
        if (!commandMatches.matches()) {
            Ui.printErrorMessage("Oof, invalid command :(");
            Ui.promptInputAgain();
            return toExit;
        }
        String nextCommand = commandMatches.group(1);
        String commandArgs = getValueOrDefault(commandMatches.group(2), "");
        commandArgs = commandArgs.trim();

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
            Ui.printErrorMessage("Invalid command :(");
            break;
        }
        Storage.saveData(TaskList.getTasksList());
        if (!toExit) {
            Ui.promptInputAgain();
        }
        return toExit;
    }
}
