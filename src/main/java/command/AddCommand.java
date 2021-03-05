package command;

import parser.Parser;
import task.Task;
import task.TaskManager;
import exception.DukeException;

/**
 * Adds a task to the ArrayList
 */
public class AddCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager tasksList) throws DukeException {
        Task taskToAdd = Parser.getTask(userInput);
        tasksList.addTask(taskToAdd);
    }
}
