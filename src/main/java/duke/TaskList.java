package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles underlying operations on task ArrayList
 */
public class TaskList {
    /**
     * ArrayList that stores all the tasks data.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs new ArrayList if no data is provided.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Assign the existing ArrayList with task data as the task storage.
     *
     * @param tasks ArrayList consists of existing task data.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add new task to the task storage.
     *
     * @param task Task to be added into the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove specified task from the task storage.
     *
     * @param task Task to be removed from the task list.
     */
    public void delete(Task task) {
        tasks.remove(task);
    }

    /**
     * Mark the specified task as done.
     *
     * @param task Task to be marked as done.
     */
    public void markAsDone(Task task) {
        task.markAsDone(true);
    }

    /**
     * Find tasks that contains user entered keyword.
     *
     * @param keyword Keyword to be searched in task descriptions.
     * @return Task list containing all tasks that contains keyword.
     */
    public TaskList find(String keyword) {
        TaskList matchedTasks = new TaskList();

        for (Task task : tasks) {
            String lowercaseTaskDescription = task.getDescription().toLowerCase();
            if (lowercaseTaskDescription.contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

    /**
     * Retrieves and returns the underlying ArrayList for storing tasks.
     *
     * @return The task list used for storing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Obtains and returns the current task list size.
     *
     * @return The number of tasks in the ArrayList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves specified task from task list.
     *
     * @param index Index of task in the task list.
     * @return The retrieved task based on specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
