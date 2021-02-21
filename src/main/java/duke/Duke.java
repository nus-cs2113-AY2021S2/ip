package duke;

import duke.data.DataManager;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.parser.CommandParser;
import duke.task.Task;
import duke.task.TaskManager;
import duke.user_interface.UserInterface;

import java.util.ArrayList;

/**
 * The Duke program implements an application
 * that allow users to keep track of their task
 *
 * @author Jalvin Chan Jia Xiang
 * @version 0.2
 * @since 16 Jan 2021
 */

public class Duke {

    private static final String DATA_FILE_PATH = "data/data.txt";

    private static UserInterface ui;
    private static TaskManager taskManager;
    private static DataManager dataManager;
    private static CommandParser parser;

    public static void main(String[] args) {

        ui = new UserInterface();
        taskManager = new TaskManager();
        dataManager = new DataManager(DATA_FILE_PATH);
        parser = new CommandParser();

        ui.showWelcomeMessage();

        ArrayList<Task> taskList = dataManager.loadData();
        taskManager.setData(taskList);

        while (true) {
            String input = ui.getUserInput();
            String feedback;
            try {
                feedback = executeCommand(input);
            } catch (InvalidCommandException e) {
                feedback = "OOPS!!! I'm sorry, but I don't know what that means :(";
            }
            ui.printFeedback(feedback);
            ArrayList<Task> data = taskManager.getData();
            dataManager.saveData(data);
        }
    }

    private static String executeCommand(String input) throws InvalidCommandException {

        String[] parsedInput = parser.parseCommand(input);

        String command = parsedInput[0];
        String description = parsedInput[1];

        String feedback = "";

        switch (command) {
        case "bye":
            exitProgram();
            break;
        case "list":
            feedback = listTask();
            break;
        case "done":
            feedback = setTaskAsDone(description);
            break;
        case "delete":
            feedback = deleteTask(description);
            break;
        case "todo":
            feedback = addTodo(description);
            break;
        case "deadline":
            feedback = addDeadline(description);
            break;
        case "event":
            feedback = addEvent(description);
            break;
        default:
            throw new InvalidCommandException();
        }

        return feedback;
    }

    private static String listTask() {
        return taskManager.listTask();
    }

    private static String setTaskAsDone(String description) {
        String feedback;
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            feedback = taskManager.doneTask(taskNumber);
        } catch (NumberFormatException e) {
            feedback = "OOPS!!! Task number has to be a number";
        }
        return feedback;
    }

    private static String deleteTask(String description) {
        String feedback;
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            feedback = taskManager.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            feedback = "OOPS!!! Task number has to be a number";
        }
        return feedback;
    }

    private static String addTodo(String description) {
        String feedback;
        try {
            feedback = taskManager.addTodo(description);
        } catch (EmptyDescriptionException e) {
            feedback = "OOPS!!! The description of a todo cannot be empty.";
        }
        return feedback;
    }

    private static String addDeadline(String description) {
        String feedback;
        try {
            feedback = taskManager.addDeadline(description);
        } catch (EmptyDescriptionException e) {
            feedback = "OOPS!!! The description of a deadline cannot be empty.";
        }
        return feedback;
    }

    private static String addEvent(String description) {
        String feedback;
        try {
            feedback = taskManager.addEvent(description);
        } catch (EmptyDescriptionException e) {
            feedback = "OOPS!!! The description of an event cannot be empty.";
        }
        return feedback;
    }

    private static void exitProgram() {
        ui.showExitMessage();
        System.exit(0);
    }
}
