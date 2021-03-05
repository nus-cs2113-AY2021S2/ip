package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Prints all tasks in the task list.
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    /**
     * Returns false as command is not of type bye.
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
