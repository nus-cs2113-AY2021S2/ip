package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    int numberOfMatchedTasks = 1;
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String editedWord = fullCommand.replaceFirst("find","");
        if (!isValidInput()) {
            return;
        }
        for (int i = 1; i <= TaskList.getListSize(); ++i) {
            if (taskList.getTaskAtIndex(i-1).getDescription().contains(editedWord)) {
                Ui.printMatchedIndex(numberOfMatchedTasks++);
                ListCommand.printList(taskList, i);
            }
        }
    }

    @Override
    public boolean isValidInput() {
        if (fullCommand.length() == 4) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
