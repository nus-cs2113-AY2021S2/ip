package Duke.Commands;

import Duke.Duke;
import Duke.Errors.Errors;
import Duke.UI.UI;

public class DeleteCommand extends Duke {
    /**
     * Delete a task
     * @param deleteTask
     */
    public static void deleteTask(String deleteTask){
        int deleteTaskIndex = Integer.parseInt(deleteTask) - 1;
        if (deleteTaskIndex < taskCount && deleteTaskIndex >= 0) {
            PrintListCommand.printList(deleteTaskIndex, deleteTaskIndex + 1);
            lists.remove(deleteTaskIndex);
            UI.taskDeleted();
            taskCount--;
        } else {
            Errors.exceedListLength(deleteTaskIndex + 1);
        }
    }
}
