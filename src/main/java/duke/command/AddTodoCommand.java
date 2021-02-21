package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends Command {

    private String line;

    public AddTodoCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(line);
        storage.saveFile(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
