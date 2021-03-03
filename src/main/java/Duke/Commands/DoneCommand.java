package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class DoneCommand extends Command {

    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Storage storage) throws RangeError {
        if (index > taskList.size() || index < 1) {
            throw new RangeError();
        }
        Task doneTask = taskList.updateCompletion(index);
        return Output.printUpdated(doneTask.toString());
    }

}
