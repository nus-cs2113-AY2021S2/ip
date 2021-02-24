package duke.command;

import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static final int REQUIRED_NUMBER_OF_ARGS = 2;
    private static final int SEARCH_EXPRESSION = 1;

    public FindCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() throws DukeException {
        if (arguments.size() != REQUIRED_NUMBER_OF_ARGS) {
            throw new DukeException("Please give me a search expression!");
        }
        String searchExpression = arguments.get(SEARCH_EXPRESSION);
        ArrayList<String> matchingTasks = dukeTaskList.findTasks(searchExpression);
        DukePrinter.printMatchingTasks(matchingTasks);
    }
}
