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

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    public EventCommand(String commandArgs) {
        super(CommandType.EVENT, commandArgs);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
