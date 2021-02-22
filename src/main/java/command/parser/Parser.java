package command.parser;

import ui.UI;
import task.list.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

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

    public static boolean selectCommand(String line, ArrayList<TaskList> tasks) {
        switch (line) {
        case "bye":
            ExitCommand.exit(tasks);
            return false;
        case "list":
            CommandsHandler.handleList(tasks);
            break;
        case "help":
            UI.printHelp();
            break;
        default:
            AmendListsCommands.amendList(line, tasks);
            break;
        }
        return true;
    }
}