package duke.commands;

import duke.task.Event;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.time.LocalDateTime;

import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandTimeException;

public class EventCommand extends Command {

    public EventCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser)
            throws EmptyCommandArgException, InvalidCommandTimeException {
        String commandType = "event";
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException(commandType);
        }
        String description = parser.getDescription(commandType, commandArg);
        LocalDateTime at = parser.getDateTime(commandType, commandArg);
        Event task = new Event(description, at);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }
}