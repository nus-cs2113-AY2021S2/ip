package jarvis.commands;

import jarvis.Duke;
import jarvis.exception.EmptyListException;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class ListTasks extends Command {

    /**
     * Prints out all the tasks in the list
     *
     * @throws EmptyListException if there is no task in the list
     */
    public static void execute() throws EmptyListException {
        if (TaskList.getSize() == 0) {
            throw new EmptyListException();
        }
        System.out.println("\tHere are the tasks in your list, sir:");
        for (int i = 0; i < TaskList.getSize(); i++) {
            Task task = TaskList.getTaskWithIndex(i);
            System.out.println(String.format("\t\t%d. ", i + 1) + task.toString());
        }
        Duke.jarvis.printDivider();
    }
}
