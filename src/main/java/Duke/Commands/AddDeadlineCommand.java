package Duke.Commands;

import Duke.Duke;
import Duke.UI.PrintMessages;
import Duke.Task.DeadlineTask;

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
            PrintMessages.taskAddedText();
            taskCount++;
        } else {
            PrintMessages.taskWithoutTime();
        }
    }
}
