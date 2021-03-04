package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String description) {
        TaskList.addDeadline(description);
    }
}
