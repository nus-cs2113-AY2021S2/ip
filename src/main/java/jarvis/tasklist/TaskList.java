package jarvis.tasklist;

import jarvis.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * Contains methods to manipulate and get information about the task list
 */
public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /** Returns the tasks list */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /** Adds a task into the tasks list */
    public static void addToTasks(Task task) {
        tasks.add(task);
    }

    /** Removes a task from the tasks list */
    public static void removeFromTasks(Task task) {
        tasks.remove(task);
    }

    /** returns the task at a specific index in the tasks list */
    public static Task getTaskWithIndex(int index) {
        return tasks.get(index);
    }

    /** returns the size of the tasks list */
    public static int getSize() {
        return tasks.size();
    }

    /**
     * Searches the list for related tasks with a given keyword
     *
     * @param keyword word used to search for a related task in the list
     * @return a new list that contains all the matching tasks
     */
    public static ArrayList<Task> getTasksWithKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
