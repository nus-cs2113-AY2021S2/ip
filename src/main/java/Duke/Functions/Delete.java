package Duke.Functions;

import Duke.Duke;
import Duke.Exceptions.DukeException;

public class Delete extends Duke {
    public static void deleteTask(String deleteTask){
        try{
            int deleteTaskIndex = Integer.parseInt(deleteTask) - 1;
            if (deleteTaskIndex < taskCount && deleteTaskIndex >= 0) {
                System.out.println(" Yay! This task is deleted!");
                printList(deleteTaskIndex, deleteTaskIndex + 1);
                lists.remove(deleteTaskIndex);
                taskCount--;
            } else {
                DukeException.exceedListLength(deleteTaskIndex + 1);
            }
        } catch (Exception e) {
            DukeException.deleteWithoutNo();
        }
    }
}
