package command;
import constant.Constants;
import task.Task;
import task.TaskList;
import ui.Printer;

import java.util.ArrayList;

/**
 * Represents the find command, static method is called to find tasks that match the keyword.
 */
public class FindTask {
    /**
     * Finds the matching tasks in the task list, and calls on Printer to print them.
     * If no matching tasks are found, call on Printer to print message indicating so.
     * @param input The full input the user has entered.
     */
    public static void findTask(String input){
        String keyword = input.substring(Constants.FIND_STRING_LENGTH).trim();
        if (keyword.isBlank()) {
            Printer.keyWordCannotBeEmptyMessage();
            return;
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: TaskList.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            Printer.printNoMatchingTasks(keyword);
        } else {
            Printer.printMatchingTaskList(matchingTasks);
        }
    }
}
