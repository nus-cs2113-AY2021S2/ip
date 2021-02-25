package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to handle the delete command
 */
public class DeleteCommand extends Command{

    final int LENGTH_OF_DELETE = 6;

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Removes the selected task from the task list
     * @param taskList the Task List object which has the current tasks
     * @param ui The Ui object for user to interact with
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!isValidInput()) {
            Ui.printCommandIsInvalid();
            return;
        }
        String[] words = fullCommand.split(" ");
        int indexOfDeletedTask = Integer.valueOf(words[1])-1;
        Task deletedTask = TaskList.getTaskAtIndex(indexOfDeletedTask);
        taskList.deleteTask(deletedTask);
        Keyword.removeKeyword(Integer.valueOf(indexOfDeletedTask));
        Ui.printDeleted();
    }

    /**
     * Checks for the error case where the input is the command without description
     * @return false if the command is invalid
     */

    @Override
    public boolean isValidInput() {
        if (fullCommand.length() == LENGTH_OF_DELETE) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
