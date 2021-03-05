package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;
import Baggie.Task.EventTask;

import static Baggie.UI.TEXT.TIME_DIVIDER;

public class AddEventCommand extends Baggie {
    /**
     * Adds Event Task into the list
     * Shows error if theres no time input
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        if (taskDescription.contains(TIME_DIVIDER)) {
            String[] taskAndTime = taskDescription.split(TIME_DIVIDER, 2);
            lists.add(new EventTask(taskAndTime[0].trim(), taskAndTime[1].trim()));
            PrintMessages.taskAddedText();
            taskCount++;
        } else {
            PrintMessages.taskWithoutTime();
        }
    }
}
