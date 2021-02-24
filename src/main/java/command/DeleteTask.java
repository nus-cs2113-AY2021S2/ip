package command;

import constant.Constants;
import task.Task;
import task.TaskList;
import ui.Printer;

public class DeleteTask {
    public static void deleteTask (String input) {
        String substr = input.substring(Constants.DELETE_STRING_LENGTH).trim();
        int taskIndex = Integer.parseInt(substr) - 1;
        try {
            Task taskToDelete = TaskList.tasks.get(taskIndex);
            TaskList.tasks.remove(taskIndex);
            TaskList.taskCount--;
            Printer.deleteTaskSuccessfulMessage(taskToDelete, TaskList.taskCount);
        } catch (IndexOutOfBoundsException e) {
            Printer.taskIndexOutOfBoundsMessage();
        }

    }
}
