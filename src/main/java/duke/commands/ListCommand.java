package duke.commands;

import duke.data.exceptions.InvalidCommandException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.common.Messages.MESSAGE_LIST_TASK_NONE;

public class ListCommand extends Command {
    public static final String LIST_WORD = "list";

    public ListCommand(String commandArgs) throws InvalidCommandException {
        if (commandArgs.length() > 0) {
            // This command is not supposed to have arguments.
            throw new InvalidCommandException();
        }
    }

    /**
     * Lists all tasks in the TaskList object.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     */
    private void listTasks(TaskList tasks, TextUI ui) {
        if (tasks.isEmpty()) {
            ui.printStatements(MESSAGE_LIST_TASK_NONE);
        } else {
            tasks.printAllTasks(ui);
        }
    }

    @Override
    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        listTasks(tasks, ui);
    }
}
