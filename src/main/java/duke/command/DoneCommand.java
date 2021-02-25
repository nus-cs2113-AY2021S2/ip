package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to handle the done command
 */
public class DoneCommand extends Command {

    final int LENGTH_OF_DONE = 4;

    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Carries out the done command and marks the specified task as done in the task list
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
        Task doneObj = taskList.getTaskAtIndex(Integer.valueOf(words[1]) - 1);
        doneObj.markAsDone();
        Ui.printDone(doneObj);
    }

    /**
     * Checks for the error case where the input is the command without description
     * @return false if the command is invalid
     */
    @Override
    public boolean isValidInput() {
        if (fullCommand.length() == LENGTH_OF_DONE) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
