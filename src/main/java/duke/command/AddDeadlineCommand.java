package duke.command;

import duke.exception.DukeException;
//import duke.tasklist.TaskList;
import duke.Duke;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String description) {
        Duke.addDeadline(description);
    }
}
