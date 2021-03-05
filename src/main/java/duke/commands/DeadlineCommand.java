package duke.commands;

import static duke.Ui.PRINT_NEW_TASK_STATEMENT;
import static duke.common.Constants.DEADLINE_TASK_TYPE;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Represents a command that creates, initialises and adds a
 * {@code Deadline} to {@code TaskList} when executed.
 */
public class DeadlineCommand extends Command {
    private final String deadlineDescription;
    private final String deadlineStatus;
    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;
    private boolean isEcho = true;

    /**
     * Class constructor.
     */
    public DeadlineCommand(TaskList tasks, String deadlineDescription, String deadlineStatus,
                           LocalDate deadlineDate, LocalTime deadlineTime) {
        super.tasks = tasks;
        this.deadlineDescription = deadlineDescription;
        this.deadlineStatus = deadlineStatus;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Class constructor with parameter {@code isEcho} specifying that this
     * command is executed silently (no message displayed to the user).
     * Used when loading data from disk.
     */
    public DeadlineCommand(TaskList tasks, String deadlineDescription, String deadlineStatus,
                           LocalDate deadlineDate, LocalTime deadlineTime, boolean isEcho) {
        super.tasks = tasks;
        this.deadlineDescription = deadlineDescription;
        this.deadlineStatus = deadlineStatus;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.isEcho = isEcho;
    }

    @Override
    public void execute() {
        Task task = new Deadline(deadlineDescription, deadlineDate, deadlineTime);
        task.setStatus(deadlineStatus);
        task.setType(DEADLINE_TASK_TYPE);
        tasks.addTaskToList(task);
        if (isEcho) {
            ui.echo(tasks, tasks.getTasksCount() - 1, PRINT_NEW_TASK_STATEMENT);
            ui.printBorder();
        }
    }
}
