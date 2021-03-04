package duke.data;

import duke.data.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the entire task list. Contains the data of the task list
 */
public class TaskList {

    private ArrayList<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty task list.
     */
    public TaskList(){
    }

    /**
     * Constructs a task list with the given tasks.
     */
    public TaskList(Task... tasks) {
        final List<Task> initialList = Arrays.asList(tasks);
        internalList.addAll(initialList);
    }

    /**
     * Constructs a list from the items in the given collection.
     * @param tasks a collection of persons
     */
    public TaskList(ArrayList<Task> tasks){
        internalList.addAll(tasks);
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task toAdd) {
        internalList.add(toAdd);
    }

    /**
     * Removes the task object from the list.
     */
    public void remove(Task toRemove) throws DukeException {
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new DukeException("Target task no found!");
        }
    }

    public ArrayList getTaskList() {
        return internalList;
    }

    public Task getTaskByIndex(int index) {
        return internalList.get(index);
    }

    public int size() {
        return internalList.size();
    }
}
