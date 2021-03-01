package duke.commands;

import static duke.common.Constants.BORDER;
import static duke.common.Constants.DONE_STATUS;
import static duke.common.Constants.NEWLINE;

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
        System.out.print(BORDER);
        System.out.print("Nice! This task is now done:" + NEWLINE);
        System.out.print("\t");
        tasks.getTaskAtIndex(taskIndex-1).printTask();
        ui.printNumberOfTasks(tasks.getTasksCount());
    }
}
