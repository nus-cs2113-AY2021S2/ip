package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(Task task) {
        tasks.remove(task);
    }

    public void markAsDone(Task task) {
        task.markAsDone(true);
    }

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
