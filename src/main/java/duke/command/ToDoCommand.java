package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.ToDo;

/**
 * ToDoCommand is called when user wants to add a new ToDo.
 */
public class ToDoCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int TODO_LENGTH = 5;

    /**
     * Constructor method of ToDoCommand which stores the user input.
     *
     * @param fullCommand String of user input for adding a new ToDo.
     */
    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Adds new ToDo to the TaskList tasks.
     * Prints output confirming addition to user.
     * Saves changes to data file.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui User Interface.
     * @param storage Storage to save all tasks, updating the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String description = fullCommand.substring(TODO_LENGTH);
            Task newTask = new ToDo(description);
            tasks.addTask(newTask);
            ui.printNewTask(newTask, tasks.getTaskCount());
            storage.saveToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDescription("todo");
        }
    }
}
