package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute (TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isValidInput();

    public abstract boolean isExit();
}
