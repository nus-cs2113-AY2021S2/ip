package duke.command;

import duke.storage.Storage;
import duke.task.*;

/**
 * Represents the executor that will display all tasks from task list
 */
public class DisplayCommand extends Command {

    /**
     * Displays all tasks in the task list
     * @param taskList TaskList object that stores all current tasks
     * @param storage Storage object that communicates with database
     * @param ui Ui object that handles the interaction with user
     * @throws DukeExceptions if the task list is empty
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        if (taskList.isEmpty()) {
            throw new DukeExceptions();
        }
        ui.showDisplayTasks(taskList);
    }

    /**
     * Checks if we want to exit the program
     * @return whether or not to exit the program
     */
    @Override
    public boolean isExit() {
        return exit;
    }
}
