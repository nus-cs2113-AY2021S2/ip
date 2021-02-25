package duke.command;

import java.util.ArrayList;
import duke.Duke;
import duke.tasks.Task;


public class ListCommand extends Command{
    public ListCommand() {
        Duke.showList();
    }
}
