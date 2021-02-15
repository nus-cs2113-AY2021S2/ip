package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class DukeReader {
    private static Scanner scanner = new Scanner(System.in);

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
