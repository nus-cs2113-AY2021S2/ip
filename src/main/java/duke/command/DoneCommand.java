package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.ui.Menu;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends Command {

    public DoneCommand(String commandArgs) {
        super(CommandType.DONE, commandArgs);
    }

    @Override
    public void execute(ArrayList<Task> tasks) throws InvalidTaskNumberException {
        if (!isValidNumber(commandArgs) || !isValidTaskNumber(Integer.parseInt(commandArgs))) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskNumber = Integer.parseInt(commandArgs);

        Task task = tasks.get(taskNumber - 1);
        if (task.isDone()) {
            Menu.printText("duke.task.Task already marked as done!");
            return;
        }
        task.setDone(true);
        Task.saveAllTasks(tasks);
        Menu.printText("Nice! I've marked this task as done:"
                + System.lineSeparator()
                + "\t"
                + task);
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= Task.getNumberOfTasks());
    }

    private static boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
