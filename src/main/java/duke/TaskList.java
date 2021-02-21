package duke;

import duke.error.EmptyNameFieldException;
import duke.error.IllegalAccessException;
import duke.error.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
