package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

public class ListCommand extends Command {

    private TaskList taskList;

    public ListCommand(TaskList taskList, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        dukePrint.printList(taskList);
    }
}