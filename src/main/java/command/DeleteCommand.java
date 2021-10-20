package command;

import parser.Parser;
import task.TaskManager;
import exception.DukeException;

/***
 * Delete task from ArrayList
 */
public class DeleteCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager tasksList) throws DukeException {
        int taskNoDelete = Parser.getTaskNoToBeMarkDelete(userInput);
        tasksList.deleteTask(taskNoDelete);
    }
}
