package jarvis.commands;

import jarvis.exception.EmptyDescriptionException;
import jarvis.exception.EmptyDetailException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class AddEvent extends Command {

    /**
     * Adds an event task to the list
     *
     * @param userInput string entered by the user
     * @throws EmptyDescriptionException when description of the event task is empty
     * @throws EmptyDetailException when the detail of /at is not specified by the user
     */
    public static void execute(String userInput) throws EmptyDescriptionException, EmptyDetailException {
        Task deadline = Parser.parseStringToEvent(userInput);
        TaskList.addToTasks(deadline);
        addSuccessMessage(deadline);
    }
}
