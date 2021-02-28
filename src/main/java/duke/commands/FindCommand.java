package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Subclass of Command
Handle exit command by users
*/
public class FindCommand extends Command {

    /*
    Constructor FindCommand Object
    Initialize activity, action variables
    */
    public FindCommand(String activity, String action) {
        super(activity, action);
    }

    /*
    Implements execute method
    Function will list all tasks that contains action string
    */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.findTask(action);
    }
}
