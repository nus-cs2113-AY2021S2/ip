package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

public class AddCommand extends Command {
    TaskList tasks;
    Task taskToAdd;

    public AddCommand(TaskList tasks, DukePrint dukePrint) {
        super(dukePrint);
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        tasks.add(taskToAdd);
        dukePrint.printAdd(taskToAdd, tasks.getSize());
    }
}
