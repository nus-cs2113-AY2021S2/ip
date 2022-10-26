package commands;

import myexceptions.EmptyListException;
import tasklist.Task;
import tasklist.Tasklist;

/**
 * Method that lists all recorded tasks for the user
 */
public class ListTasks {

    public static void execute() throws EmptyListException {
        if (Tasklist.getSize() == 0) {
            throw new EmptyListException();
        }
        System.out.println("As of now, you have " + Tasklist.getSize() + " tasks!");
        for (int i = 0; i < Tasklist.getSize(); ++i) {
            Task task = Tasklist.getIndex(i);
            System.out.println(i + 1 + "." + task.getStatusIcon() + " "
                    + task.getDescription());
        }
    }
}
