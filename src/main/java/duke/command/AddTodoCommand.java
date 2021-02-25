package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends Command {

    private String line;

    /**
     * Constructor of AddTodoCommand Class
     *
     * @param line user input
     */
    public AddTodoCommand(String line) {
        this.line = line;
    }

    /**
     * Adds Todo task to TaskList and saves it in a textfile
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(line);
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Returns false as command is not of type bye
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
