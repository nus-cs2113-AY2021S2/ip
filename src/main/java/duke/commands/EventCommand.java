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
    private final String eventDescription;
    private final String eventStatus;
    private final String eventTiming;
    private boolean isEcho = true;

    /**
     * Class constructor.
     */
    public EventCommand(TaskList tasks, String eventDescription, String eventStatus,
                        String eventTiming) {
        super.tasks = tasks;
        this.eventDescription = eventDescription;
        this.eventStatus = eventStatus;
        this.eventTiming = eventTiming;
    }

    /**
     * Class constructor with parameter {@code isEcho} specifying that this
     * command is executed silently (no message displayed to the user).
     * Used when loading data from disk.
     */
    public EventCommand(TaskList tasks, String eventDescription, String eventStatus,
                        String eventTiming, boolean isEcho) {
        super.tasks = tasks;
        this.eventDescription = eventDescription;
        this.eventStatus = eventStatus;
        this.eventTiming = eventTiming;
        this.isEcho = isEcho;
    }

    @Override
    public void execute() {
        Task task = new Event(eventDescription, eventTiming);
        task.setStatus(eventStatus);
        task.setType(EVENT_TASK_TYPE);
        tasks.addTaskToList(task);
        if (isEcho) {
            ui.echo(tasks, tasks.getTasksCount() - 1, PRINT_NEW_TASK_STATEMENT);
            ui.printBorder();
        }
    }
}
