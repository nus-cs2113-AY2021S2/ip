package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;

public class DeleteCommand extends Baggie {

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public static void deleteTask(int taskIndex){
        if (taskIndex >= taskCount || taskIndex < 0) {
            PrintMessages.exceedListLength(taskIndex + 1);
            return;
        }
        PrintListCommand.printList(taskIndex, taskIndex + 1);
        lists.remove(taskIndex);
        PrintMessages.taskDeleted();
        taskCount--;
    }
}
