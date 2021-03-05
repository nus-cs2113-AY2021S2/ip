package duke.commands;

import static duke.Ui.PRINT_NEW_TASK_STATEMENT;
import static duke.common.Constants.TODO_TASK_TYPE;

import duke.TaskList;
import duke.tasks.Task;

/**
 * Represents a command that creates, initialises and adds a
 * {@code Task} to {@code TaskList} when executed.
 */
public class TodoCommand extends Command {
    private final String taskDescription;
    private final String taskStatus;
    private boolean isEcho = true;

    /**
     * Class constructor.
     */
    public TodoCommand(TaskList tasks, String taskDescription, String taskStatus) {
        super.tasks = tasks;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    /**
     * Class constructor with parameter {@code isEcho} specifying that this
     * command is executed silently (no message displayed to the user).
     * Used when loading data from disk.
     */
    public TodoCommand(TaskList tasks, String taskDescription, String taskStatus, boolean isEcho) {
        super.tasks = tasks;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.isEcho = isEcho;
    }

    @Override
    public void execute() {
        Task task = new Task(taskDescription);
        task.setStatus(taskStatus);
        task.setType(TODO_TASK_TYPE);
        tasks.addTaskToList(task);
        if (isEcho) {
            ui.echo(tasks, tasks.getTasksCount() - 1, PRINT_NEW_TASK_STATEMENT);
            ui.printBorder();
        }
    }
}
