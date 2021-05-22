package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class AddCommand extends Command {

    /**
     * Task to be added to the Task List
     */
    public Task taskToAdd;

    /**
     * Constructor
     * @param taskToAdd
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(taskToAdd);
        return Output.printAdded(taskToAdd.toString(), taskList.size());
    }

}
