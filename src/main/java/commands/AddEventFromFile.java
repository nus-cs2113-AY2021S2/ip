package commands;

import task.Deadline;
import task.Event;

public class AddEventFromFile extends Command {

    public static void execute(String commandDone, String commandArgs) {
        final String[] descriptionAndTime = parser.splitDescriptionAndTime(commandArgs);
        Event event = new Event(descriptionAndTime[0], descriptionAndTime[1]);
        if (commandDone.equals("1")) {
            event.markAsDone();
        }
        taskManager.addTask(event);
    }
}
