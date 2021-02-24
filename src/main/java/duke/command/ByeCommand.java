package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command{

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

    @Override
    public boolean isExit() {
        return true;
    }
}
