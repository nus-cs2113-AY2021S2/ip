package duke.command;

import duke.*;
import duke.task.Task;
import java.util.ArrayList;

public class FindCmd extends Command{
    public FindCmd(String s) {
        super(s);
    }

    /**
     * Executes a find command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if the date user input is not in form of yyyy-mm-dd
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException{
        try {
            if(userInput.trim().equalsIgnoreCase("find")) {
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
