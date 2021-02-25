package duke.command;

import duke.common.Utils;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Command to add a task with deadline
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for DeadlineCommand. Takes in command arguments, sets the command type and arguments.
     * @param commandArgs command arguments from user input
     */
    public DeadlineCommand(String commandArgs) {
        super(CommandType.DEADLINE, commandArgs);
    }

    /**
     * Handles adding task with deadline, saving and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] deadlineArgs = commandArgs.split("\\s+/by\\s+", 2);

        LocalDateTime date = Utils.getDateFromUserInput(deadlineArgs[1]);
        if (!Utils.isValidDeadline(date)) {
            throw new InvalidDeadlineException();
        }

        Task task = new Deadline(deadlineArgs[0], date);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.printAddedTask(task);
        ui.printTotalTasks(tasks.getTasks());
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
