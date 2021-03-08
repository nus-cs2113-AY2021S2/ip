package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TaskList object used in the Duke Application
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class TaskList implements Serializable {

    private final ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList object needed by Duke.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds task object to taskList
     *
     * @param task task object created by user or loaded from save file
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves task object from task list.
     *
     * @param indexOfTask the index which represents the position of the desired task object in the ArrayList
     * @return desired task object
     */
    public Task get(int indexOfTask) {
        Task task;
        task = taskList.get(indexOfTask);
        return task;
    }

    /**
     * Retrieves number of task objects in the list.
     *
     * @return number of task objects in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Obtains formatted format of entire task list
     *
     * @return Formatted string of the User's task list
     */
    public String taskListAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("\t\t%d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Deletes a task from the task list using its index
     *
     * @param targetIndex is the index of the task that is to be deleted
     */
    public void deleteTask(int targetIndex) {
        taskList.remove(targetIndex);
    }

    /**
     * Finds and returns task list of task containing the specified keywords
     *
     * @param keywords is the words or phrases to match the task
     * @return task list containing the tasks that has been matched
     */
    public TaskList findMatchedTask(String keywords) {
        TaskList matchedTaskList = new TaskList();
        for (Task task : taskList) {
            if (task.description.toLowerCase().contains(keywords)) {
                matchedTaskList.add(task);
            }
        }
        return matchedTaskList;
    }
}
