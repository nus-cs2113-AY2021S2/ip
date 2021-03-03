package commands;

import task.Event;
import exceptions.EmptyDescriptionException;

public class AddEvent extends Command {

    /**
     * add event and feedback display message when event added
     * @param commandArgs description and time of the event
     * @throws EmptyDescriptionException when description of the event is empty
     */
    public static void execute(String commandArgs) throws EmptyDescriptionException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        }
        final String[] splitDescriptionAndTime = parser.splitDescriptionAndTime(commandArgs);
        Event event = new Event(splitDescriptionAndTime[0], splitDescriptionAndTime[1]);
        taskManager.addTask(event);
        printAddTask(event);
    }
}
