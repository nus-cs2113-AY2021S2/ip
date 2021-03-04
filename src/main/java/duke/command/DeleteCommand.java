package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(String description) {
        TaskList.deleteTask(description);
    }
}
