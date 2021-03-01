package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that removes a {@code Task} from {@TaskList} when executed.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(TaskList tasks, int taskIndex) {
        super.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        tasks.getTaskAtIndex(taskIndex-1).printTask();
        tasks.deleteTaskFromList(taskIndex-1);
        ui.echo(tasks, Ui.PRINT_DELETED_TASK_STATEMENT);
    }
}
