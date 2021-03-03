package models;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Removes a Task from the TaskList
     *
     * @param index Index of the Task to be removed
     * @return TaskList after removing the Task
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Adds a Task to the TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * @return Size of the TaskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the specified Task using its Index
     *
     * @param index Index of the Task to be queried
     * @return Task of the indexed location in the TaskList
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the ArrayList of Tasks
     *
     * @return taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Sets the current ArrayList of Tasks to the specified new ArrayList of Tasks
     *
     * @param taskList ArrayList of Tasks to be set
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
