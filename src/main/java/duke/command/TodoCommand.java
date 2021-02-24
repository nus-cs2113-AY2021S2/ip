package duke.command;

import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;
import duke.task.Todo;

import java.util.ArrayList;

public class TodoCommand extends Command {
    private static final int REQUIRED_NUMBER_OF_ARGS = 2;
    private static final int TASK_DESCRIPTION = 1;

    public TodoCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() throws DukeException {
        if (arguments.size() != REQUIRED_NUMBER_OF_ARGS) {
            throw new DukeException("Please give me more details about the task!");
        }

        String description = arguments.get(TASK_DESCRIPTION);
        if (description.isEmpty()) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }

        Todo todo = new Todo(description);
        dukeTaskList.addTask(todo);
        int numberOfTasks = dukeTaskList.getNumberOfTasks();
        DukePrinter.printTaskAdded(todo.toString(), numberOfTasks);
    }
}
