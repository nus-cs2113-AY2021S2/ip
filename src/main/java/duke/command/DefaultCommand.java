package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;

import java.util.ArrayList;

public class DefaultCommand extends Command {
    public DefaultCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() throws DukeException {
        DukePrinter.printFallbackMessage();
    }
}
