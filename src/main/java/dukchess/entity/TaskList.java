package dukchess.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Data Access Object class that provides convenience methods for CRUD operations on the list of tasks in the app
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    private TaskList() { } // ensure TaskList can never be instantiated

    /**
     * Set the list of tasks initially
     * @param tasks - list of tasks
     */
    public static void setTaskList(ArrayList<Task> tasks) {
        if (TaskList.tasks == null) {
            TaskList.tasks = tasks;
        }
    }

    /**
     * Gets the list of tasks in the program at the moment
     * @return a list of tasks
     */
    public static ArrayList<Task> getTasksList() {
        return tasks;
    }

    /**
     * Deletes a task with a given task ID.
     * @param taskIdToDelete - the id of the task to delete
     * @return the outcome as a message
     */
    public static String deleteTask(int taskIdToDelete) {
        try {
            Task taskToDelete = tasks.get(taskIdToDelete - 1);
            tasks.remove(taskIdToDelete - 1);
            return String.format("Noted, I've removed this task:\n"
                    + "%s\n"
                    + "Now, you have %d tasks in the list.", taskToDelete.toString(), tasks.size());
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return String.format("There is no task with id %d at the moment.", taskIdToDelete);
        }
    }

    /**
     * Finds tasks with a given keyword.
     * @param keyword - the keyword to search tasks on
     * @return a list of tasks matching the keyword
     */
    public static List<Task> findTask(String keyword) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                searchResults.add(task);
            }
        };
        return searchResults;
    };

}
