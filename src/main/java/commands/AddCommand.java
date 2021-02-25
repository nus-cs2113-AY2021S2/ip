package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

public class AddCommand extends Command {
    TaskList taskList;
    Task taskToAdd;

    public AddCommand(TaskList taskList, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.add(taskToAdd);
        dukePrint.printAdd(taskToAdd);
    }
}
