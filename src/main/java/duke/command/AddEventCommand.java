package duke.command;

import duke.exception.DukeException;
//import duke.tasklist.TaskList;
import duke.Duke;

public class AddEventCommand extends Command {
    public AddEventCommand(String description) {
        Duke.addEvent(description);
    }
}
