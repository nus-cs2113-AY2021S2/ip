package duke.command;

import duke.task.Deadline;
import duke.ui.Menu;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String commandArgs) {
        super(CommandType.DEADLINE, commandArgs);
    }

    @Override
    public void execute(ArrayList<Task> tasks) throws MissingDescriptionException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] deadlineArgs = commandArgs.split("\\s+/by\\s+", 2);
        Task task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        tasks.add(task);
        Task.saveAllTasks(tasks);
        Menu.printAddedTask(task);
    }
}
