package command;

import parser.Parser;
import task.TaskManager;

/***
 * Delete task from ArrayList
 */
public class DeleteCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        int taskNoDelete = Parser.getTaskNoToBeMarkDelete(userInput);
        taskList.deleteTask(taskNoDelete);
    }
}
