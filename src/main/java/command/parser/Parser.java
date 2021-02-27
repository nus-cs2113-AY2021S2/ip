package command.parser;

import ui.UI;
import task.list.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a parser that deals with all commands
 */
public class Parser {

    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String HELP_COMMAND = "help";

    /**
     * reads command that the user has inputted
     *
     * @param tasks is the list of tasks
     */
    public static void processCommands(ArrayList<Task> tasks) {
        String line;
        Scanner in = new Scanner(System.in);
        boolean hasToContinue = true;
        UI.printHelp();
        while (hasToContinue) {
            line = in.nextLine();
            hasToContinue = selectCommand(line, tasks);
        }
    }

    /**
     * selects the appropriate command based on user input
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     * @return whether user wants to continue to use the program
     */
    public static boolean selectCommand(String line, ArrayList<Task> tasks) {
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