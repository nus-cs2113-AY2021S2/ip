package duke;

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

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int indexToDelete) throws IndexOutOfBoundsException {
        tasks.remove(indexToDelete);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

}
