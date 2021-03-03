package duke.command;

import duke.CommandType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import duke.task.Todo;

import java.io.IOException;

public class TodoCmd extends Command{
    public TodoCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
        String[] typeContent = userInput.split("[Tt][Oo][Dd][Oo]",2);
        if (typeContent[1].equals("")) {
            throw new DukeException(CommandType.TODO);
        }

        Todo t = tasks.addTodo(typeContent[1].trim());
        ui.showAddResult(t, tasks.getNumOfTasks());
        try {
            storage.writeToTxt(tasks.getTasks());
        } catch (IOException e) {
            ui.showWriteToFileError();
        }
    }
}
