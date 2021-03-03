package command;

import command.Command;
import parser.Parser;
import task.TaskManager;
import exception.DukeException;

/**
 * Marks task as done when user indicates the task is done
 */
public class DoneCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskList) throws DukeException {
        int taskNoDone = Parser.getTaskNoToBeMarkDone(userInput);
        taskList.markDone(taskNoDone);
    }
}
