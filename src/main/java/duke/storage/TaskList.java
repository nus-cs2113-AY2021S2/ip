package duke.storage;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the entire task list used to store user tasks for this program.
 * Encapsulates the methods and variables complementing the task list.
 */

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new, empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Constructs a task list with the given data.
     * 
     * @param tasks an array list of {@code Tasks}
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Fetches the current number of tasks in the task list.
     * 
     * @return the number of tasks currently in the list
     */
    public int getCount() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Returns the task at the specified index of the task list.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes the task at the specified index from the task list.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns true if the {@code Task} is present in the task list.
     */
    public boolean contains(Task t) {
        return taskList.contains(t);
    }
    
    
}
