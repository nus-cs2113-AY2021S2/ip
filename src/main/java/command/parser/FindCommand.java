package command.parser;

import exceptions.IllegalCommandException;
import exceptions.IllegalTaskException;
import task.list.Deadline;
import task.list.Event;
import task.list.Task;
import task.list.Todo;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the find command
 */
public class FindCommand extends Command {

    public static final String TASK_COUNTER_SENTENCE = "Here is task number: ";
    public static final int INCORRECT_LENGTH = 1;

    // finds task from list of tasks
    public static void findTask(String line, ArrayList<Task> tasks) throws IllegalCommandException,
            IllegalTaskException {
        if (line.length() == INCORRECT_LENGTH) {
            throw new IllegalCommandException();
        }
        String description = AddCommand.getTaskDescription(line).trim();
        int counter = 0;
        for (Task task : tasks) {
            counter = updateCounter(description, counter, task);
        }
        if (counter == EMPTY) {
            throw new IllegalTaskException();
        } else if (counter > EMPTY) {
            UI.printFindComplete();
        }
    }

    // Updates counter when a match is found 
    private static int updateCounter(String description, int counter, Task task) {
        if (task.getTaskDescription().equals(description)) {
            counter++;
            printTask(task, counter);
        }
        return counter;
    }

    // prints task(s) that the user is looking for
    public static void printTask(Task task, int counter) {
        System.out.println(TASK_COUNTER_SENTENCE + counter);
        if (task instanceof Event) {
            Event temp = (Event) task;
            temp.printTask();
        } else if (task instanceof Deadline) {
            Deadline temp = (Deadline) task;
            temp.printTask();
        } else if (task instanceof Todo) {
            Todo temp = (Todo) task;
            temp.printTask();
        }
    }
}