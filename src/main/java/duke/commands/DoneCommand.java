package duke.commands;

import static duke.Ui.PRINT_DONE_TASK_STATEMENT;
import static duke.common.Constants.DONE_STATUS;

import duke.TaskList;

/**
 *  Represents a command that marks a {@code Task} in
 *  {@code TaskList} as completed when executed.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(TaskList tasks, int taskIndex) {
        super.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        tasks.getTaskAtIndex(taskIndex-1).setStatus(DONE_STATUS);
        ui.echo(tasks, PRINT_DONE_TASK_STATEMENT);
    }
}
