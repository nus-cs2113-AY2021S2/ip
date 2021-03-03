package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String line;

    /**
     * Constructor of FindCommand Class
     *
     * @param line user input
     */
    public FindCommand(String line) {
        this.line = line;
    }

    /**
     * Finds task(s) in the task list based on keyword.
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(line);
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
