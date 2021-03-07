package Duke.Command;

import Duke.Constant.Message;
import Duke.Constant.SpaceAndLine;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;

/**
 * DeleteCommand helps the user delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

     /**
     * Deletes a task and updates Duke.txt.
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
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
        ui.printMessage(Message.REMOVE_TASK_MESSAGE);
        System.out.println(SpaceAndLine.LONG_SPACE + tasks.getTask(index));

        tasks.deleteTask(index);

        ui.printNumberOfTasksMessage(tasks.getSize());
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException(Message.FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
