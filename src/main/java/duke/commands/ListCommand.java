package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class ListCommand extends Command{

    public ListCommand(String activity, String action){
        super(activity, action);
    }

    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.displayListOfActivities();
    }
}
