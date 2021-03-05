import java.util.ArrayList;

/**
 * Maintains a list of tasks provided by the user, and handles operations relating to the list.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;

    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    public void addTask(Task taskToAdd) {
        storedTasks.add(taskToAdd);
    }

    public Task getTaskAt(int taskIndex) {
        return storedTasks.get(taskIndex);
    }

    public Task deleteTask(int taskIndex) {
        return storedTasks.remove(taskIndex);
    }

    public boolean isEmpty() {
        return storedTasks.isEmpty();
    }

    public int getTaskCount() {
        return storedTasks.size();
    }
}
