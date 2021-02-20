package duke.storage;
import duke.tasks.Task;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;
    private int count = 0;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // GETTERS & SETTERS
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // METHODS
    public void add(Task t) {
        taskList.add(t);
        this.count = taskList.size();
    }
    
    public Task get(int index) {
        return taskList.get(index);
    }
    
    public void remove(int index) {
        taskList.remove(index);
        this.count = taskList.size();
    }
    
    public boolean contains(Task t) {
        return taskList.contains(t);
    }
    
    
}
