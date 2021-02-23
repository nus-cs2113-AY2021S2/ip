package duke.commands;

import duke.task.Event;
import duke.task.TaskList;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandTimeException;

public class EventCommand extends Command {

    public EventCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyCommandArgException, InvalidCommandTimeException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("event");
        }
        String[] taskDescriptionAndAt = splitCommandArg("event", commandArg);       
        String description = taskDescriptionAndAt[0];
        String at = taskDescriptionAndAt[1];
        Event task = new Event(description, at);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }
}