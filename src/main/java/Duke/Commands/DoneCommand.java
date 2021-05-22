package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class DoneCommand extends Command {

    /**
     * Index of the task to be set as done in the ArrayList
     */
    int index;

    /**
     * Constructor
     * @param index
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     * @throws RangeError
     */
    public String execute(TaskList taskList, Storage storage) throws RangeError {
        if (index > taskList.size() || index < 1) {
            throw new RangeError();
        }
        Task doneTask = taskList.updateCompletion(index);
        return Output.printUpdated(doneTask.toString());
    }

}
