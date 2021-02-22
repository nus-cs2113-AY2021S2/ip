package command;

import command.Command;
import parser.Parser;
import task.TaskManager;

public class DoneCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        int taskNoDone = Parser.getTaskNoToBeMarkDone(userInput);
        taskList.markDone(taskNoDone);
    }
}
