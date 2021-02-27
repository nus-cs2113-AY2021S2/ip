package command.parser;

import task.list.Task;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the bye command
 */
public class ExitCommand extends Command {

    /**
     * exits DUKE
     *
     * @param tasks is the list of tasks
     */
    public static void exit(ArrayList<Task> tasks) {
        if (tasks.size() > EMPTY && UI.getAreAllTasksDone(tasks)) {
            UI.printGoodEnding();
        } else if (tasks.size() > EMPTY && UI.getAreAllTasksNotDone(tasks)) {
            UI.printBadEnding();
        } else {
            UI.printTraitor();
        }
    }
}