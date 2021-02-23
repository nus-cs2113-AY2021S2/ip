package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.error.EmptyNameFieldException;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static final int ERR_NO_NAME = -4;

    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    public FindCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            findItems(this.input, tasks);
        } catch (EmptyNameFieldException e) {
            this.ui.printError(ERR_NO_NAME);
        }
    }

    public void findItems(String line, ArrayList<Task> tasks) throws EmptyNameFieldException {
        String searchName = extractSearchName(line);
        this.ui.printFoundItems(searchName, tasks);
    }
    public String extractSearchName(String line) throws EmptyNameFieldException {
        String searchName = line.substring(4).trim();
        if (searchName.length() == 0) {
            throw new EmptyNameFieldException();
        }
        return searchName;
    }
}
