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

        showLogo();

        showWelcomeMessage();

        Storage.loadFile();
        TextUi.showDividingLine();

        // Scan for input
        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();

        parsedList = Parser.parseInput(userInput);
        taskType = Parser.parseTaskType(parsedList);
        taskName = Parser.parseTaskName(parsedList);

        processCommands(taskType, taskName, userInput);

        showByeMessage();
    }

    private void processCommands(String taskType, String taskName, String userInput) {
        Scanner userInputScanner = new Scanner(System.in);
        ArrayList<String> parsedList = new ArrayList<>();

        // Loop for user input until "bye" is inputted
        while(!Util.isBye(taskType)) {
            switch (taskType) {
            case "todo":
                addTodo(taskName, userInput);
                Storage.attemptSaveFile();
                break;
            case "deadline":
                addDeadline(taskName, userInput);
                Storage.attemptSaveFile();
                break;
            case "event":
                addEvent(taskName, userInput);
                Storage.attemptSaveFile();
                break;
            case "list":
                list();
                break;
            case "delete":
                delete(userInput);
                Storage.attemptSaveFile();
                break;
            case "done":
                done(userInput);
                Storage.attemptSaveFile();
                break;
            case "find":
                find(taskName);
                break;
            default:
                showErrorMessage();
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

    private void showLogo() {
        TextUi.showLogo();
        TextUi.showDividingLine();
    }

    private void showWelcomeMessage() {
        TextUi.showDividingLine();
        TextUi.showWelcomeMessage();
        TextUi.showDividingLine();
    }

    private void showByeMessage() {
        TextUi.showDividingLine();
        TextUi.showByeMessage();
        TextUi.showDividingLine();
    }

    private void showErrorMessage() {
        TextUi.showDividingLine();
        TextUi.showUnrecognizedCommandError();
        TextUi.showDividingLine();
    }

    private void find(String taskName) {
        TextUi.showDividingLine();
        Task.findTaskWithValidation(taskName);
        TextUi.showDividingLine();
    }

    private void done(String userInput) {
        TextUi.showDividingLine();
        Task.markAsDoneWithValidation(Util.getTaskIndex(userInput), userInput);
        TextUi.showDividingLine();
    }

    private void delete(String userInput) {
        TextUi.showDividingLine();
        Task.deleteTaskWithValidation(Util.getTaskIndex(userInput), userInput);
        TextUi.showDividingLine();
    }

    private void list() {
        TextUi.showDividingLine();
        Task.listTasks();
        TextUi.showDividingLine();
    }

    private void addEvent(String taskName, String userInput) {
        String atTime;
        atTime = Util.extractTime(taskName);
        taskName = Util.extractTaskName(taskName);
        Task e = new Event(taskName, atTime);
        TextUi.showDividingLine();
        Task.addTaskWithValidation(userInput, e);
        TextUi.showDividingLine();
    }

    private void addDeadline(String taskName, String userInput) {
        String byTime;
        byTime = Util.extractTime(taskName);
        taskName = Util.extractTaskName(taskName);
        Task d = new Deadline(taskName, byTime);
        TextUi.showDividingLine();
        Task.addTaskWithValidation(userInput, d);
        TextUi.showDividingLine();
    }

    private void addTodo(String taskName, String userInput) {
        Task t = new Todo(taskName);
        TextUi.showDividingLine();
        Task.addTaskWithValidation(userInput, t);
        TextUi.showDividingLine();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
