package duke.command;

import duke.exception.DukeException;
//import duke.tasklist.TaskList;
import duke.Duke;

public class AddTodoCommand extends Command {
    public AddTodoCommand(String description) {
        Duke.addToDo(description);
    }
}
