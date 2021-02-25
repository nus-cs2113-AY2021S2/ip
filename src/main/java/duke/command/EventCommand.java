package duke.command;

import duke.common.Utils;
import duke.exception.DukeException;
import duke.exception.InvalidEventException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Command to add event task
 */
public class EventCommand extends Command {
    /**
     * Constructor for EventCommand. Takes in command arguments, sets the command type and arguments.
     * @param commandArgs
     */
    public EventCommand(String commandArgs) {
        super(CommandType.EVENT, commandArgs);
    }

    /**
     * Handles adding event task, saving and printing output information.
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
        String[] eventArgs = commandArgs.split("\\s+/at\\s+", 2);

        LocalDateTime dateTime = Utils.getDateTimeFromUserInput(eventArgs[1]);
        if (!Utils.isValidEvent(dateTime)) {
            throw new InvalidEventException();
        }
        Task task = new Event(eventArgs[0], dateTime);
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
