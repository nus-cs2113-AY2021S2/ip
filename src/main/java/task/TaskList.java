package task;

import exception.TaskAlreadyDoneException;

import exception.DoneFormatException;
import exception.DeleteFormatException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represent a list of different types of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

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


    public void setTaskDone(int index) throws IndexOutOfBoundsException, TaskAlreadyDoneException {

        if(tasks.get(index).getDone()){
            throw new TaskAlreadyDoneException();
        }
        tasks.get(index).setDone(true);
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException{
        tasks.remove(index);
    }

}
