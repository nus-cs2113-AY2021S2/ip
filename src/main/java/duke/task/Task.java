package duke.task;

import duke.exception.TaskAlreadyCompletedException;
import duke.exception.TaskNotExistException;

import java.util.ArrayList;

/**
 * The main building block for this application
 */
public class Task {
    private boolean isDone;
    private String description;
    private static int taskCounter = 0;
    private static int completedTaskCounter = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Task recentTask;

    /**
     * @param description main content of the Task object
     */
    public Task(String description){
        this.description = description;
        // default status for isDone is false
        isDone = false;
    }

    /**
     * @param query contains the search query from user input
     * @return a list of tasks with any match to the search query
     */
    public static ArrayList<Task> find(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                matches.add(task);
            }
        }
        return matches;
    }


    /**
     * Accessor method
     * @return a boolean value signalling whether a task is done or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Accessor method
     * @return the description of the Task, in a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Convenient method to easily mark any task as complete without much unnecessary manipulation
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * @return a status icon corresponding to whether a task is completed or not
     */
    public String getStatusIcon(){
        return (isDone ? "[ \u2713 ] " : "[ \u2718 ] ");
    }

    /**
     * @return an integer representing the number of tasks presently in the application
     */
    // static methods for all the Tasks
    public static int getTaskCounter() {
        return taskCounter;
    }

    /**
     * @return an integer indicating the number of tasks that are yet to be completed
     */
    public static int getRemainingTasks() {
        return taskCounter - completedTaskCounter;
    }

    /**
     * @return a complete list of tasks in the application
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * @param newTask is the task to be added
     */
    public static void addNewTask(Task newTask) {
        tasks.add(newTask);
        incrementTaskCounter();
    }

    /**
     * @param taskNumber is the corresponding index of the task to be deleted
     */
    public static void deleteTask(int taskNumber){
        recentTask = tasks.get(taskNumber);
        if (tasks.get(taskNumber).isDone) {
            completedTaskCounter--;
        }
        tasks.remove(taskNumber);
        System.out.println("The deleted task is : " + recentTask);
        taskCounter--;
    }


    /**
     * @param taskNumber is the index of the task to be marked as completed
     */
    public static void completeTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        recentTask = tasks.get(taskNumber);
        completedTaskCounter -= -1;
    }

    /**
     * @param manipulatedTask should be true if the task to be returned was recently deleted, otherwise it will by
     * default return the task that was most recently added to the application
     * @return Task depending on the above
     */
    public static Task getRecentTask(boolean manipulatedTask) {
        if (manipulatedTask) {
            return recentTask;
        } else {
            return tasks.get(getTaskCounter() - 1);
        }
    }

    public static void incrementTaskCounter(){
        taskCounter++;
    }

    public static void incrementCompletedTaskCounter() {
        completedTaskCounter++;
    }

    // standard string return format for all tasks subclasses expand on this format
    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getDescription();
    }

    // Check for exceptions
    public static void checkTaskIndex(int queryTask) throws TaskNotExistException {
        if (queryTask > taskCounter - 1) {
            throw new TaskNotExistException();
        }
    }

    public static void checkTaskComplete(int queryTask) throws  TaskAlreadyCompletedException {
        if (tasks.get(queryTask).isDone()) {
            throw new TaskAlreadyCompletedException();
        }
    }
}
