package duke.task;

import duke.exception.TaskAlreadyCompletedException;
import duke.exception.TaskNotExistException;

import java.util.ArrayList;

public class Task {
    private boolean isDone;
    private String description;
    private static int taskCounter = 0;
    private static int completedTaskCounter = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Task recentTask;

    public Task(String description){
        this.description = description;
        // default status for isDone is false
        isDone = false;
    }

    public static ArrayList<Task> find(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                matches.add(task);
            }
        }
        return matches;
    }
    

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "[ \u2713 ] " : "[ \u2718 ] ");
    }

    // static methods for all the Tasks
    public static int getTaskCounter() {
        return taskCounter;
    }

    public static int getRemainingTasks() {
        return taskCounter - completedTaskCounter;
    }

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void addNewTask(Task newTask) {
        tasks.add(newTask);
        incrementTaskCounter();
    }

    public static void deleteTask(int taskNumber){
        recentTask = tasks.get(taskNumber);
        if (tasks.get(taskNumber).isDone) {
            completedTaskCounter--;
        }
        tasks.remove(taskNumber);
        System.out.println("The deleted task is : " + recentTask);
        taskCounter--;
    }


    public static void completeTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        recentTask = tasks.get(taskNumber);
        completedTaskCounter -= -1;
    }

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
