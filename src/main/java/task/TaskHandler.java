package task;

import printer.Printer;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * TaskHandler class for handling the entire collection of tasks.
 */
public class TaskHandler {
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static int taskCount = 0;
    private static final Printer printer = new Printer();

    /**
     * Add a task to the collection.
     *
     * @param task is a task given by the calling method.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Increase the task count.
     */
    public void increaseTaskCount() {
        taskCount++;
    }

    /**
     * Delete a task from the collection.
     *
     * @param index is the index of the task in the collection.
     */
    public void deleteTask(int index) {
        taskArrayList.remove(index);
    }

    /**
     * Decrease the task count.
     */
    public void decreaseTaskCount() {
        taskCount--;
    }

    /**
     * Return the current total task count.
     *
     * @return the current total task count.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Check whether the task is marked as done or undone.
     *
     * @param index is the index of the task in the collection.
     * @return the true or false based on the status of the task.
     */
    public boolean checkStatus(int index) {
        return taskArrayList.get(index).isDone();
    }

    /**
     * Mark the task as done.
     *
     * @param taskNumber is the index of the task in the collection.
     */
    public void markDone(int taskNumber) {
        taskArrayList.get(taskNumber).setTaskAsDone();
    }

    /**
     * Find the task that matches the keyword given.
     * Print the tasks (if any).
     *
     * @param keyword is the keyword specified.
     */
    public void findByWord(String keyword) {
        boolean isFound = false;
        System.out.println("Processing keyword searches, please wait.");
        for (int i = 0; i < taskCount; i++) {
            if (taskArrayList.get(i).getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.print((i+1) + ".");
                printer.printTaskDetails(i);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Oh no mushroom head could not find any matches!");
        } else {
            System.out.println("Search is complete!");
        }
    }

    /**
     * Convert the task list to the file writing format.
     *
     * @return the string containing the correct file writing format.
     */
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Task currentTask : taskArrayList) {
            joiner.add(currentTask.getFileFormat());
        }
        return joiner.toString();
    }

    /**
     * Extract the task timing.
     *
     * @param index is the index of the task in the collection.
     * @return the string containing the task timing.
     */
    public String extractTaskTiming(int index) {
        return taskArrayList.get(index).getTaskTiming();
    }

    /**
     * Extract the task type.
     *
     * @param index is the index of the task in the collection.
     * @return the string containing the task type.
     */
    public String extractTaskSymbol(int index) {
        return taskArrayList.get(index).getTaskSymbol();
    }

    /**
     * Extract the given task status.
     *
     * @param index is the index of the task in the collection.
     * @return the string containing the task status.
     */
    public String extractTaskStatus(int index) {
        return taskArrayList.get(index).getTaskStatus();
    }

    /**
     * Extract the given task description.
     *
     * @param index is the index of the task in the collection.
     * @return the string containing the task description.
     */
    public String extractTaskDescription(int index) {
        return taskArrayList.get(index).getTaskDescription();
    }
}
