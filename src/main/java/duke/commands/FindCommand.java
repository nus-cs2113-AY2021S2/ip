package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {

    public FindCommand(String activity, String action) {
        super(activity, action);
    }

    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.findTask(action);
    }
}
