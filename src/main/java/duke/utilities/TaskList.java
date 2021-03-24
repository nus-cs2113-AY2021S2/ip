package duke.utilities;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructs empty list of task.
     */
    public TaskList () {
    }

    /**
     * Constructs a list of task using stored values.
     *
     * @param tasksData the supply data of a list of task.
     */
    public TaskList(ArrayList<Task> tasksData) {
        this.tasks = tasksData;
    }

    /**
     * Add a task into the TaskList.
     *
     * @param task the task object entered by the user.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a task from the TaskList.
     *
     * @param indexToDelete the index specified to delete
     * @throws IndexOutOfBoundsException if the index is outside of the range of TaskList
     */
    public void deleteTask(int indexToDelete) throws IndexOutOfBoundsException {
        tasks.remove(indexToDelete);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return the size of the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the specific Task object from the TaskList.
     *
     * @param taskIndex the index of the task from the list
     * @return the task object in TaskList of the particular index
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

}
