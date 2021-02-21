package jarvis.tasklist;

import jarvis.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * Contains methods to manipulate and get information about the task list
 */
public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    // returns the list
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    // adds a task into the list
    public static void addToTasks(Task task) {
        tasks.add(task);
    }

    // removes a task from the list
    public static void removeFromTasks(Task task) {
        tasks.remove(task);
    }

    // returns the task at a specific index
    public static Task getTaskWithIndex(int index) {
        return tasks.get(index);
    }

    // returns the size of the list
    public static int getSize() {
        return tasks.size();
    }
}
