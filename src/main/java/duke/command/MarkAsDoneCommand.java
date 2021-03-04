package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

public class MarkAsDoneCommand extends Command{
    public MarkAsDoneCommand(String description) {
        TaskList.markAsDone(description);
    }
}
