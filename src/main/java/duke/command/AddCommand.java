package duke.command;

import duke.storage.Storage;
import duke.task.*;

import java.util.List;

public class AddCommand extends Command{
    public String taskType;
    public String taskName;
    public boolean isCompleted;
    public boolean exit;
    public String timeConstraint;

    public AddCommand(String taskType, String taskName, boolean isCompleted, String timeConstraint) {
        super();
        this.taskType = taskType;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.timeConstraint = timeConstraint;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskName, taskType);
        } else if (taskType.equals("deadline")) {
            newTask = new Deadline(taskName, taskType, timeConstraint);
        } else if (taskType.equals("event")) {
            newTask = new Event(taskName, taskType, timeConstraint);
        } else {
            throw new DukeExceptions();
        }
        newTask.setIsCompleted(isCompleted);
        taskList.appendTask(newTask);
        storage.save(taskList);
        ui.showAddedTasks(newTask, taskList.getSize());
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
