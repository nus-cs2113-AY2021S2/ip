package commands;

import task.Task;
import exceptions.EmptyNumberException;

public class DoneTask extends Command {

    public static void execute(String commandArgs) throws EmptyNumberException {
        if (commandArgs.equals("")) {
            throw new EmptyNumberException();
        }
        int taskIndex = Integer.parseInt(commandArgs) - constants.ARRAY_OFFSET;
        Task taskToBeDone = taskManager.getTask(taskIndex);
        taskToBeDone.markAsDone();
        showTaskDone(taskToBeDone);
    }

    public static void showTaskDone(Task task) {
        System.out.println(messages.MESSAGE_TASK_DONE);
        System.out.println(constants.INDENTATION + task.toString());
    }
}
