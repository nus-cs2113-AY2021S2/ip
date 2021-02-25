package duke.task;

import duke.command.DukeExceptions;
import java.util.ArrayList;

/**
 * Represents the object that stores all current tasks
 */
public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return (taskList.size() == 0);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets a task within the task list
     * @param index The index of the task within the arraylist
     * @return The task indexed by the input index
     * @throws DukeExceptions If the index provided is out of range
     */
    public Task getTask(Integer index) throws DukeExceptions {
        if (index > taskList.size()) {
            throw new DukeExceptions();
        }
        return taskList.get(index);
    }

    /**
     * Adds a new task into task list
     * @param newTask The task to be added
     */
    public void appendTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Marks a task as valid
     * @param index The index position of the task
     * @param isCompleted The completion status to be changed
     */
    public void markIsCompletedTask(Integer index, boolean isCompleted) {
        Task currentTask = taskList.get((int)index);
        currentTask.setIsCompleted(isCompleted);
    }

    /**
     * Deletes a task in the task list
     * @param index The index position of the task to delete
     * @throws DukeExceptions If index is out of range
     */
    public void deleteTask(Integer index) throws DukeExceptions{
        if (index > taskList.size()) {
            throw new DukeExceptions();
        }
        taskList.remove((int)index);
    }

    public Integer getSize() {
        return taskList.size();
    }
}
