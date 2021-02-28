package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Subclass of Command
Handle exit command by users
*/
public class ExitCommand extends Command{

    /*
    Constructor ExitCommand Object
    Initialize activity, action variables
    */
    public ExitCommand(String activity, String action){
        super(activity, action);
    }

    /*
    Implements execute method
    Function will set the exit variable condition to true
    */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        super.setExitCondition(true);
    }
}
