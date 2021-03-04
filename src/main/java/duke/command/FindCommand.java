package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand is called when user wants to find all tasks whose description contains the queried keyword.
 */
public class FindCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int FIND_LENGTH = 5;

    /**
     * Constructor method of FindCommand which stores the user input.
     *
     * @param fullCommand String of user input for finding tasks with a queried keyword.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Finds all tasks who description contains queried keyword.
     * Prints out matching tasks in an ordered list.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui User Interface.
     * @param storage Storage to save all tasks, updating the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String query = fullCommand.substring(FIND_LENGTH);
            ui.printQueryList(tasks.getTasksByKeyword(query));
        } catch (Exception e) {
            ui.printEmptyQuery();
        }
    }
}