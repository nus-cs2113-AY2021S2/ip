package duke.commands;

import java.time.LocalDateTime;

import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandTimeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents the deadline command given by the user.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser)
            throws EmptyCommandArgException, InvalidCommandTimeException {
        String commandType = "deadline";
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException(commandType);
        }
        String description = parser.getDescription(commandType, commandArg);
        LocalDateTime by = parser.getDateTime(commandType, commandArg);
        Deadline task = new Deadline(description, by);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }
}