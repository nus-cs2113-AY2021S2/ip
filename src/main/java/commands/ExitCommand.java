package commands;

import io.DukePrint;

public class ExitCommand extends Command {

    public ExitCommand(DukePrint dukePrint) {
        super(dukePrint);
    }

    /**
     * Prints the Goodbye Message
     */
    @Override
    public void execute() {
        dukePrint.printBye();
    }
}
