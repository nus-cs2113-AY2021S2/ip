package command.parser;

import exceptions.IllegalListException;
import task.list.Task;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the list command
 */
public class ListCommand extends Command {

    // prints all tasks in the list
    public static void printAllLists(ArrayList<Task> tasks) throws IllegalListException {
        int i = 1;
        if (tasks.size() == EMPTY) {
            throw new IllegalListException();
        }
        UI.printListName();
        for (Task t : tasks) {
            System.out.print(i + ". ");
            t.printTask();
            i++;
        }
        printNumberOfTasksLeft(tasks);
        UI.printDottedLines();
    }

    // prints the number of tasks in the list that hasn't been done yet
    public static void printNumberOfTasksLeft(ArrayList<Task> tasks) {
        if (UI.getAreAllTasksDone(tasks) && tasks.size() > EMPTY) {
            UI.printCompletedTasks();
        } else if (UI.getAreAllTasksNotDone(tasks) && tasks.size() > EMPTY) {
            UI.printNoTasksDone();
        } else {
            int tasksLeft = UI.getNumberOfTaskRemaining(tasks);
            UI.printSomeTasksRemaining(tasksLeft);
        }
    }
}