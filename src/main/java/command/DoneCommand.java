package command;

import command.Command;
import parser.Parser;
import task.TaskManager;

/**
 * Marks task as done when user indicates the task is done
 */
public class DoneCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        int taskNoDone = Parser.getTaskNoToBeMarkDone(userInput);
        taskList.markDone(taskNoDone);
    }
}
