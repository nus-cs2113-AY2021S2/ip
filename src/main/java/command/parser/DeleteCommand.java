package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalTaskException;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public static void deleteTask(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException,
            IllegalTaskException {
        int index;
        try {
            index = DoneCommand.getIndex(sentence, tasks);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        TaskList t = tasks.get(index - 1);
        UI.printDeletingTask();
        t.printTask();
        tasks.remove(index - 1);
        if (tasks.size() > EMPTY) {
            ListCommand.printNumberOfTasksLeft(tasks);
        }
    }
}