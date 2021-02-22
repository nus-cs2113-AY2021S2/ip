package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

public class CommandsHandler {
    public static void handleDelete(ArrayList<TaskList> tasks, String[] sentence) {
        try {
            DeleteCommand.deleteTask(sentence, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        }
    }

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

    public static void handleList(ArrayList<TaskList> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyList();
        }
    }

    // handles exceptions that can occur when user inputs find command
    public static void handleFind(String line, ArrayList<TaskList> tasks) {
        try {
            FindCommand.findTask(line, tasks);
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        }
    }
}