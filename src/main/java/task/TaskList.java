package task;

import exception.TaskAlreadyDoneException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represent a list of different types of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public int getTaskCount(){
        return tasks.size();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Iterator<Task> getIterator(){
        return tasks.iterator();
    }

    /**
     * Mark the task at given index as done.
     * @param index index of the task
     * @throws IndexOutOfBoundsException if the index is out of bound.
     * @throws TaskAlreadyDoneException if the task at given index was marked as done before.
     */
    public void setTaskDone(int index) throws IndexOutOfBoundsException, TaskAlreadyDoneException {

        if(tasks.get(index).getDone()){
            throw new TaskAlreadyDoneException();
        }
        tasks.get(index).setDone(true);
    }

    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Delete the task at given index.
     * @param index index of the task.
     * @throws IndexOutOfBoundsException if the index is out of bound.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException{
        tasks.remove(index);
    }

    public void clearTasks(){
        tasks.clear();
    }

}
