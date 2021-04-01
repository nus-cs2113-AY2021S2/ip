package task;

import storage.DukeReader;

import java.util.ArrayList;

/**
 * Represents a class containing the task list and the number of tasks in the list.
 */
public class TaskList {
    private static int taskCount = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
        taskCount--;
    }

    public static void loadTasks() {
        tasks = DukeReader.getTaskListFromFile();
        taskCount = tasks.size();
    }
}
