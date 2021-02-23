package duke.commands;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandTimeException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyCommandArgException, InvalidCommandTimeException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("deadline");
        }
        String[] taskDescriptionAndBy = splitCommandArg("deadline", commandArg);  
        String description = taskDescriptionAndBy[0];
        String by = taskDescriptionAndBy[1];
        Deadline task = new Deadline(description, by);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }
}