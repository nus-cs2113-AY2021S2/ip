package command;

import parser.Parser;
import task.Task;
import task.TaskManager;

/**
Adds a task to the ArrayList
 */
public class AddCommand extends Command{
    @Override
    public void executeCommand(String userInput, TaskManager taskList){
        Task taskToAdd = Parser.getTask(userInput);
        taskList.addTask(taskToAdd);
    }
}
