package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;
import duke.task.Event;

public class EventCommand extends Command {

    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    public String getAt() {
        return at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(getDescription(), getAt());
        taskList.add(task);
        storage.storeTasksToFile(taskList);
        ui.showAddTaskMessage(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
