package duke.commands;

import duke.exception.IllegalTaskCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

public class CommandHandler {
    public static void executeCommand(String command, String userInput) throws IOException {
        try {
            int index;
            switch (command) {
            case "TODO":
                index = executeTodo(userInput);
                Ui.echoInput(index);
                break;
            case "EVENT":
                index = executeEvent(userInput);
                Ui.echoInput(index);
                break;
            case "DEADLINE":
                index = executeDeadline(userInput);
                Ui.echoInput(index);
                break;
            case "LIST":
                TaskList.printList();
                break;
            case "DONE":
                index = executeMarkDone(userInput);
                Ui.printMarkDonePrompt(index);
                break;
            case "BYE":
                Ui.printGoodbyeMessage();
                break;
            case "DELETE":
                executeDelete(userInput);
                break;
            case "SEARCH":
                executeSearch(userInput);
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

    private static void executeSearch(String userInput) throws IllegalTaskCommandException {
        String keyWord = Parser.getSubstring(userInput);
        TaskList.searchWord(keyWord);
    }

    public static void executeDelete(String userInput) throws IllegalTaskCommandException {
        String errand;
        errand = Parser.getSubstring(userInput);
        int index = Validator.validateIndex(errand);
        if (index == -1) {
            throw new IllegalTaskCommandException("There is no such task, Commander!");
        } else if (index == -2) {
            throw new IllegalTaskCommandException("Only numbers are allowed commander!");
        } else {
            TaskList.deleteTask(index);
        }
    }

    public static int executeMarkDone(String userInput) throws IllegalTaskCommandException {
        String errand;
        errand = Parser.getSubstring(userInput);
        int index = Validator.validateIndex(errand);
        if (index == -1) {
            throw new IllegalTaskCommandException("There is no such task, Commander!");
        } else if (index == -2) {
            throw new IllegalTaskCommandException("Only numbers are allowed commander!");
        } else {
            TaskList.markDone(index);
        }
        return index;
    }

    public static int executeDeadline(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getErrand(userInput);
        String timestampHeader = Parser.getTimestampHeader(userInput);
        String timestamp = Parser.getTimestamp(userInput);
        Task deadline = new Deadline(errand, timestamp, timestampHeader);
        return TaskList.addToTaskList(deadline);
    }

    public static int executeEvent(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getErrand(userInput);
        String timestampHeader = Parser.getTimestampHeader(userInput);
        String timestamp = Parser.getTimestamp(userInput);
        Task event = new Event(errand, timestamp, timestampHeader);
        return TaskList.addToTaskList(event);
    }

    public static int executeTodo(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getSubstring(userInput);
        Task todo = new Todo(errand);
        return TaskList.addToTaskList(todo);
    }

    public static void executeLoadMarkDone(String[] fileInput) throws IllegalTaskCommandException {
        if (fileInput[1].strip().equals("X")) {
            String doneCommand = "DONE " + TaskList.returnTaskCount();
            executeMarkDone(doneCommand);
        }
    }
}
