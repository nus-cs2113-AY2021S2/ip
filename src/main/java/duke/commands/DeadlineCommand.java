package duke.commands;

import static duke.common.Constants.DEADLINE_TASK_TYPE;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Represents a command that creates, initialises and adds a
 * {@code Deadline} to {@code TaskList} when executed.
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String taskStatus;
    private String taskDeadline;

    public DeadlineCommand(TaskList tasks, String taskDescription, String taskStatus, String taskDeadline) {
        super.tasks = tasks;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDeadline = taskDeadline;
    }

    @Override
    public void execute() {
        Task task = new Deadline(taskDescription, taskDeadline);
        task.setStatus(taskStatus);
        task.setType(DEADLINE_TASK_TYPE);
        tasks.addTaskToList(task);
        ui.echo(tasks);
    }
}
