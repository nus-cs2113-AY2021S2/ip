package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    /**
     * Shows a goodbye message and triggers the end of the program loop
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
