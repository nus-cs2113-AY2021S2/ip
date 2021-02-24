package duke.command;

import duke.DukePrinter;
import duke.DukeTaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() {
        ArrayList<String> taskStrings = dukeTaskList.getTasksAsString();
        DukePrinter.printTasks(taskStrings);
    }
}
