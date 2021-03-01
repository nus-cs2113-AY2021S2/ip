package duke.command;

import duke.*;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class FindCmd extends Command{
    public FindCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        try {
            if(userInput.trim().toLowerCase().equals("find")) {
                throw new DukeException(CommandType.FIND);
            }
        } catch (DukeException e) {
            e.showMessage(ui);
            return;
        }
        String[] typeString = userInput.split("[Ff][Ii][Nn][Dd]",2);
        ArrayList<Task> filtedTasks = tasks.filterTasksByString(typeString[1].trim());
        ui.showFindResult(filtedTasks);
    }
}
