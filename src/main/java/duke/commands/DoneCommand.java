package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Subclass of Command
Handle done command by users
*/
public class DoneCommand extends Command {

    /*
    Constructor DoneCommand Object
    Initialize activity, action variables
    */
    public DoneCommand(String activity, String action) {
        super(activity, action);
    }

    /*
    Implements execute method
    Function will mark a task as done
    */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.markActivityAsDone(action);
        storage.dump(tasks);
    }
}
