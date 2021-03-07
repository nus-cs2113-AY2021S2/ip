package Duke.Command;


import Duke.Constant.Message;
import Duke.Constant.SpaceAndLine;
import Duke.Constant.TaskType;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;

/**
 * AddCommand helps the user adds a task.
 */
public class AddCommand extends Command {
    private String taskType;
    private String content;

    public AddCommand(String taskType, String content) {
        this.taskType = taskType;
        this.content = content;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Adds a task of the correct type, print it out to the user and store it in Duke.txt.
     *
     * @param tasks the list of tasks
     * @param ui do outputs
     * @param storage store the data
     * @param matchedTasks the list of matched tasks
     * @param sameDateTasks the list of tasks occurring on the same specified date
     * @throws DukeException the exceptions which can happen during add command execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException {
        Task task;

        switch (taskType) {
        case TaskType.TODO_TYPE:
            task = new Todo(content);
            break;
        case TaskType.DEADLINE_TYPE:
            String[] deadlineComponents = content.split(" /by ");
            task = new Deadline(deadlineComponents[0], deadlineComponents[1]);
            break;
        case TaskType.EVENT_TYPE:
            String[] eventComponents = content.split(" /at ");
            task = new Event(eventComponents[0], eventComponents[1]);
            break;
        default:
            throw new DukeException(Message.WRONG_COMMAND_ERROR_MESSAGE);
        }
        tasks.addTask(task);

        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
        ui.printMessage(Message.ADD_TASK_MESSAGE);
        System.out.println(SpaceAndLine.LONG_SPACE + task);
        ui.printNumberOfTasksMessage(tasks.getSize());
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException(Message.FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
