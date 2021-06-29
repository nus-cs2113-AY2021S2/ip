package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;

public class MarkAsDoneCommand extends Baggie {
    /**
     * Takes the index from input.
     * Marks the task with the index as done.
     *
     * @param doneTaskIndex Index of task to be marked as done.
     */
    public static void markAsDone(int doneTaskIndex) {
        if (doneTaskIndex >= taskCount || doneTaskIndex < 0) {
            PrintMessages.exceedListLength(doneTaskIndex + 1);
            return;
        }
        if (!lists.get(doneTaskIndex).isDone()) {
            lists.get(doneTaskIndex).markAsDone();
            PrintMessages.taskDone();
            PrintListCommand.printList(doneTaskIndex, doneTaskIndex + 1);
        } else {
            PrintMessages.taskAlreadyDone();
        }
    }
}
