package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.TaskList;

import java.util.ArrayList;

public class DoneCommand extends Command {

    public static void markTaskAsDone(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException, IllegalTaskException,
            IllegalTaskRedoException {
        int index;
        try {
            index = getIndex(sentence, tasks);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        TaskList t = tasks.get(index - 1);
        try {
            t.markAsDone();
        } catch (IllegalTaskRedoException e) {
            throw new IllegalTaskRedoException();
        }
        tasks.set(index - 1, t);
        ListCommand.printNumberOfTasksLeft(tasks);
    }

    public static int getIndex(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException,
            IllegalTaskException {
        if (sentence.length > NUMBER_OF_COMMAND_ARGUMENTS) {
            throw new IllegalCommandException();
        }
        int index = getIndexFromCommand(sentence[1]);
        if (index > tasks.size() || index == -1) {
            throw new IllegalTaskException();
        }
        return index;
    }

    public static int getIndexFromCommand(String index) {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            return WRONG_INDEX;
        }
    }
}