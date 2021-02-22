package Duke.Commands;

import Duke.Duke;
import Duke.Errors.Errors;
import Duke.UI.UI;

public class MarkAsDoneCommand extends Duke {
    /**
     * Take the index from input
     * and mark the task with the index as done
     * @param doneTask
     */
    public static void markAsDone(String doneTask){
        int doneTaskIndex = Integer.parseInt(doneTask) - 1;
        if (doneTaskIndex < taskCount && doneTaskIndex >= 0) {
            UI.taskDone();
            lists.get(doneTaskIndex).markAsDone();
            PrintListCommand.printList(doneTaskIndex, doneTaskIndex + 1);
        } else {
            Errors.exceedListLength(doneTaskIndex + 1);
        }
    }
}
