package command;

import task.Task;
import task.TaskList;
import ui.Printer;

public class DoneTask {
    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        Task taskToMarkDone = TaskList.tasks.get(taskIndex);
        taskToMarkDone.markDone();
        Printer.taskMarkedAsDoneMessage(taskToMarkDone);
    }
}
