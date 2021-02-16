package duke.command;

import duke.task.Event;
import duke.ui.Menu;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class EventCommand extends Command {

    public EventCommand(String commandArgs) {
        super(CommandType.EVENT, commandArgs);
    }

    @Override
    public void execute(ArrayList<Task> tasks) throws MissingDescriptionException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] eventArgs = commandArgs.split("\\s+/at\\s+", 2);
        Task task = new Event(eventArgs[0], eventArgs[1]);
        tasks.add(task);
        Menu.printAddedTask(task);
    }
}
