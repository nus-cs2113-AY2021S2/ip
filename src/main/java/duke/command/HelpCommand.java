package duke.command;

import duke.DukePrinter;
import duke.DukeTaskList;

import java.util.ArrayList;

public class HelpCommand extends Command{
    public HelpCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() {
        DukePrinter.printHelpMessage();
    }
}
