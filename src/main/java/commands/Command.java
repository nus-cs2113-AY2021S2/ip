package commands;

import io.DukePrint;

public abstract class Command {
    protected DukePrint dukePrint;

    Command(DukePrint dukePrint) {
        this.dukePrint = dukePrint;
    }

    /**
     * Executes the specific command
     */
    public abstract void execute();
}
