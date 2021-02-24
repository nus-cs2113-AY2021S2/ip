package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    final int LENGTH_OF_DONE = 4;

    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

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
