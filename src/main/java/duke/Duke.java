package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;

import javax.swing.*;
import java.util.logging.FileHandler;

public class Duke {

    private static final String dataFilePath = "data/data.txt";

    private static UserInterface ui = new UserInterface();
    private static TaskManager taskManager = new TaskManager();
    private static DataManager dataManager = new DataManager(dataFilePath);

    public static void main(String[] args) {

        ui.showWelcomeMessage();

        taskManager.setData(dataManager.loadData());

        while (true) {
            String input = ui.getUserInput();
            String feedback = executeCommand(input);
            dataManager.saveData(taskManager.getData());
            ui.printFeedback(feedback);
        }
    }

    private static String executeCommand(String input) {
        String[] parsedInput = parseInput(input);
        String command = parsedInput[0].toLowerCase();
        String feedback = null;

        switch (command) {
        case "bye":
            ui.showExitMessage();
            System.exit(0);
            break;
        case "list":
            feedback = taskManager.listTask();
            break;
        case "done":
            try {
                int taskNumber = Integer.parseInt(parsedInput[1]) - 1;
                feedback = taskManager.doneTask(taskNumber);
            } catch (NumberFormatException e) {
                feedback = "OOPS!!! Task number has to be a number";
            }
            break;
        default:
            feedback = taskManager.addTask(command, parsedInput[1]);
        }

        return feedback;
    }

    public static String[] parseInput(String input) {
        String[] split = input.split(" ", 2);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }
}
