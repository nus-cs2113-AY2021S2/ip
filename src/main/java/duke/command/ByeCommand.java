package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to handle the bye command
 */
public class ByeCommand extends Command {

    public ByeCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isValidInput() {
        return false;
    }

    /**
     * @return true when user inputs bye to terminate the duke object
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
