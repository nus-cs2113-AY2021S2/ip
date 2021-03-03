package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Storage storage) throws RangeError {
        if (index > taskList.size() || index < 1) {
            throw new RangeError();
        }
        Task deletedTask = taskList.deleteTask(index);
        return Output.printDeleted(deletedTask.toString(), taskList.size());
    }

}
