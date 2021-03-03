package duke.commands;

import duke.TaskList;
import duke.task.Task;

public class AddCommand implements Command {
    Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList tasks) {
        tasks.addTask(newTask);
    }
}
