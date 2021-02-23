package commands;

import task.Deadline;
import exceptions.EmptyDescriptionException;

public class AddDeadline extends Command {

    public static void execute(String commandArgs) throws EmptyDescriptionException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        }
        final String[] descriptionAndDeadline = parser.splitDescriptionAndDeadline(commandArgs);
        Deadline deadline = new Deadline(descriptionAndDeadline[0], descriptionAndDeadline[1]);
        taskManager.addTask(deadline);
        printAddTask(deadline);
    }
}
