package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Subclass of Command
Handle list command by users
*/
public class ListCommand extends Command{

    /*
    Constructor ListCommand Object
    Initialize activity, action variables
    */
    public ListCommand(String activity, String action){
        super(activity, action);
    }

    /*
    Implements execute method
    Function will list all tasks from the tasklist
    */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.displayListOfActivities();
    }
}
