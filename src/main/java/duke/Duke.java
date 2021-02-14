package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;

public class Duke {

    private static final UserInterface ui = new UserInterface();
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {

        ui.showWelcomeMessage();

        while (true) {
            String input = ui.getUserInput();
            String feedback = executeCommand(input);
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
        case "delete":
            try {
                int taskNumber = Integer.parseInt(parsedInput[1]) - 1;
                feedback = taskManager.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                feedback = "OOPS!!! Task number has to be a number";
            }
            break;
        default:
            try {
                feedback = taskManager.addTask(command, parsedInput[1]);
            } catch (InvalidCommandException e) {
                feedback = "OOPS!!! I'm sorry, but I don't know what that means :(";
            } catch (EmptyDescriptionException e) {
                feedback = "OOPS!!! The description of a " + command + " cannot be empty.";
            }
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
