package duke;

import java.util.Scanner;

public class DukeReader {
    private static Scanner scanner = new Scanner(System.in);

    private static String[] tokenizeInput(String userCommand) {
        String[] commandTokens = userCommand.split(" ", 2);
        /* Handle empty inputs */
        if (commandTokens.length == 0) {
            return commandTokens;
        }
        /* Make commands case-insensitive */
        commandTokens[0] = commandTokens[0].toLowerCase();
        String[] commandParsed = commandTokens;
        switch (commandTokens[0]) {
        case DukeCommands.DEADLINE_COMMAND:
            if (commandTokens.length < 2) {
                break;
            }
            String[] commandSplitDeadline = commandTokens[1].split(DukeCommands.DEADLINE_DELIMITER);
            commandParsed = new String[commandSplitDeadline.length + 1];
            commandParsed[0] = commandTokens[0];
            for (int i = 0; i < commandSplitDeadline.length; i++) {
                commandParsed[i + 1] = commandSplitDeadline[i];
            }
            break;
        case DukeCommands.EVENT_COMMAND:
            if (commandTokens.length < 2) {
                break;
            }
            String[] commandSplitEvent = commandTokens[1].split(DukeCommands.EVENT_DELIMITER);
            commandParsed = new String[commandSplitEvent.length + 1];
            commandParsed[0] = commandTokens[0];
            for (int i = 0; i < commandSplitEvent.length; i++) {
                commandParsed[i + 1] = commandSplitEvent[i];
            }
            break;
        default:
            /*
             * For commands that do not require further tokenization
             * e.g. "todo", "list", "bye"
             */
            break;
        }
        /* Remove any trailing whitespace from the tokens */
        for (int i = 0; i < commandParsed.length; i++) {
            commandParsed[i] = commandParsed[i].trim();
        }
        return commandParsed;
    }

    public static String[] readUserInput() {
        String userInput = scanner.nextLine();
        String[] userInputTokenized = tokenizeInput(userInput);
        return userInputTokenized;
    }
}
