package tasks;

import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;


    /**
     * Returns task count.
     */
    public int getTaskCount() {
        return taskCount;
    }


    /**
     * Changes task count.
     *
     * @param value New task count.
     */
    public void setTaskCount(int value) {
        taskCount = value;
    }


    /**
     * Returns task at index.
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
    }


    /**
     * Removes task at index in the task list.
     *
     * @param index Index of task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }


    /**
     * Clears task list.
     */
    public void reset() {
        taskCount = 0;
        tasks.clear();
    }

}
