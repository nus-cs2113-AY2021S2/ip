package duke.data;

import duke.data.task.Task;
import duke.exception.TaskNotFoundException;

import java.util.*;

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
    public TaskList(Collection<Task> tasks){
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
    public void remove(Task toRemove) throws TaskNotFoundException {
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
    }

    public ArrayList getTaskList() {
        return internalList;
    }
}
