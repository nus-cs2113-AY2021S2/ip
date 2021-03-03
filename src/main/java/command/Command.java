package command;

import task.TaskManager;
import exception.DukeException;

public abstract class Command {
    public abstract void executeCommand(String userInput, TaskManager taskManager) throws DukeException;
}