package command;

import exception.UnknownCommandException;
import parser.TaskParser;
import task.Task;
import task.TaskList;
import ui.Printer;

public class AddTask {

    public static void addNewTask(String input) {
        try {
            Task taskToAdd = TaskParser.processTaskToAdd(input);
            if (taskToAdd != null) {
                addTaskSuccessful(taskToAdd);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTaskSuccessful(Task taskToAdd) {
        TaskList.tasks.add(taskToAdd);
        TaskList.taskCount++;
        Printer.addTaskSuccessfulMessage(taskToAdd, TaskList.taskCount);
    }
}
