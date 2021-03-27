package dukchess.controller;

import static dukchess.commands.AddDeadlineCommand.handleAddDeadline;
import static dukchess.commands.AddEventCommand.handleAddEvent;
import static dukchess.commands.AddTodoCommand.handleAddTodo;
import static dukchess.commands.DeleteCommand.handleDeleteTask;
import static dukchess.commands.DoneCommand.handleDoneTask;
import static dukchess.commands.FindCommand.handleFindTask;
import static dukchess.commands.ListCommand.printAddedTasks;
import static dukchess.parser.Parser.COMMAND_ARGS_INDEX;
import static dukchess.parser.Parser.NEXT_COMMAND_INDEX;
import static dukchess.parser.Parser.parseInput;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import dukchess.entity.TaskList;
import dukchess.exceptions.InvalidCommandException;
import dukchess.storage.Storage;
import dukchess.ui.Ui;

/**
 * Delegates business logic from user to individual commands
 */
public class Controller {

    /**
     * Executes a given raw input from stdin.
     * @param input - raw input string from stdin
     * @return whether to exit from the interaction loop
     */
    private static boolean execute(String input) {
        try {
            boolean toExit = false;

            List<String> parsedInput = parseInput(input);
            String nextCommand = parsedInput.get(NEXT_COMMAND_INDEX);
            String commandArgs = parsedInput.get(COMMAND_ARGS_INDEX);

            switch (nextCommand) {
            case "bye":
                toExit = true;
                break;
            case "list":
                printAddedTasks();
                break;
            case "done":
                handleDoneTask(commandArgs);
                break;
            case "todo":
                handleAddTodo(commandArgs);
                break;
            case "deadline":
                handleAddDeadline(commandArgs);
                break;
            case "event":
                handleAddEvent(commandArgs);
                break;
            case "delete":
                handleDeleteTask(commandArgs);
                break;
            case "find":
                handleFindTask(commandArgs);
                break;
            default:
                throw new InvalidCommandException();
            }
            Storage.saveData(TaskList.getTasksList());
            if (!toExit) {
                Ui.promptInputAgain();
            }
            return toExit;
        } catch (InvalidCommandException e) {
            Ui.printErrorMessage("Invalid command :(");
            Ui.promptInputAgain();
            return false;
        }
    }


    /**
     * Initiate the loop that prompts for a user's input and executes commands based on the input.
     */
    public static void runInteractionLoop() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toLowerCase(Locale.ROOT).trim();
        boolean toExit = execute(input);
        while (!toExit) {
            input = in.nextLine().toLowerCase(Locale.ROOT).trim();
            toExit = execute(input);
        }
        in.close();
    }
}
