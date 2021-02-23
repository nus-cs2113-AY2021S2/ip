package command;

import parser.Parser;
import task.TaskManager;

public class DeleteCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        int taskNoDelete = Parser.getTaskNoToBeMarkDelete(userInput);
        taskList.deleteTask(taskNoDelete);


    }
}
