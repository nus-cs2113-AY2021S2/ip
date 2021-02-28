package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DeadlineCommand is called when user wants to add a new Deadline
 */
public class DeadlineCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DEADLINE_LENGTH = 9;
    private static final int BY_LENGTH = 3;

    /**
     * Constructor method of DeadlineCommand which stores the user input
     *
     * @param fullCommand String of user input for adding a new Deadline
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Adds new Deadline to the TaskList tasks
     * Prints output confirming addition to user
     * Saves changes to data file
     * @param tasks TaskList containing all tasks
     * @param ui User Interface
     * @param storage Storage to save all tasks, updating the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = fullCommand.indexOf("/");
            String description = fullCommand.substring(DEADLINE_LENGTH, index - 1);
            String byString = fullCommand.substring(index + BY_LENGTH).trim();
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime by = LocalDateTime.parse(byString, inputFormat);
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.printNewTask(newTask, tasks.getTaskCount());
            storage.saveToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDescription("deadline");
        } catch (DateTimeParseException e) {
            ui.printDateError();
        }
    }
}
