package command.parser;

import exceptions.IllegalListException;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static void printAllLists(ArrayList<TaskList> tasks) throws IllegalListException {
        int i = 1;
        if (tasks.size() == EMPTY) {
            throw new IllegalListException();
        }
        UI.printListName();
        for (TaskList t : tasks) {
            System.out.print(i + ". ");
            t.printTask();
            i++;
        }
        printNumberOfTasksLeft(tasks);
        UI.printDottedLines();
    }

    public static void printNumberOfTasksLeft(ArrayList<TaskList> tasks) {
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