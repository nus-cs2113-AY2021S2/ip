package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command{

    final int LENGTH_OF_DELETE = 6;

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

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
