package command;

import parser.Parser;
import task.TaskManager;
import exception.DukeException;

/**
 * Marks task as done when user indicates the task is done
 */
public class DoneCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager tasksList) throws DukeException {
        int taskNoDone = Parser.getTaskNoToBeMarkDone(userInput);
        tasksList.markDone(taskNoDone);
    }
}
