package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * ExitCommand is called when user wants to exit the program
 */
public class ExitCommand extends Command {
    private boolean isExit;

    /**
     * Constructor method of ExitCommand which sets isExit to true
     */
    public ExitCommand() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Prints goodbye message
     * @param tasks TaskList containing all tasks
     * @param ui User Interface
     * @param storage Storage to save all tasks, updating the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}
