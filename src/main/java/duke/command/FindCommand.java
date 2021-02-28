package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles find command.
 */
public class FindCommand extends Command {
    /**
     * Keyword used for searching in tasks.
     */
    private String keyword;

    /**
     * Constructs a new find command.
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Filter tasks which contains keyword specified in find command.
     * Prints out resulting filtered tasks in task list.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMatchingTasks(taskList.find(getKeyword()));
    }

    /**
     * {@inheritDoc}
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
