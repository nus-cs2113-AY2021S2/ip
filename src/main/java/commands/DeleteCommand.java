package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

public class DeleteCommand extends Command {

    /**
     * Index of the Task to be deleted specified by the user
     */
    private final int toDelete;
    private TaskList taskList;

    public DeleteCommand(TaskList taskList, int toDelete, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
        this.toDelete = toDelete;
    }

    /**
     * Removes the specified Task from the TaskList and prints the delete message
     */
    @Override
    public void execute() {
        Task removedTask = taskList.remove(toDelete - 1);
        dukePrint.printDelete(removedTask);
    }
}
