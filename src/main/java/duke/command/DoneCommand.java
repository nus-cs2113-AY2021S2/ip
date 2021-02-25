package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private String line;

    /**
     * Constructor of DoneCommand Class
     *
     * @param line user input
     */
    public DoneCommand(String line) {
        this.line = line;
    }

    /**
     * Marks a task from TaskList as done and saves the textfile
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(line);
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Returns false as command is not of type bye
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
