package command;
import constant.Constants;
import exception.EmptyCommandException;
import task.Task;
import task.TaskList;
import ui.Printer;

import java.util.ArrayList;

public class FindTask {

    public static void findTask(String input){
        String keyword = input.substring(Constants.FIND_STRING_LENGTH).trim();
        if (keyword.isBlank()) {
            Printer.keyWordCannotBeEmptyMessage();
            return;
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: TaskList.tasks) {
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
