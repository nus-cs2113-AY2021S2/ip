package command;

import exception.UnknownCommandException;
import parser.TaskParser;
import task.Task;
import task.TaskList;
import ui.Printer;

/**
 * Represents the add command, has static methods that are called to add a new task to task list.
 */
public class AddTask {
    /**
     * Checks whether the task can be added successfully to the task list and does so if so.
     *
     * @param input The full entered by the user.
     */
    public static void addNewTask(String input) {
        try {
            Task taskToAdd = TaskParser.parse(input);
            if (taskToAdd != null) {
                addTaskSuccessful(taskToAdd);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a task to the task list
     *
     * @param taskToAdd The task to add to the task list
     */
    public static void addTaskSuccessful(Task taskToAdd) {
        TaskList.addTask(taskToAdd);
        Printer.addTaskSuccessfulMessage(taskToAdd, TaskList.getTaskCount());
    }
}
