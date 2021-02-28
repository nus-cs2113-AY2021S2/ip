package duke;

import static duke.common.Constants.NEWLINE;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Task;

/** List of tasks being maintained
 * and number of tasks it has
*/
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int tasksCount = 0;

    public void addTaskToList(Task task) {
        tasks.add(task);
        tasksCount++;
    }

    public void deleteTaskFromList(int taskIndex) {
        tasks.remove(taskIndex);
        tasksCount--;
    }

    public int getTasksCount() {
        return tasksCount;
    }

    public Task getTaskAtIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public String convertToText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasksCount; i++) {
            text.append(tasks.get(i).toString());
            text.append(NEWLINE);
        }
        return text.toString();
    }
}
