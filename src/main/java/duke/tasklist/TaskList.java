package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

}
