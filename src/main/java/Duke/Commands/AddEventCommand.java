package Duke.Commands;

import Duke.Duke;
import Duke.UI.PrintMessages;
import Duke.Task.EventTask;

public class AddEventCommand extends Duke {
    /**
     * Add Event Task into the list
     * show error if theres no time input
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        if (taskDescription.contains("/")) {
            String[] taskAndTime = taskDescription.split("/", 2);
            lists.add(new EventTask(taskAndTime[0].trim(), taskAndTime[1].trim()));
            PrintMessages.taskAddedText();
            taskCount++;
        } else {
            PrintMessages.taskWithoutTime();
        }
    }
}
