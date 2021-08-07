package duke.commands;

import duke.exception.IllegalTaskCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Validator;
import duke.ui.Ui;

import java.io.IOException;

public class CommandHandler {
    /**
     * Performs an executable method based on the command given.
     *
     * @param command the "command" segment of the userInput parsed.
     * @param userInput the raw input from the terminal or loaded file.
     * @throws IOException when the file is not saved properly.
     */
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
            System.out.println();
        }

        Storage.saveTasks();
    }

    /**
     * Searches for Task objects in the ArrayList from the keyword provided.
     *
     * @param userInput is the raw input from the console.
     * @throws IllegalTaskCommandException when insufficient parameters are provided.
     */
    private static void executeSearch(String userInput) throws IllegalTaskCommandException {
        String keyWord = Parser.getSubstring(userInput);
        TaskList.searchWord(keyWord);
    }

    /**
     * Deletes the Task object from the ArrayList that is referenced by the index.
     *
     * @param userInput is the raw input from the console.
     * @throws IllegalTaskCommandException when insufficient parameters are provided.
     */
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

    /**
     * Marks the Task object done from the ArrayList that is reference by the index.
     *
     * @param userInput is the input from the loaded file or console.
     * @return the index of the Task object that the method was executed on.
     * @throws IllegalTaskCommandException when insufficient parameters are provided.
     */
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

    /**
     * Adds the Deadline object into the ArrayList.
     *
     * @param userInput is the input from the loaded file or console.
     * @return the index of the Deadline object that is added to the ArrayList.
     * @throws IllegalTaskCommandException when there are missing parameters for the Deadline object.
     */
    public static int executeDeadline(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getErrand(userInput);
        String timestampHeader = Parser.getTimestampHeader(userInput);
        String timestamp = Parser.getTimestamp(userInput);
        Task deadline = new Deadline(errand, timestamp, timestampHeader);
        return TaskList.addToTaskList(deadline);
    }

    /**
     * Adds the Event object into the ArrayList.
     *
     * @param userInput is the input from the loaded file or console.
     * @return the index of the Event object that is added to the ArrayList.
     * @throws IllegalTaskCommandException when there are missing parameters for the Event object.
     */
    public static int executeEvent(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getErrand(userInput);
        String timestampHeader = Parser.getTimestampHeader(userInput);
        String timestamp = Parser.getTimestamp(userInput);
        Task event = new Event(errand, timestamp, timestampHeader);
        return TaskList.addToTaskList(event);
    }

    /**
     * Adds the Todo object into the ArrayList.
     *
     * @param userInput is the input from the loaded file or console.
     * @return the index of the Todo object that is added to the ArrayList.
     * @throws IllegalTaskCommandException when there are missing parameters for the Todo object.
     */
    public static int executeTodo(String userInput) throws IllegalTaskCommandException {
        String errand = Parser.getSubstring(userInput);
        Task todo = new Todo(errand);
        return TaskList.addToTaskList(todo);
    }

    /**
     * A parser method to call the executeMarkDone method using the input from the loaded file.
     *
     * @param fileInput is the raw input from the loaded save file
     */
    public static void executeLoadMarkDone(String[] fileInput) throws IllegalTaskCommandException {
        if (fileInput[1].strip().equals("X")) {
            String doneCommand = "DONE " + TaskList.returnTaskCount();
            executeMarkDone(doneCommand);
        }
    }
}
