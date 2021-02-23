package command.parser;

import ui.UI;
import task.list.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a parser that deals with all commands
 */
public class Parser {

    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String HELP_COMMAND = "help";

    // reads command that the user has inputted
    public static void processCommands(ArrayList<TaskList> tasks) {
        String line;
        Scanner in = new Scanner(System.in);
        boolean hasToContinue = true;
        UI.printHelp();
        while (hasToContinue) {
            line = in.nextLine();
            hasToContinue = selectCommand(line, tasks);
        }
    }

    // selects the appropriate command based on user input
    public static boolean selectCommand(String line, ArrayList<TaskList> tasks) {
        switch (line) {
        case EXIT_COMMAND:
            ExitCommand.exit(tasks);
            return false;
        case LIST_COMMAND:
            CommandsHandler.handleList(tasks);
            break;
        case HELP_COMMAND:
            UI.printHelp();
            break;
        default:
            AmendListsCommands.amendList(line, tasks);
            break;
        }
        return true;
    }
}