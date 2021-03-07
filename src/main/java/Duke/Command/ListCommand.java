package Duke.Command;

import Duke.Constant.Message;
import Duke.Constant.SpaceAndLine;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * ListCommand helps the user print the task(s) in the list.
 */
public class ListCommand extends Command {

    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks the list of tasks
     * @param ui outputs to be printed
     * @param storage store data into Duke.txt
     * @throws DukeException the exceptions which can happen
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException {
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
        switch (tasks.getSize()) {
        case 0:
            System.out.println(Message.EMPTY_LIST_MESSAGE);
            break;
        case 1:
            System.out.println(Message.ONE_TASK_IN_LIST_MESSAGE);
            break;
        default:
            System.out.println(Message.MORE_THAN_ONE_TASK_IN_LIST_MESSAGE);
        }
        ui.printTasks(tasks);
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
    }
}
