package duke.commands;

import duke.data.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Find a task with the search keyword in its description.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find all task descriptions containing the given keyword.\n"
            + "Example: " + COMMAND_WORD
            + "book";

    private final String searchKey;

    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public CommandResult execute() throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            throw new DukeException("Empty list. Nothing to be displayed.");
        }
        String taskListDisplay = "Here are the matching tasks in your list:\n";
        // Create an iterator for the list
        Iterator<Task> iter = taskList.iterator();
        // Displaying the values after iterating through the list
        int counter = 0;
        for (Task task : taskList) {
            if (task.getDescription().contains(searchKey)) {
                counter++;
                taskListDisplay += String.format("%1$d. %2$s\n", counter, task.toString());
            }
        }

        return new CommandResult(taskListDisplay);
    }
}
