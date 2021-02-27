import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUi;
import duke.util.Util;
import duke.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a smart bot that creates a task
 * list which includes todos, deadlines and events
 */
public class Duke {
    private void run(){
        String taskType;
        String taskName;
        ArrayList<String> parsedList;

        TextUi.showLogo();
        TextUi.showDividingLine();

        // Print welcome message
        TextUi.showDividingLine();
        TextUi.showWelcomeMessage();
        TextUi.showDividingLine();

        // Load up save file, create file if it's not been created
        Storage.loadFile();
        TextUi.showDividingLine();

        // Scan for input
        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();

        parsedList = Parser.parseInput(userInput);
        taskType = Parser.parseTaskType(parsedList);
        taskName = Parser.parseTaskName(parsedList);

        processCommands(taskType, taskName, userInput);

        // Print Bye Message
        TextUi.showDividingLine();
        TextUi.showByeMessage();
        TextUi.showDividingLine();
    }

    private void processCommands(String taskType, String taskName, String userInput) {
        Scanner userInputScanner = new Scanner(System.in);
        String byTime;
        String atTime;
        ArrayList<String> parsedList = new ArrayList<>();
        // Loop for user input until "bye" is inputted
        while(!Util.isBye(taskType)) {
            switch (taskType) {
            case "todo":
                Task t = new Todo(taskName);
                TextUi.showDividingLine();
                Task.addTaskWithValidation(userInput, t);
                TextUi.showDividingLine();
                Storage.attemptSaveFile();
                break;
            case "deadline":
                byTime = Util.extractTime(taskName);
                taskName = Util.extractTaskName(taskName);
                Task d = new Deadline(taskName, byTime);
                TextUi.showDividingLine();
                Task.addTaskWithValidation(userInput, d);
                TextUi.showDividingLine();
                Storage.attemptSaveFile();
                break;
            case "event":
                atTime = Util.extractTime(taskName);
                taskName = Util.extractTaskName(taskName);
                Task e = new Event(taskName, atTime);
                TextUi.showDividingLine();
                Task.addTaskWithValidation(userInput, e);
                TextUi.showDividingLine();
                Storage.attemptSaveFile();
                break;
            case "list":
                TextUi.showDividingLine();
                Task.listTasks();
                TextUi.showDividingLine();
                break;
            case "delete":
                TextUi.showDividingLine();
                Task.deleteTask(Util.getTaskIndex(userInput));
                TextUi.showDividingLine();
                Storage.attemptSaveFile();
                break;
            case "done":
                TextUi.showDividingLine();
                Task.markAsDoneWithValidation(Util.getTaskIndex(userInput), userInput);
                TextUi.showDividingLine();
                Storage.attemptSaveFile();
                break;
            case "find":
                TextUi.showDividingLine();
                Task.findTaskWithValidation(taskName);
                TextUi.showDividingLine();
                break;
            default:
                TextUi.showDividingLine();
                TextUi.showUnrecognizedCommandError();
                TextUi.showDividingLine();
                break;
            }

            // Scan input again
            userInput = userInputScanner.nextLine();
            Parser.emptyList(parsedList);
            parsedList = Parser.parseInput(userInput);
            taskType = Parser.parseTaskType(parsedList);
            taskName = Parser.parseTaskName(parsedList);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
