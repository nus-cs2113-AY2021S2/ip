package duke.commands;

import duke.TaskList;
import duke.Ui;

import static duke.Ui.PRINT_DELETED_TASK_STATEMENT;

/**
 * Represents a command that removes a {@code Task} from {@code TaskList} when executed.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(TaskList tasks, int taskIndex) {
        super.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        ui.echo(tasks, taskIndex - 1, PRINT_DELETED_TASK_STATEMENT);
        tasks.deleteTaskFromList(taskIndex - 1);
        ui.printNumberOfTasks(tasks.getTasksCount());
    }
}
