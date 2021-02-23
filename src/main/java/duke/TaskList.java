package duke;

import java.util.ArrayList;
import duke.task.*;

/**
 * Represents a list of tasks. A TaskList object corresponds to a list of tasks represented by 
 * their type e.g. todo, deadline and event. 
 */
public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Creates a list with the given data. 
     * 
     * @param tasks List containing all tasks retrieved form storage. 
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty list. 
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Checks if list is empty.
     * 
     * @return True if sizeOfTaskList is 0 and false if not.
     */
    public boolean isTaskListEmpty() {
        return getSize() == 0;
    }
    
    /**
     * Gets a task object from the tasks list.
     * 
     * @param index Location of the task on the tasks list.
     * @return The task at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the length of task list. 
     * 
     * @return Length of task list. 
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes a task from the task list. 
     * 
     * @param taskNumber Number of the task in the task list to delete (starts with 1). 
     */
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Adds a task to the task list. 
     * 
     * @param task Task to add. 
     */
	public void addToTaskList(Task task) {
        tasks.add(task);
    }
}
