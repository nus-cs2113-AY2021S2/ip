package Duke.Commands;

import Duke.Duke;
import Duke.Errors.Errors;
import Duke.Task.DeadlineTask;
import Duke.UI.UI;

public class AddDeadlineCommand extends Duke {
    /**
     * Add Deadline Task into the list,
     * show error message if theres no time input
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        if (taskDescription.contains("/")) {
            String[] taskAndTime = taskDescription.split("/", 2);
            lists.add(new DeadlineTask(taskAndTime[0].trim(), taskAndTime[1].trim()));
            UI.taskAddedText();
            taskCount++;
        } else {
            Errors.taskWithoutTime();
        }
    }
}
