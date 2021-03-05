package duke.command;

import duke.*;
import duke.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FilterCmd extends Command {
    public FilterCmd(String s) {
        super(s);
    }

    /**
     * Executes a filter command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if the date user input is not in form of yyyy-mm-dd
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
        try{
            String[] typeDate = userInput.split("[Ff][Ii][Ll][Tt][Ee][Rr]",2);
            LocalDate filterDate = Parser.parseDate(typeDate[1].trim());
            ArrayList<Task> filteredTasks = tasks.filterByDate(filterDate);
            ui.showFindResult(filteredTasks);
        } catch (DateTimeParseException e) {
            ui.showWrongDateMessage();
        }
    }
}
