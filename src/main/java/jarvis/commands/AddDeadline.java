package jarvis.commands;

import jarvis.exception.EmptyDescriptionException;
import jarvis.exception.EmptyDetailException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class AddDeadline extends Command {

    /**
     * Adds a deadline task to the list
     *
     * @param userInput string entered by the user
     * @throws EmptyDescriptionException when description of the deadline task is empty
     * @throws EmptyDetailException when the detail of /by is not specified by the user
     */
    public static void execute(String userInput) throws EmptyDescriptionException, EmptyDetailException {
        Task deadline = Parser.parseStringToDeadline(userInput);
        TaskList.addToTasks(deadline);
        addSuccessMessage(deadline);
    }
}
