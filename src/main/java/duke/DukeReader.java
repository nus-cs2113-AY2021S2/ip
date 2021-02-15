package duke;

import java.util.ArrayList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DukeReader {
    private static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Task> readFromFile(String directory, String filename) throws IOException {
        File inputDirectory = new File(directory);
        if (!inputDirectory.exists()) {
            inputDirectory.mkdir();
        }
        File inputFile = new File(directory + filename);
        if (!inputFile.exists()) {
            /*
            * TODO: Add message to tell user that we are importing the data
            */
            inputFile.createNewFile();
        }
        Scanner inputReader = new Scanner(inputFile);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (inputReader.hasNext()) {
            String taskEntry = inputReader.nextLine();
            String[] taskArguments = taskEntry.split(",");
            if (taskArguments.length == 0) {
                continue;
            }
            switch (taskArguments[0]) {
            case "todo":
                /*
                 * TODO: Add error handling while importing tasks (For now, assume that all input is valid)
                 */
                if (taskArguments.length < 3) {
                    break;
                }
                Todo todo = new Todo(taskArguments[1]);
                tasks.add(todo);
                break;
            case "deadline":
                if (taskArguments.length < 4) {
                    break;
                }
                Deadline deadline = new Deadline(taskArguments[1], taskArguments[3]);
                tasks.add(deadline);
                break;
            case "event":
                if (taskArguments.length < 4) {
                    break;
                }
                Event event = new Event(taskArguments[1], taskArguments[3]);
                tasks.add(event);
                break;
            default:
                /*
                 * TODO: Handle invalid inputs
                 */
                break;
            }
            boolean isDone = Boolean.parseBoolean(taskArguments[2]);
            if (isDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    private static ArrayList<String> tokenizeInput(String userCommand) {
        String[] commandTokens = userCommand.split(" ", 2);
        /* Handle empty inputs */
        if (commandTokens.length == 0) {
            return new ArrayList<String>();
        }
        /* Make commands case-insensitive */
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
            /*
             * For commands that do not require further tokenization
             * e.g. "todo", "list", "bye"
             */
            for (int i = 1; i < commandTokens.length; i++) {
                commandParsed.add(commandTokens[i]);
            }
            break;
        }
        /* Remove any trailing whitespace from the tokens */
        for (int i = 0; i < commandParsed.size(); i++) {
            commandParsed.set(i, commandParsed.get(i).trim());
        }
        return commandParsed;
    }

    public static ArrayList<String> readUserInput() {
        String userInput = scanner.nextLine();
        ArrayList<String> userInputTokenized = tokenizeInput(userInput);
        return userInputTokenized;
    }
}
