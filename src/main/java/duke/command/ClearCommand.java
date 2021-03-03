package duke.command;

import duke.DukePrinter;
import duke.DukeTaskList;

import java.util.ArrayList;

public class ClearCommand extends Command {
    public ClearCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() {
        dukeTaskList.clearTasks();
        DukePrinter.printTasksClearedMessage();
    }
}
