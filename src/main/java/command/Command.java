package command;
import task.TaskManager;

public abstract class Command {
    public abstract void executeCommand(String userInput, TaskManager taskManager);
}