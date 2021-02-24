package duke.commands;

import duke.exception.EmptyCommandArgException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

public class FindCommand extends Command {

    public FindCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) throws EmptyCommandArgException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("find");
        }
        TaskList filteredList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription();
            if (description.contains(commandArg)) {
                filteredList.addTask(task);
            }
        }

        if (filteredList.getListSize() == 0) {
            ui.displayNoResultsFoundMessage(commandArg);
        } else {
            ui.printFindCommandResults(filteredList);
        }
    }
}