package duke.command;

import duke.Duke;
import duke.ui.Menu;
import duke.task.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        Duke.setIsRunning(false);
        Menu.printBye();
    }
}
