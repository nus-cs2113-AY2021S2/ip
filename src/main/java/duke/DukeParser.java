package duke;

import duke.task.Task;

import java.util.ArrayList;

import java.util.Scanner;

public class DukeParser {
    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<String> tokenizeInput(String userCommand) {
        String[] commandTokens = userCommand.split(" ", 2);

        if (commandTokens.length == 0) {
            return new ArrayList<String>();
        }

        commandTokens[0] = commandTokens[0].toLowerCase();
        ArrayList<String> commandParsed = new ArrayList<String>();
        commandParsed.add(commandTokens[0]);

        switch (commandTokens[0]) {
        case DukeCommands.DEADLINE_COMMAND:
            if (commandTokens.length < 2) {
                break;
            }
            String[] commandSplitDeadline = commandTokens[1].split(DukeCommands.DEADLINE_DELIMITER, 2);
            for (String argument : commandSplitDeadline) {
                commandParsed.add(argument);
            }
            break;
        case DukeCommands.EVENT_COMMAND:
            if (commandTokens.length < 2) {
                break;
            }
            String[] commandSplitEvent = commandTokens[1].split(DukeCommands.EVENT_DELIMITER, 2);
            for (String argument : commandSplitEvent) {
                commandParsed.add(argument);
            }
            break;
        default:
            for (int i = 1; i < commandTokens.length; i++) {
                commandParsed.add(commandTokens[i]);
            }
            break;
        }

        for (int i = 0; i < commandParsed.size(); i++) {
            String trimmedArg = commandParsed.get(i);
            trimmedArg = trimmedArg.trim();
            commandParsed.set(i, trimmedArg);
        }

        return commandParsed;
    }

    public static ArrayList<String> readUserInput() {
        String userInput = scanner.nextLine();
        ArrayList<String> userInputTokenized = tokenizeInput(userInput);
        return userInputTokenized;
    }

    public static ArrayList<ArrayList<String>> parseTaskInfo(ArrayList<String> taskInfo) {
        ArrayList<ArrayList<String>> parsedTaskInfo = new ArrayList<ArrayList<String>>();
        for (String taskString : taskInfo) {
            ArrayList<String> parsedTaskString = new ArrayList<String>();
            String[] tokenizedTaskString = taskString.split(",");
            for (String token : tokenizedTaskString) {
                parsedTaskString.add(token.trim());
            }
            parsedTaskInfo.add(parsedTaskString);
        }
        return parsedTaskInfo;
    }

    public static boolean executeCommand(
            DukeTaskList dukeTaskList, ArrayList<String> commandTokens)
            throws DukeException {
        if (commandTokens.isEmpty()) {
            throw new DukeException("Please enter a command.\n"
                    + "Try using \"help\" for a list of commands."
            );
        }
        boolean isDoneReadingInputs = false;
        Task task = null;
        switch (commandTokens.get(0)) {
        case DukeCommands.BYE_COMMAND:
            isDoneReadingInputs = true;
            break;
        case DukeCommands.CLEAR_COMMAND:
            dukeTaskList.clearTasks();
            break;
        case DukeCommands.LIST_COMMAND:
            dukeTaskList.printTasks();
            break;
        case DukeCommands.HELP_COMMAND:
            DukePrinter.printHelpMessage();
            break;
        case DukeCommands.DONE_COMMAND:
            dukeTaskList.markTaskAsDone(commandTokens);
            break;
        case DukeCommands.TODO_COMMAND:
            task = dukeTaskList.addTodo(commandTokens);
            DukePrinter.printTaskAdded(task, dukeTaskList.getNumberOfTasks());
            break;
        case DukeCommands.DEADLINE_COMMAND:
            task = dukeTaskList.addDeadline(commandTokens);
            DukePrinter.printTaskAdded(task, dukeTaskList.getNumberOfTasks());
            break;
        case DukeCommands.EVENT_COMMAND:
            task = dukeTaskList.addEvent(commandTokens);
            DukePrinter.printTaskAdded(task, dukeTaskList.getNumberOfTasks());
            break;
        case DukeCommands.DELETE_COMMAND:
            dukeTaskList.deleteTask(commandTokens);
            break;
        default:
            DukePrinter.printFallbackMessage();
            break;
        }
        return isDoneReadingInputs;
    }
}
