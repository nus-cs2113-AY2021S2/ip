package tasks;

import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    public int getTaskCount() {
        return taskCount;
    }


    /**
     * Returns task at specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * Adds new task to task list.
     *
     * @param task New Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }


    /**
     * Removes task at index in the task list.
     *
     * @param index Index of task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
        taskCount--;
    }


    /**
     * Clears task list.
     */
    public void reset() {
        taskCount = 0;
        tasks.clear();
    }

}
