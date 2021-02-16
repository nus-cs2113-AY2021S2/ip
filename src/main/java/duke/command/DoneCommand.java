package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.parser.Parser;
import duke.ui.Menu;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends Command {

    public DoneCommand(String commandArgs) {
        super(CommandType.DONE, commandArgs);
    }

    @Override
    public void execute(ArrayList<Task> tasks) throws InvalidTaskNumberException {
        if (!Parser.isValidTaskNumber(tasks, commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1;
        Task task = tasks.get(taskIndex);
        if (task.isDone()) {
            Menu.printText("Task already marked as done!");
            return;
        }
        task.setDone(true);
        Task.saveAllTasks(tasks);
        Menu.printText("Nice! I've marked this task as done:"
                + System.lineSeparator()
                + "\t"
                + task);
    }
}
