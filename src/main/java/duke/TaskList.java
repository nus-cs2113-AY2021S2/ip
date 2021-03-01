package duke;

import static duke.common.Constants.NEWLINE;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the list of tasks maintained throughout a run.
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

    /**
     * Generates the data to save to the .txt storage file at the end of a run by converting, item-by-item,
     * the differently-formatted {@code TaskList}.
     * @return a multi-line string representation of {@code TaskList}
     */
    public String convertToText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasksCount; i++) {
            text.append(tasks.get(i).toString());
            text.append(NEWLINE);
        }
        return text.toString();
    }
}
