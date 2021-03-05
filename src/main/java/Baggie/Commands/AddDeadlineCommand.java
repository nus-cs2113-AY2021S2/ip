package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;
import Baggie.Task.DeadlineTask;

import static Baggie.UI.TEXT.TIME_DIVIDER;

public class AddDeadlineCommand extends Baggie {
    /**
     * Adds Deadline Task into the list,
     * Shows error message if theres no time input
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        if (taskDescription.contains(TIME_DIVIDER)) {
            String[] taskAndTime = taskDescription.split(TIME_DIVIDER, 2);
            lists.add(new DeadlineTask(taskAndTime[0].trim(), taskAndTime[1].trim()));
            PrintMessages.taskAddedText();
            taskCount++;
        } else {
            PrintMessages.taskWithoutTime();
        }
    }
}
