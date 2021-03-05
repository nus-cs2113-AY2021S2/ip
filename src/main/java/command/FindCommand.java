package command;

import parser.Parser;
import task.TaskManager;

/**
 * Finds and lists all task in ArrayList whose task contains any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskManager tasksList) {
        String keywordToFind = Parser.getFindKeyword(userInput);
        tasksList.findTask(keywordToFind);
    }
}
