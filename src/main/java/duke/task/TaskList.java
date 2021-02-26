package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= tasks.size(); i++) {
            list += ("\t" + i + ". ");
            list += (tasks.get(i - 1) + "\n");
        }
        return list;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}
