package commands;

import task.Task;
import exceptions.EmptyNumberException;

public class DeleteTask extends Command{

    /**
     * delete task given index
     * @param commandArgs index given in String
     * @throws EmptyNumberException when no index provided
     */
    public static void execute(String commandArgs) throws EmptyNumberException {
        if (commandArgs.equals("")) {
            throw new EmptyNumberException();
        }
        int taskIndex = Integer.parseInt(commandArgs) - constants.ARRAY_OFFSET;
        Task taskToBeDeleted = taskManager.getTask(taskIndex);
        taskManager.deleteTask(taskIndex);
        showTaskDeleted(taskToBeDeleted);
    }

    /**
     * display message when task deleted
     * @param task details of task deleted
     */
    public static void showTaskDeleted(Task task) {
        System.out.println(messages.MESSAGE_TASK_REMOVED);
        System.out.println(constants.INDENTATION + task.toString());
        System.out.println("Now you have " + (taskManager.getTaskCount()) + " tasks in the list.");
    }
}
