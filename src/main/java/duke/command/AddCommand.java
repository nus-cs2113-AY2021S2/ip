package duke.command;

import duke.storage.Storage;
import duke.task.*;

/**
 * Represents an executor that will add tasks to the task list
 */
public class AddCommand extends Command{
    public String taskType;
    public String taskName;
    public boolean isCompleted;
    public boolean exit;
    public String timeConstraint;

    /**
     * Constructor of this class. Initializes necessary details to add a task
     * @param taskType The type of task to be added
     * @param taskName The name of task to be added
     * @param isCompleted Whether the task has been completed
     * @param timeConstraint The time requirements set by user
     */
    public AddCommand(String taskType, String taskName, boolean isCompleted, String timeConstraint) {
        super();
        this.taskType = taskType;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.timeConstraint = timeConstraint;
    }

    /**
     * Adds a task to the task list
     * @param taskList TaskList object that stores all current tasks
     * @param storage Storage object that communicates with database
     * @param ui Ui object that handles the interaction with user
     * @throws DukeExceptions if the task type is not valid
     */
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

    /**
     * Checks if we want to exit the program
     * @return whether or not to exit the program
     */
    @Override
    public boolean isExit() {
        return exit;
    }
}
