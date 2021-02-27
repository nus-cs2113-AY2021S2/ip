package command;

import constant.Constants;
import task.Task;
import task.TaskList;
import ui.Printer;

/**
 * Represents the delete command, static method is called to delete task from task list.
 */
public class DeleteTask {
    /**
     * Checks whether the task can be deleted from the task list successfully and does so if so.
     * @param input The full input entered by the user.
     */
    public static void deleteTask (String input) {
        String substr = input.substring(Constants.DELETE_STRING_LENGTH).trim();
        try {
            int taskIndex = Integer.parseInt(substr) - 1;
            Task taskToDelete = TaskList.getTask(taskIndex);
            TaskList.removeTask(taskToDelete);
            Printer.deleteTaskSuccessfulMessage(taskToDelete, TaskList.getTaskCount());
        } catch (IndexOutOfBoundsException e) {
            Printer.taskIndexOutOfBoundsMessage();
        } catch (NumberFormatException e) {
            Printer.invalidDeleteFormatMessage();
        }

    }
}
