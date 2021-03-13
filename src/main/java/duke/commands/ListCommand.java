package duke.commands;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents the list command given by the user.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) {
        ui.listAllTasks(taskList);
    }
}
