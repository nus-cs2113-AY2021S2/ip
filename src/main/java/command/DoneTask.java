package command;

import task.Task;
import task.TaskList;
import ui.Printer;

/**
 * Represents the done command, static method is called to mark a task as done in the task list.
 */
public class DoneTask {
    /**
     * Checks whether the task index is valid, marks the task as done if so.
     * @param input The full input the user has entered.
     */
    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            Task taskToMarkDone = TaskList.getTask(taskIndex);
            taskToMarkDone.markDone();
            Printer.taskMarkedAsDoneMessage(taskToMarkDone);
        } catch (IndexOutOfBoundsException e) {
            Printer.taskIndexOutOfBoundsMessage();
        } catch (NumberFormatException e) {
            Printer.invalidDoneFormatMessage();
        }
    }
}
