package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Subclass of Command
Handle delete command by users
*/
public class DeleteCommand extends Command{

    /*
    Constructor DeleteCommand Object
    Initialize activity, action variables
    */
    public DeleteCommand(String activity, String action){
        super(activity, action);
    }

    /*
    Implements execute method
    Function will delete a task from the tasklist
    */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.deleteATask(action);
        storage.dump(tasks);
    }
}
