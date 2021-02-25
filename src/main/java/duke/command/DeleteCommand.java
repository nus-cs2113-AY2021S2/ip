package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private String line;

    /**
     * Constructor of DeleteCommand Class
     *
     * @param line user input
     */
    public DeleteCommand(String line) {
        this.line = line;
    }

    /**
     * Deletes a task from TaskList and saves the textfile
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(line);
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
