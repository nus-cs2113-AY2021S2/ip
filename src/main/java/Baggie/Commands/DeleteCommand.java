package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;

public class DeleteCommand extends Baggie {
    /**
     * Deletes a task
     * @param deleteTask
     */
    public static void deleteTask(String deleteTask){
        try {
            int deleteTaskIndex = Integer.parseInt(deleteTask) - 1;
            if (deleteTaskIndex < taskCount && deleteTaskIndex >= 0) {
                PrintListCommand.printList(deleteTaskIndex, deleteTaskIndex + 1);
                lists.remove(deleteTaskIndex);
                PrintMessages.taskDeleted();
                taskCount--;
            } else {
                PrintMessages.exceedListLength(deleteTaskIndex + 1);
            }
        } catch (Exception e) {
            PrintMessages.illegalInput();
        }

    }
}
