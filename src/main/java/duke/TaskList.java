package duke;

import java.io.IOException;
import java.util.ArrayList;
import duke.task.*;

public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * If data found, create a lit with stored data. 
     * 
     * @param tasks List containing all tasks retrieved form storage. 
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * If Storage data not found, create an empty list. 
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Check if list is empty.
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

    public int getSize() {
        return tasks.size();
    }

    public void removeTask(int taskNumber) throws IOException{
        tasks.remove(taskNumber);
    }

	public void addToTaskList(Task task) throws IOException{
        tasks.add(task);
    }

}
