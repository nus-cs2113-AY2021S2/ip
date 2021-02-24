package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalTaskException;
import task.list.Task;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the delete command
 */
public class DeleteCommand extends Command {

    // deletes the tasks indicated by the index that the user inputted
    public static void deleteTask(String[] sentence, ArrayList<Task> tasks) throws IllegalCommandException,
            IllegalTaskException {
        int index;
        try {
            index = DoneCommand.getIndex(sentence, tasks);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        Task t = tasks.get(index - 1);
        UI.printDeletingTask();
        t.printTask();
        tasks.remove(index - 1);
        if (tasks.size() > EMPTY) {
            ListCommand.printNumberOfTasksLeft(tasks);
        }
    }
}