package duke.commands;

import static duke.Ui.PRINT_NEW_TASK_STATEMENT;
import static duke.common.Constants.EVENT_TASK_TYPE;

import duke.TaskList;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Represents a command that creates, initialises and adds an
 * {@code Event} to {@code TaskList} when executed.
 */
public class EventCommand extends Command {
    private final String taskDescription;
    private final String taskStatus;
    private final String taskTiming;

    public EventCommand(TaskList tasks, String taskDescription, String taskStatus, String taskTiming) {
        super.tasks = tasks;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskTiming = taskTiming;
    }

    @Override
    public void execute() {
        Task task = new Event(taskDescription, taskTiming);
        task.setStatus(taskStatus);
        task.setType(EVENT_TASK_TYPE);
        tasks.addTaskToList(task);
        ui.echo(tasks, tasks.getTasksCount() - 1, PRINT_NEW_TASK_STATEMENT);
        ui.printBorder();
    }
}
