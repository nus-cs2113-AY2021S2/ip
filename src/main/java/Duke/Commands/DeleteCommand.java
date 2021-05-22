package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class DeleteCommand extends Command {

    /**
     * Index of the task to be deleted from the ArrayList
     */
    int index;

    /**
     * Constructor
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     * @throws RangeError when the index provided by the user is out of range of the ArrayList
     */
    public String execute(TaskList taskList, Storage storage) throws RangeError {
        if (index > taskList.size() || index < 1) {
            throw new RangeError();
        }
        Task deletedTask = taskList.deleteTask(index);
        return Output.printDeleted(deletedTask.toString(), taskList.size());
    }

}
