package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents an exception handler that deals with incorrect user inputs
 */
public class CommandsHandler {

    // handles exceptions when user uses the delete command
    public static void handleDelete(ArrayList<TaskList> tasks, String[] sentence) {
        try {
            DeleteCommand.deleteTask(sentence, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        }
    }

    // handles exceptions when the user uses the done command
    public static void handleMarkAsDone(ArrayList<TaskList> tasks, String[] sentence) {
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
    public static void handleList(ArrayList<TaskList> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyList();
        }
    }
}