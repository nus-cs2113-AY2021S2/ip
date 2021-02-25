package commands;

import io.DukePrint;
import models.TaskList;
import models.Todo;

public class AddToDoCommand extends AddCommand {

    public AddToDoCommand(TaskList taskList, DukePrint dukePrint,
                          String name) {
        super(taskList, dukePrint);
        taskToAdd = new Todo(name);
    }
}