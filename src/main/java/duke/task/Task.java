package duke.task;

import duke.exception.TaskAlreadyCompletedException;
import duke.exception.TaskNotExistException;

public class Task {
    private boolean isDone;
    private String description;
    private static int taskCounter = 0;
    private static int completedTaskCounter = 0;
    private static Task[] tasks = new Task[100];
    private static int completedTaskIndex = 0;

    public Task(String description){
        this.description = description;
        // default status for isDone is false
        isDone = false;
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

    public static Task[] getTaskList() {
        return tasks;
    }

    public static void addNewTask(Task newTask) {
        tasks[taskCounter] = newTask;
        incrementTaskCounter();
    }

    public static void completeTask(int taskNumber) {
        tasks[taskNumber].markAsDone();
        completedTaskIndex = taskNumber;
        completedTaskCounter++;
    }

    public static Task getLatestTask(boolean getCompletedTask) {
        if (getCompletedTask) {
            return tasks[completedTaskIndex];
        } else {
            return tasks[getTaskCounter() - 1];
        }
    }

    public static void incrementTaskCounter(){
        taskCounter++;
    }

    // standard string return format for all tasks subclasses expand on this format
    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getDescription();
    }

    // Check for exceptions
    public static void checkDoneTask(int task) throws TaskAlreadyCompletedException, TaskNotExistException {
        if (task > taskCounter - 1) {
            throw new TaskNotExistException();
        }
        if (tasks[task].isDone()) {
            throw new TaskAlreadyCompletedException();
        }
    }

}
