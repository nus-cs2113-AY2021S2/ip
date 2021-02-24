package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList () {
    }

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

    public Task get (int taskIndex) {
        return tasks.get(taskIndex);
    }

}
