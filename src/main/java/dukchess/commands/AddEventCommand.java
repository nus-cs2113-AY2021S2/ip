package dukchess.commands;

import dukchess.entity.Event;
import dukchess.ui.Ui;

public class AddEventCommand extends Command {
    private static String addEvent(String description, String at) {
        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
        return String.format("Gotcha, added this event: %s", newEvent.toString());
    }

    /**
     * Performs input validation before adding a new event to the list of tasks.
     * @param commandArgs - expects a two-length array, first element is event's description,
     *                    second element is when the event is at
     */
    public static void handleAddEvent(String commandArgs) {
        if (commandArgs.length() == 0) {
            Ui.printErrorMessage("Oops, event description cannot be empty :(");
            return;
        }
        String[] eventArgs = commandArgs.split(" /at ");
        if (eventArgs.length != 2) {
            Ui.printErrorMessage("Oops, event time cannot be empty :(");
            return;
        }
        String eventAdditionOutcome = addEvent(eventArgs[0], eventArgs[1]);
        System.out.println(eventAdditionOutcome);
    }
}
