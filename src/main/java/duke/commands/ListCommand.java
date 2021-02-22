package duke.commands;

import duke.task.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.listAllTasks(taskList);
    }
}
