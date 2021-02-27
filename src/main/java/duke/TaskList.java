package duke;

import duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    public void deleteTaskAtIndex(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addToTaskList(Task task) {
        taskList.add(task);
    }

}
