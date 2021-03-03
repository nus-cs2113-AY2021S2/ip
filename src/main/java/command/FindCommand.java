package command;

import exception.DukeException;
import parser.Parser;
import task.TaskManager;

/**
 * Finds and lists all task in ArrayList whose task contains any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindCommand extends Command{
    @Override
    public void executeCommand(String userInput, TaskManager taskList) {
        String keywordToFind = Parser.getFindKeyword(userInput);
        taskList.findTask(keywordToFind);
    }
}
