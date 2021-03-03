package jarvis.commands;

import jarvis.Duke;
import jarvis.task.Task;
import jarvis.task.TaskList;

public abstract class Command {

    /**
     * Prints a message when a task is added to the list
     *
     * @param task the task to be added into the list
     */
    protected static void addSuccessMessage(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + TaskList.getSize() + " tasks in the list.");
        Duke.jarvis.printDivider();
    }
}
