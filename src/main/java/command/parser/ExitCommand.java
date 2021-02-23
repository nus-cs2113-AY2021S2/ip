package command.parser;

import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the bye command
 */
public class ExitCommand extends Command {

    // exits DUKE
    public static void exit(ArrayList<TaskList> tasks) {
        if (tasks.size() > EMPTY && UI.getAreAllTasksDone(tasks)) {
            UI.printGoodEnding();
        } else if (tasks.size() > EMPTY && UI.getAreAllTasksNotDone(tasks)) {
            UI.printBadEnding();
        } else {
            UI.printTraitor();
        }
    }
}