package command;

import parser.Parser;
import task.Task;
import task.TaskManager;

public class AddCommand extends Command{
    @Override
    public void executeCommand(String userInput, TaskManager taskList){
        Task taskToAdd = Parser.getTask(userInput);
        taskList.addTask(taskToAdd);
    }
}
