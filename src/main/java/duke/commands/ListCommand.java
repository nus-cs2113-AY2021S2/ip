package duke.commands;

import duke.data.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            throw new DukeException("Empty list. Nothing to be displayed.");
        }
        String taskListDisplay = "Here are the tasks in your list:\n";
        // Create an iterator for the list
        Iterator<Task> iter = taskList.iterator();
        // Displaying the values after iterating through the list
        int counter = 0;
        while (iter.hasNext()) {
            counter++;
            taskListDisplay+= String.format("%1$d. %2$s\n", counter, iter.next().toString());
        }

        return new CommandResult(taskListDisplay);
    }
}
