package duke.commands;

import static duke.common.Constants.EVENT_TASK_TYPE;

import duke.TaskList;
import duke.tasks.Event;
import duke.tasks.Task;

public class EventCommand extends Command {
    private String taskDescription;
    private String taskStatus;
    private String taskTiming;

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
        ui.echo(tasks);
    }
}
