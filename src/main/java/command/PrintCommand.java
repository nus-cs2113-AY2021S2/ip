package command;

import task.TaskManager;

public class PrintCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskManager) {
        taskManager.printTaskItems();
        System.out.println("Enter next command: ");
        }
    }



