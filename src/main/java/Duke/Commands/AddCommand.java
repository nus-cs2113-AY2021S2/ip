package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

public class AddCommand extends Command {

    public Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(taskToAdd);
        return Output.printAdded(taskToAdd.toString(), taskList.size());
    }

}
