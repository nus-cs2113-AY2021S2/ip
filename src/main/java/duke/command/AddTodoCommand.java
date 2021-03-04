package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

import duke.Duke;

public class AddTodoCommand extends Command {
    public AddTodoCommand(String description) {
        TaskList.addToDo(description);
    }
}
