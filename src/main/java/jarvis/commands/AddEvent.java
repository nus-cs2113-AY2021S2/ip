package jarvis.commands;

import jarvis.exception.EmptyDescriptionException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class AddEvent extends Command {

    /**
     * Adds an event task to the list
     *
     * @param userInput string entered by the user
     * @throws EmptyDescriptionException when description of the event task is empty
     */
    public static void execute(String userInput) throws EmptyDescriptionException {
        Task deadline = Parser.parseStringToEvent(userInput);
        TaskList.addToTasks(deadline);
        addSuccessMessage(deadline);
    }
}
