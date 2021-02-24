package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.Task;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents an exception handler that deals with incorrect user inputs
 */
public class CommandsHandler {

    // handles exceptions when user uses the delete command
    public static void handleDelete(ArrayList<Task> tasks, String[] sentence) {
        try {
            DeleteCommand.deleteTask(sentence, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        }
    }

    // handles exceptions when the user uses the done command
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

    // handles exceptions when the user uses the list command
    public static void handleList(ArrayList<Task> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyList();
        }
    }

    // handles exceptions that can occur when user inputs find command
    public static void handleFind(String line, ArrayList<Task> tasks) {
        try {
            FindCommand.findTask(line, tasks);
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        }
    }

    // handles exceptions when searching for a task based on the date provided
    public static void handleSearch(ArrayList<Task> tasks, String[] sentence) {
        try {
            SearchCommand.searchTasksDates(tasks, sentence);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        }
    }
}