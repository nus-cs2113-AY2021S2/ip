package command;

import task.TaskManager;

/**
 * Lists all task in the ArrayList to the user.
 */
public class PrintCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskManager) {
        taskManager.printTaskItems();
        System.out.println("Enter next command: ");
        }
    }



