package dukchess.commands;

import static dukchess.entity.TaskList.findTask;

import java.util.List;

import dukchess.entity.Task;
import dukchess.ui.Ui;

/**
 * Command for finding tasks based on a keyword
 */
public class FindCommand extends Command {
    /**
     * Performs input validation before finding a task from the list of tasks.
     * @param keyword - the keyword to search task list on
     */
    public static void handleFindTask(String keyword) {
        if (keyword.length() == 0) {
            Ui.printErrorMessage("You did not specify the keyword to find!");
            return;
        }
        List<Task> searchResults = findTask(keyword);
        if (searchResults.size() == 0) {
            Ui.printErrorMessage(String.format("Could not find any tasks matching \"%s\"", keyword));
            return;
        }
        Ui.printListOfTasks(searchResults);
    }
}
