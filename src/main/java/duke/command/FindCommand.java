package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class deals with the find command
 */
public class FindCommand extends Command{

    int numberOfMatchedTasks = 0;

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * This method finds and print all matching words that match with the search word
     * @param taskList the Task List object which has the current tasks
     * @param ui The Ui object for user to interact with
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String editedWord = fullCommand.replaceFirst("find","");
        if (!isValidInput()) {
            Ui.printCommandIsInvalid();
            return;
        }
        for (int i = 1; i <= TaskList.getListSize(); ++i) {
            if (taskList.getTaskAtIndex(i-1).getDescription().contains(editedWord)) {
                Ui.printMatchedIndex(++numberOfMatchedTasks);
                ListCommand.printList(taskList, i);
            }
        }
        if (numberOfMatchedTasks == 0) {
            Ui.printNoMatchedTasks();
        }
    }

    /**
     * This method checks for the error case where input is just the command without index number
     * @return false if the input is just the command without index number
     */
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
