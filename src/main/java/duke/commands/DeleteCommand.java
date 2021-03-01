package duke.commands;

import static duke.common.Constants.BORDER;
import static duke.common.Constants.NEWLINE;

import duke.TaskList;

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
        System.out.print(BORDER);
        System.out.print("This task has been removed:" + NEWLINE);
        System.out.print("\t");
        tasks.getTaskAtIndex(taskIndex-1).printTask();
        tasks.deleteTaskFromList(taskIndex-1);
        ui.printNumberOfTasks(tasks.getTasksCount());
    }
}
