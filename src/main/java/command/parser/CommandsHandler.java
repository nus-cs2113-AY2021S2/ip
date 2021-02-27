package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.Task;
import ui.UI;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents an exception handler that deals with incorrect user inputs
 */
public class CommandsHandler {

    /**
     * handles exceptions when user uses the delete command
     *
     * @param tasks    is the list of tasks
     * @param sentence is the inputted line in array format
     */
    public static void handleDelete(ArrayList<Task> tasks, String[] sentence) {
        try {
            DeleteCommand.deleteTask(sentence, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        }
    }

    /**
     * handles exceptions when the user uses the done command
     *
     * @param tasks    is the list of tasks
     * @param sentence is the inputted line in array format
     */
    public static void handleMarkAsDone(ArrayList<Task> tasks, String[] sentence) {
        try {
            DoneCommand.markTaskAsDone(sentence, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskRedoException e) {
            UI.printTaskAlreadyCompletedPhrase(tasks);
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        }
    }

    /**
     * handles exceptions when the user uses the list command
     *
     * @param tasks is the list of tasks
     */
    public static void handleList(ArrayList<Task> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyList();
        }
    }

    /**
     * handles exceptions that can occur when user inputs find command
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     */
    public static void handleFind(String line, ArrayList<Task> tasks) {
        try {
            FindCommand.findTask(line, tasks);
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        }
    }

    /**
     * handles exceptions when searching for a task based on the date provided
     *
     * @param tasks    is the list of tasks
     * @param sentence is the inputted line in array format
     */
    public static void handleSearch(ArrayList<Task> tasks, String[] sentence) {
        try {
            SearchCommand.searchTasksDates(tasks, sentence);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        }
    }

    /**
     * handles corrupted duke.txt file
     *
     * @param input is the scanner that reads from the file
     * @param tasks is the list of tasks
     */
    public static void handleCorruptedFile(Scanner input, ArrayList<Task> tasks) {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                UI.printCorruptedFile();
                System.exit(-1);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);
        Parser.selectCommand(input.nextLine(), tasks);
        timer.cancel();
    }
}