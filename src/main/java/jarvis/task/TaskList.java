package jarvis.task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * Contains methods to manipulate and get information about the task list
 */
public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void addToTasks(Task task) {
        tasks.add(task);
    }

    public static void removeFromTasks(Task task) {
        tasks.remove(task);
    }

    /** returns the task at a specific index in the tasks list */
    public static Task getTaskWithIndex(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }
}
