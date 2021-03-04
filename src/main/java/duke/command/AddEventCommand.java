package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import duke.Duke;

public class AddEventCommand extends Command {
    public AddEventCommand(String description) {
        TaskList.addEvent(description);
    }
}
