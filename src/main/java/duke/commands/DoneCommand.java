package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String activity, String action) {
        super(activity, action);
    }

    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.markActivityAsDone(action);
        storage.dump(tasks);
    }
}
