package duke.commands;

import duke.TaskList;
import duke.task.Task;

/**
 * Adds new Task object to the TaskList
 */
public class AddCommand implements Command {
    Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList tasks) {
        tasks.addTask(newTask);
    }
}
