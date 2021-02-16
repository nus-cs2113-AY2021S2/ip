package duke.task;

import duke.dao.TaskDaoImpl;

import java.util.ArrayList;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public static ArrayList<Task> loadAllTasks() {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        return taskDao.loadAllTasks();
    }

    public static void saveAllTasks(ArrayList<Task> tasks) {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        taskDao.saveAllTasks(tasks);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
