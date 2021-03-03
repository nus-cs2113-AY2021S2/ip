package task;

import java.util.ArrayList;

/**
 * Manage task list manipulation commands
 */
public class TaskManager {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
