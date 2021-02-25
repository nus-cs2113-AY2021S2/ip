package command;

import exception.DukeException;
import parser.Parser;
import task.TaskManager;

public class FindCommand extends Command{
    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        String keywordToFind = Parser.getFindKeyword(userInput);
        taskList.findTask(keywordToFind);
    }
}
