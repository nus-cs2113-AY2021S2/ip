package Duke.Commands;

import Duke.Duke;
import Duke.UI.PrintMessages;
import Duke.Task.DeadlineTask;

import static Duke.UI.TEXT.TIME_DIVIDER;

public class AddDeadlineCommand extends Duke {
    /**
     * Add Deadline Task into the list,
     * show error message if theres no time input
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
