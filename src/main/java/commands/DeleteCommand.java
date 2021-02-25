package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

public class DeleteCommand extends Command {

    private TaskList taskList;
    private int toDelete;

    public DeleteCommand(TaskList taskList, int toDelete, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
        this.toDelete = toDelete;
    }

    @Override
    public void execute() {
        Task removedTask = taskList.remove(toDelete - 1);
        dukePrint.printDelete(removedTask);
    }
}
