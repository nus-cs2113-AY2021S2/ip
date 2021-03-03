package Duke;

import Duke.Tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList class that stores the user's task data during the run time of the app into an ArrayList
 */
public class TaskList {

    /**
     * All TaskList object centers around a central ArrayList<Task> object
     */
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Adds a task to the ArrayList
     * @param inputTask
     */
    public void addTask(Task inputTask) {
        tasks.add(inputTask);
    }

    /**
     * Updates a task as completed.
     * @param taskIndex index of task to be updated as completed
     * @return the task that is updated as completed
     */
    public Task updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        return tasks.get(taskIndex - 1);
    }

    /**
     *
     * @return the current size of the ArrayList
     */
    public int size() {
        return tasks.size();
    }

    /**
     *
     * @param index
     * @return the task at the particular index of the ArrayList
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task from the ArrayList
     * @param index of the task to be deleted
     * @return the task that is deleted
     */
    public Task deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Looks through the ArrayList to find an event or deadline that matches the date given by the user
     * @param queryDate date object to be queried for similarity
     * @return an ArrayList of task that has the same date as that queried by the user
     */
    public ArrayList<Task> findByDate(LocalDate queryDate) {
        ArrayList<Task> tasksWithDate = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String taskDateInString = currentTask.getDate().toString();
            if (currentTask.getDate() != null && taskDateInString.equals(queryDate.toString())) {
                tasksWithDate.add(currentTask);
            }
        }
        return tasksWithDate;
    }

    /**
     *
     * @return the entire task list
     */
    public ArrayList<Task> returnTaskList() {
        return tasks;
    }

    /**
     *
     * @param keyword
     * @return the task list of tasks that has names or texts that matches the keyword.
     */
    public ArrayList<Task> returnFilteredTaskList(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String nameOfTask = currentTask.getName();
            if (nameOfTask.contains(keyword)) {
                tasksWithKeyword.add(currentTask);
                continue;
            }
        }
        return tasksWithKeyword;
    }

}