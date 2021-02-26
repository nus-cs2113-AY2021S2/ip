package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int TODO_LENGTH = 5;

    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

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
