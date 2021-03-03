package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {

    private String line;

    /**
     * Constructor of AddDeadlineCommand Class.
     *
     * @param line user input
     */
    public AddDeadlineCommand(String line) {
        this.line = line;
    }

    /**
     * Adds Deadline task to task list and saves it in the data file.
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(line);
        storage.saveFile(tasks.getTasks());
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
