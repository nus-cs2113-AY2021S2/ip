package Duke.Commands;

import Duke.Duke;
import Duke.Errors.Errors;
import Duke.Task.EventTask;
import Duke.UI.UI;

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
            UI.taskAddedText();
            taskCount++;
        } else {
            Errors.taskWithoutTime();
        }
    }
}
