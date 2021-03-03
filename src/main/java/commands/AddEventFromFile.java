package commands;

import task.Deadline;
import task.Event;

public class AddEventFromFile extends Command {

    /**
     * add event from save file
     * @param commandDone whether the event is done or not
     * @param commandArgs description of the event
     */
    public static void execute(String commandDone, String commandArgs) {
        final String[] descriptionAndTime = parser.splitDescriptionAndTime(commandArgs);
        Event event = new Event(descriptionAndTime[0], descriptionAndTime[1]);
        if (commandDone.equals("1")) {
            event.markAsDone();
        }
        taskManager.addTask(event);
    }
}
