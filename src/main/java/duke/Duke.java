package duke;

import duke.exception.IllegalTaskCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void executeCommand(String command, String userInput) throws IOException {
        String errand;
        String timestamp;
        String timestampHeader;

        try {
            switch (command) {
            case "TODO":
                errand = Parser.getSubstring(userInput);
                Task todo = new Todo(errand);
                TaskList.addToTaskList(todo);
                break;
            case "EVENT":
                errand = Parser.getErrand(userInput);
                timestampHeader = Parser.getTimestampHeader(userInput);
                timestamp = Parser.getTimestamp(userInput);
                Task event = new Event(errand, timestamp, timestampHeader);
                TaskList.addToTaskList(event);
                break;
            case "DEADLINE":
                errand = Parser.getErrand(userInput);
                timestampHeader = Parser.getTimestampHeader(userInput);
                timestamp = Parser.getTimestamp(userInput);
                Task deadline = new Deadline(errand, timestamp, timestampHeader);
                TaskList.addToTaskList(deadline);
                break;
            case "LIST":
                TaskList.printList();
                break;
            case "DONE":
                errand = Parser.getSubstring(userInput);
                int index = Validator.validateIndex(errand);
                if (index == -1) {
                    throw new IllegalTaskCommandException("There is no such task, Commander!");
                } else if (index == -2) {
                    throw new IllegalTaskCommandException("Only numbers are allowed commander!");
                } else {
                    TaskList.markDone(index);
                }
                break;
            case "BYE":
                Ui.printGoodbyeMessage();
                break;
            case "DELETE":
                errand = Parser.getSubstring(userInput);
                index = Validator.validateIndex(errand);
                if (index == -1) {
                    throw new IllegalTaskCommandException("There is no such task, Commander!");
                } else if (index == -2) {
                    throw new IllegalTaskCommandException("Only numbers are allowed commander!");
                } else {
                    TaskList.deleteTask(index);
                }
                break;
            default:
                throw new IllegalTaskCommandException("Unacceptable Command!");
            }
        } catch (IllegalTaskCommandException e) {
            Ui.printErrorLogo();
            System.out.println(e.getMessage());
        }

        Storage.saveTasks();
    }

    /**
     * Starts the Task Manager program.
     * <p>Runs an infinite loop until "BYE" is called</p>
     */
    private static void runTaskManager() throws IOException {
        String userInput;
        String command;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            command = Parser.getCommand(userInput);
            executeCommand(command, userInput);
        } while (!command.equals("BYE"));
    }

    public static void main(String[] args) throws IllegalTaskCommandException, IOException {
        Ui.initialiseWelcomeMessage();
        //Storage.loadTasks();
        runTaskManager();
    }
}
