package duke.command;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Command to find tasks by keyword
 */
public class FindCommand extends Command {
    /**
     * Constructor for FindCommand. Takes in command arguments, sets command type and arguments.
     * @param commandArgs command arguments from user input
     */
    public FindCommand(String commandArgs) {
        super(CommandType.FIND, commandArgs);
    }

    /**
     * Handles finding tasks that match the keyword, and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matches = tasks.findTasksByKeyword(commandArgs);
        if (matches.size() == 0) {
            ui.printText(Messages.INFO_TASKS_NOT_FOUND + commandArgs);
            return;
        }
        ui.printText(Messages.INFO_FOUND_TASKS);
        matches.stream()
                .forEach((m) -> ui.printText(" " + tasks.getTaskNumber(m) + ". " + m));
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
