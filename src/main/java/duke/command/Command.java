package duke.command;

import duke.Ui;
import duke.error.EmptyNameFieldException;

public abstract class Command {

    protected Ui ui;

    // To be overriden
    public abstract void execute(Ui ui);
}
