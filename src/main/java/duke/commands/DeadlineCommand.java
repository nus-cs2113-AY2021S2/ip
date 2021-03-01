package duke.commands;

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
    private String taskDescription;
    private String taskStatus;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public DeadlineCommand(TaskList tasks, String description, String status, LocalDate date, LocalTime time) {
        super.tasks = tasks;
        taskDescription = description;
        taskStatus = status;
        deadlineDate = date;
        deadlineTime = time;
    }

    @Override
    public void execute() {
        Task task = new Deadline(taskDescription, deadlineDate, deadlineTime);
        task.setStatus(taskStatus);
        task.setType(DEADLINE_TASK_TYPE);
        tasks.addTaskToList(task);
        ui.echo(tasks);
    }
}
