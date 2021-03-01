package Duke.Commands;

import Duke.Duke;
import Duke.UI.PrintMessages;

public class MarkAsDoneCommand extends Duke {
    /**
     * Takes the index from input
     * and marks the task with the index as done
     * @param doneTask
     */
    public static void markAsDone(String doneTask){
        try {
            int doneTaskIndex = Integer.parseInt(doneTask) - 1;
            if (doneTaskIndex < taskCount && doneTaskIndex >= 0) {
                if (!lists.get(doneTaskIndex).isDone()) {
                    lists.get(doneTaskIndex).markAsDone();
                    PrintMessages.taskDone();
                    PrintListCommand.printList(doneTaskIndex, doneTaskIndex + 1);
                } else {
                    PrintMessages.taskAlreadyDone();
                }
            } else {
                PrintMessages.exceedListLength(doneTaskIndex + 1);
            }
        } catch (Exception e) {
            PrintMessages.illegalInput();
        }

    }
}
