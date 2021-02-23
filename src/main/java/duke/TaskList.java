package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> Tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.Tasks = new ArrayList<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }
}
