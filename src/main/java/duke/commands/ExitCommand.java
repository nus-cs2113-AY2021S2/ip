package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command{

    public ExitCommand(String activity, String action){
        super(activity, action);
    }

    public void execute(TaskList tasks, Storage storage) throws DukeException {
        super.setExitCondition(true);
    }
}
